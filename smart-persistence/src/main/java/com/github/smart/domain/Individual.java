package com.github.smart.domain;


import com.mysql.jdbc.StringUtils;

import java.sql.Date;
import java.sql.Timestamp;

public class Individual implements SimilarityComparator<Individual>, FieldsCounter {
    private int id;
    private String name;
    private String email;
    private Date dateOfBirth;
    private String gender;
    private Timestamp registerTime;
    private Address address;

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Timestamp getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Timestamp registerTime) {
        this.registerTime = registerTime;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public int totalFields() {
        return 4 + this.address.totalFields();
    }

    @Override
    public int compare(Individual target) {
        int compareResult = 0;
        if (isNotNullOrEmpty(this.getName(), target.getName())) {
            if (this.getName().equalsIgnoreCase(target.getName())) {
                compareResult++;
            }
        }
        if (isNotNullOrEmpty(this.getEmail(), target.getEmail())) {
            if (this.getEmail().equalsIgnoreCase(target.getEmail())) {
                compareResult++;
            }
        }
        if (isNotNullOrEmpty(this.getName(), target.getName())) {
            if (this.getGender().equalsIgnoreCase(target.getGender())) {
                compareResult++;
            }
        }
        if (this.getDateOfBirth() != null && target.getDateOfBirth() != null) {
            if (this.getDateOfBirth().equals(target.getDateOfBirth())) {
                compareResult++;
            }
        }

        return compareResult + this.getAddress().compare(target.getAddress());
    }

    private boolean isNotNullOrEmpty(String sourceStr, String targetStr) {
        return (!StringUtils.isNullOrEmpty(sourceStr)) && (!StringUtils.isNullOrEmpty(targetStr));
    }
}
