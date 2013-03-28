package com.github.smart.web.domains;

import java.util.List;

public class Customer {
    private int customerId;
    private String name;
    private List<String> brands;

    public Customer(int customerId, String name, List<String> brands) {
        this.customerId = customerId;
        this.name = name;
        this.brands = brands;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public List<String> getBrands() {
        return brands;
    }
}
