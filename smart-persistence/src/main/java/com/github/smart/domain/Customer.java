package com.github.smart.domain;

public class Customer implements SimilarityComparator<Customer>, FieldsCounter{
    private int id;
    private String name;
    private ProfileBrand profileBrand;

    public Customer() {
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public ProfileBrand getProfileBrand()
    {
        return profileBrand;
    }

    public void setProfileBrand(ProfileBrand profileBrand)
    {
        this.profileBrand = profileBrand;
    }

    @Override
    public int compare(Customer target) {
        return 0;
    }

    @Override
    public int totalFields() {
        return 5; //+ this.address.totalFields();
    }
}
