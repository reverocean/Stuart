package com.github.smart.service;

import com.github.smart.domain.Address;
import com.github.smart.domain.Customer;

public class DefaultCustomerService implements CustomerService
{
    @Override
    public Customer findById(int customerId)
    {
        return stubCustomer();
    }

    private Customer stubCustomer()
    {
        Customer customer = new Customer();
        Address address = createAddress();
        customer.setAddress(address);
        return customer;
    }

    private Address createAddress()
    {
        Address address = new Address();
        address.setAddressLine("Nanxiangshan");
        address.setCity("Hechuan");
        address.setPostCode("123446");
        address.setState("Chongqing");
        address.setStreet("Nanxiangshan");
        return address;
    }
}
