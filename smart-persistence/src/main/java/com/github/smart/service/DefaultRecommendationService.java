package com.github.smart.service;

import com.github.smart.domain.BrandSimilarity;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class DefaultRecommendationService implements RecommendationService
{
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public List<String> retrieveBrands() {
        Session currentSession = sessionFactory.getCurrentSession();
        SQLQuery sqlQuery = currentSession.createSQLQuery("SELECT DISTINCT BRAND FROM PROFILE");
       return sqlQuery.list();
    }

    @Override
    @Transactional
    public void saveSimilarity(String thisBrand, String thatBrand, double similarity) {
        BrandSimilarity brandSimilarity = new BrandSimilarity();
        brandSimilarity.setThisBrand(thisBrand);
        brandSimilarity.setThatBrand(thatBrand);
        brandSimilarity.setSimilarity(similarity);
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.save(brandSimilarity);
    }

    @Override
    public int countBothBrands(String thisBrand, String thatBrand) {
        return 1;
    }

    @Override
    public int countEitherBrand(String thisBrand, String thatBrand) {
        return 1;
    }

    @Override
    public List<String> findCustomerBrands(String customerId) {
        return null;
    }

    @Override
    public double retrieveSimilarity(String customerBrand, String brand) {
        return 0.;
    }

    @Required
    public void setSessionFactory(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }
}
