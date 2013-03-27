package com.github.smart.domain;

public class Profile
{
    private int id;
    private String brand;
    private Individual individual;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getBrand()
    {
        return brand;
    }

    public void setBrand(String brand)
    {
        this.brand = brand;
    }

    public Individual getIndividual()
    {
        return individual;
    }

    public void setIndividual(Individual individual)
    {
        this.individual = individual;
    }
}
