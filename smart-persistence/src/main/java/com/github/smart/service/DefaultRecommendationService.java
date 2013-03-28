package com.github.smart.service;

import com.github.smart.domain.BrandSimilarity;
import com.github.smart.domain.Customer;
import com.github.smart.domain.Profile;
import com.google.common.base.Function;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

import static com.google.common.collect.Iterables.transform;
import static com.google.common.collect.Sets.newHashSet;


public class DefaultRecommendationService implements RecommendationService
{
    public static final String ALL_BRANDS_QUERY = "SELECT DISTINCT BRAND FROM PROFILE";
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public List<String> retrieveBrands()
    {
        Session currentSession = sessionFactory.getCurrentSession();
        SQLQuery sqlQuery = currentSession.createSQLQuery(ALL_BRANDS_QUERY);
        return sqlQuery.list();
    }

    @Override
    @Transactional
    public void saveSimilarity(String thisBrand, String thatBrand, double similarity)
    {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.save(createBrandSimilarity(thisBrand, thatBrand, similarity));
    }

    @Override
    @Transactional
    public int countBothBrands(String thisBrand, String thatBrand)
    {
        Session currentSession = sessionFactory.getCurrentSession();
        List<Customer> customers = currentSession.createQuery("from Customer").list();
        return countBothBrandsCustomer(thisBrand, thatBrand, customers);
    }

    @Override
    @Transactional
    public int countEitherBrand(String thisBrand, String thatBrand)
    {
        Session currentSession = sessionFactory.getCurrentSession();
        List<Customer> customers = currentSession.createQuery("from Customer").list();
        return countEitherBrandCustomer(thisBrand, thatBrand, customers);
    }

    private boolean containsEitherBrand(String thisBrand, String thatBrand, Set<String> customerBrands)
    {
        return customerBrands.contains(thisBrand) || customerBrands.contains(thatBrand);
    }

    @Override
    @Transactional
    public Set<String> findCustomerBrands(int customerId)
    {
        Session currentSession = sessionFactory.getCurrentSession();
        Customer customer = (Customer) currentSession.load(Customer.class, customerId);

        Function<Profile, String> brandFilter = createBrandFilter();
        Iterable<String> transform = transform(customer.getProfiles(), brandFilter);
        return newHashSet(transform);

    }

    @Override
    @Transactional
    public Double retrieveSimilarity(String thisBrand, String thatBrand)
    {
        Session currentSession = sessionFactory.getCurrentSession();
        SQLQuery query = currentSession.createSQLQuery("SELECT SIMILARITY FROM BRAND_SIMILARITY WHERE THIS_BRAND = :thisBrand AND THAT_BRAND = :thatBrand");
        query.setString("thisBrand", thisBrand).setString("thatBrand", thatBrand);
        return (Double) query.list().get(0);


    }

    @Required
    public void setSessionFactory(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }

    private BrandSimilarity createBrandSimilarity(String thisBrand, String thatBrand, double similarity)
    {
        BrandSimilarity brandSimilarity = new BrandSimilarity();
        brandSimilarity.setThisBrand(thisBrand);
        brandSimilarity.setThatBrand(thatBrand);
        brandSimilarity.setSimilarity(similarity);
        return brandSimilarity;
    }

    private Function<Profile, String> createBrandFilter()
    {
        return new Function<Profile, String>()
        {
            @Override
            public String apply(Profile input)
            {
                return input.getBrand();
            }
        };
    }

    private int countBothBrandsCustomer(String thisBrand, String thatBrand, List<Customer> customers)
    {
        int count =0;
        for(Customer customer : customers)
        {
            Set<String> customerBrands = findCustomerBrands(customer.getId());
            if(containsBothBrands(thisBrand, thatBrand, customerBrands))
            {
                count++;
            }
        }
        return count;
    }

    private boolean containsBothBrands(String thisBrand, String thatBrand, Set<String> customerBrands)
    {
        return customerBrands.contains(thisBrand) && customerBrands.contains(thatBrand);
    }

    private int countEitherBrandCustomer(String thisBrand, String thatBrand, List<Customer> customers)
    {
        int count =0;
        for(Customer customer : customers)
        {
            Set<String> customerBrands = findCustomerBrands(customer.getId());
            if(containsEitherBrand(thisBrand, thatBrand, customerBrands))
            {
                count++;
            }
        }
        return count;
    }
}
