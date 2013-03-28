package com.github.smart.service;

import com.github.smart.domain.Customer;
import com.google.common.base.Predicate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.google.common.collect.Iterables.filter;
import static com.google.common.collect.Lists.newArrayList;

public class DefaultLessThanService implements LessThanService {
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public List<Customer> getLessBrandsCustomers(final int limit) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery("FROM Customer");
        List<Customer> list = query.list();
        return newArrayList(filter(list, new Predicate<Customer>() {
            @Override
            public boolean apply(Customer input) {
                return input.getProfiles().size() < limit;
            }
        }));
    }

    @Required
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
