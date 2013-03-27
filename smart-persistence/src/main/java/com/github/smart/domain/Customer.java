package com.github.smart.domain;

import java.util.Set;

public class Customer implements SimilarityComparator<Customer>, FieldsCounter
{
    private int id;
    private String name;
    private Set<Profile> profiles;

    public Customer()
    {
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

    @Override
    public int compare(Customer target)
    {
        return 0;
    }

    @Override
    public int totalFields()
    {
        return 5;
    }

    public Set<Profile> getProfiles()
    {
        return profiles;
    }

    public void setProfiles(Set<Profile> profiles)
    {
        this.profiles = profiles;
    }
}
