package com.github.smart.service;

import com.github.smart.domain.Customer;

public interface CustomerService
{
    public Customer findById(int customerId);

    public void save(Customer customer);

    void saveIndividual(String brand);
    void createCustomer();
}
