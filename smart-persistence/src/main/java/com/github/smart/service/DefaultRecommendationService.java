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

import static com.google.common.collect.Iterables.transform;
import static com.google.common.collect.Lists.newArrayList;


public class DefaultRecommendationService implements RecommendationService
{
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public List<String> retrieveBrands()
    {
        Session currentSession = sessionFactory.getCurrentSession();
        SQLQuery sqlQuery = currentSession.createSQLQuery("SELECT DISTINCT BRAND FROM PROFILE");
        return sqlQuery.list();
    }

    @Override
    @Transactional
    public void saveSimilarity(String thisBrand, String thatBrand, double similarity)
    {
        BrandSimilarity brandSimilarity = new BrandSimilarity();
        brandSimilarity.setThisBrand(thisBrand);
        brandSimilarity.setThatBrand(thatBrand);
        brandSimilarity.setSimilarity(similarity);
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.save(brandSimilarity);
    }

    @Override
    public int countBothBrands(String thisBrand, String thatBrand)
    {
        return 1;
    }

    @Override
    public int countEitherBrand(String thisBrand, String thatBrand)
    {
        return 1;
    }

    @Override
    @Transactional
    public List<String> findCustomerBrands(int customerId)
    {
        Session currentSession = sessionFactory.getCurrentSession();
        Customer customer = (Customer) currentSession.load(Customer.class, customerId);

        Iterable<String> transform = transform(customer.getProfiles(), new Function<Profile, String>()
        {
            @Override
            public String apply(Profile input)
            {
                return input.getBrand();
            }
        });
        return newArrayList(transform);

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
}
