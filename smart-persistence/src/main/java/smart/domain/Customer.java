package smart.domain;

import java.sql.Date;
import java.sql.Timestamp;

public class Customer
{
    private int id;
    private String name;
    private String email;
    private String brand;
    private Date dateOfBirth;
    private String gender;
    private Timestamp registerTime;
    private Address address;

    public Customer()
    {
    }

    public Address getAddress()
    {
        return address;
    }

    public void setAddress(Address address)
    {
        this.address = address;
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

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getBrand()
    {
        return brand;
    }

    public void setBrand(String brand)
    {
        this.brand = brand;
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
}
