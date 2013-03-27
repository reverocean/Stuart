package com.github.smart.domain;

public class Customer implements SimilarityComparator<Customer>, FieldsCounter{
    private Address address;

    public Customer() {
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public int compare(Customer target) {
        return 0 + this.address.compare(target.getAddress());
    }

    @Override
    public int totalFields() {
        return 5 + this.address.totalFields();
    }
}
