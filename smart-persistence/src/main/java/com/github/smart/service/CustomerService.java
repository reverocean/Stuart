package com.github.smart.service;

import com.github.smart.domain.Customer;
import org.springframework.transaction.annotation.Transactional;

public interface CustomerService
{
    public Customer findById(int customerId);

    public void save(Customer customer);

    @Transactional
    void saveIndividual(String brand);
}
