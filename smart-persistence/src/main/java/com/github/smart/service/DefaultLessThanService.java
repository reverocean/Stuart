package com.github.smart.service;

import com.github.smart.domain.Customer;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Required;

import java.util.List;

public class DefaultLessThanService implements LessThanService {
    private SessionFactory sessionFactory;

    @Override
    public List<Customer> getLessBrandsCustomers(int limit) {
        return null;
    }

    @Required
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
