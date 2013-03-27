package com.github.smart.service;

import com.github.smart.domain.Customer;
import org.hibernate.SessionFactory;

import java.util.List;

public class DefaultLessThanService implements LessThanService {
    private final SessionFactory sessionFactory;

    public DefaultLessThanService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Customer> getLessBrandsCustomers(int limit) {
        return null;
    }
}
