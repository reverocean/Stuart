package com.github.smart.service;

import com.github.smart.domain.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;

public class DefaultCustomerService implements CustomerService
{
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public Customer findById(int customerId)
    {
        Session currentSession = sessionFactory.getCurrentSession();
        return (Customer) currentSession.load(Customer.class, customerId);
    }

    @Override
    @Transactional
    public void save(Customer customer)
    {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.save(customer);
    }


    @Required
    public void setSessionFactory(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }
}
