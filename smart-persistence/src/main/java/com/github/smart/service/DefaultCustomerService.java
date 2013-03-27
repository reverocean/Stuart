package com.github.smart.service;

import com.github.smart.domain.Address;
import com.github.smart.domain.Customer;
import com.github.smart.domain.Individual;
import com.github.smart.domain.Profile;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;

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
    public void saveIndividual(String brand)
    {
        Session currentSession = sessionFactory.getCurrentSession();
        Profile profile = createProfile(brand);
        currentSession.save(profile);
    }

    private Profile createProfile(String brand)
    {
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
        Profile profile = new Profile();
        profile.setBrand(brand);
        profile.setIndividual(individual);
        return profile;
    }

    @Override
    @Transactional
    public void createCustomer()
    {
        Session currentSession = sessionFactory.getCurrentSession();
        Customer customer = new Customer();
        customer.setName("name");
        HashSet<Profile> profiles = new HashSet<Profile>();
        Profile profile = createProfile("suncorp");
        profiles.add(profile);
        customer.setProfiles(profiles);
        currentSession.save(customer);
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
