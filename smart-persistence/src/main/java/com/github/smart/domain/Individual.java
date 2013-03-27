package com.github.smart.domain;

import java.sql.Date;
import java.sql.Timestamp;

public class Individual
{
    private int id;
    private String name;
    private String email;
    private Date dateOfBirth;
    private String gender;
    private Timestamp registerTime;
    private Address address;

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

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public Date getDateOfBirth()
    {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth)
    {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender()
    {
        return gender;
    }

    public void setGender(String gender)
    {
        this.gender = gender;
    }

    public Timestamp getRegisterTime()
    {
        return registerTime;
    }

    public void setRegisterTime(Timestamp registerTime)
    {
        this.registerTime = registerTime;
    }

    public Address getAddress()
    {
        return address;
    }

    public void setAddress(Address address)
    {
        this.address = address;
    }
}
