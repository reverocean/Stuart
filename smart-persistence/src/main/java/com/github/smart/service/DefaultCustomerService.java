package com.github.smart.service;

import com.github.smart.domain.Address;
import com.github.smart.domain.Customer;
import com.github.smart.domain.Individual;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Timestamp;

public class DefaultCustomerService implements CustomerService
{
    private SessionFactory sessionFactory;

    @Override
    public Customer findById(int customerId)
    {
        return stubCustomer();
    }
    
    @Override
    @Transactional
    public void saveIndividual()
    {
        Session currentSession = sessionFactory.getCurrentSession();
        Individual individual = new Individual();
        individual.setName("name");
        Address address = new Address();
        address.setAddressLine("line");
        address.setCity("hechuan");
        address.setPostCode("12345");
        address.setState("chongqing");
        address.setStreet("tianfu");
        individual.setAddress(address);
        individual.setEmail("davenkin");
        individual.setDateOfBirth(new Date(System.currentTimeMillis()));
        individual.setRegisterTime(new Timestamp(System.currentTimeMillis()));
        currentSession.save(individual);
    }

    
    private Customer stubCustomer()
    {
        return new Customer();
    }

    @Required
    public void setSessionFactory(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }
}
