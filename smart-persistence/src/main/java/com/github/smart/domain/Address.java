package com.github.smart.domain;

import com.mysql.jdbc.StringUtils;

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
        int compareResult = 0;
        if (isNotNullOrEmpty(this.getState(), target.getState())) {
            if (this.getState().equalsIgnoreCase(target.getState())) {
                compareResult++;
            }
        }
        if (isNotNullOrEmpty(this.getPostCode(), target.getPostCode())) {
            if (this.getPostCode().equalsIgnoreCase(target.getPostCode())) {
                compareResult++;
            }
        }
        if (isNotNullOrEmpty(this.getCity(), target.getCity())) {
            if (this.getCity().equalsIgnoreCase(target.getCity())) {
                compareResult++;
            }
        }
        if (isNotNullOrEmpty(this.getStreet(), target.getStreet())) {
            if (this.getStreet().equalsIgnoreCase(target.getStreet())) {
                compareResult++;
            }
        }
        if (isNotNullOrEmpty(this.getAddressLine(), target.getAddressLine())) {
            if (this.getAddressLine().equalsIgnoreCase(target.getAddressLine())) {
                compareResult++;
            }
        }
        return compareResult;
    }

    private boolean isNotNullOrEmpty(String sourceStr, String targetStr) {
        return (!StringUtils.isNullOrEmpty(sourceStr)) && (!StringUtils.isNullOrEmpty(targetStr));
    }
}
