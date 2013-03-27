package com.github.smart.domain;

import com.github.smart.service.FieldsCounter;
import com.github.smart.service.SimilarityComparator;

public class Address implements SimilarityComparator<Address>, FieldsCounter
{
    private String state;
    private String postCode;
    private String city;
    private String street;
    private String addressLine;

    public Address() {
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    @Override
    public int totalFields() {
        return 5;
    }

    @Override
    public int compare(Address target) {
        return 0;
    }
}
