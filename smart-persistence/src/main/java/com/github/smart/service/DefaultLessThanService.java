package com.github.smart.service;

import com.github.smart.domain.Customer;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class DefaultLessThanService implements LessThanService {
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public List<Customer> getLessBrandsCustomers(int limit) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery("FROM Customer c WHERE size(c.profiles) < " + limit);
        return query.list();
    }

    @Required
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
