package com.github.smart.service;

import com.github.smart.domain.Customer;

import java.util.List;

public interface LessThanService {
    List<Customer> getLessBrandsCustomers(int limit);
}
