package com.github.smart.domain;

import com.github.smart.service.FieldsCounter;
import com.github.smart.service.SimilarityComparator;

import java.util.Set;

public class Customer implements SimilarityComparator<Customer>, FieldsCounter
{
    private int id;
    private String name;
    private Set<String> brands;

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
        return 5; //+ this.address.totalFields();
    }

    public Set<String> getBrands()
    {
        return brands;
    }

    public void setBrands(Set<String> brands)
    {
        this.brands = brands;
    }
}
