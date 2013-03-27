package com.github.smart.domain;

public class BrandSimilarity
{
    private int id;
    private String thisBrand;
    private String thatBrand;
    private double similarity;

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BrandSimilarity that = (BrandSimilarity) o;

        if (thatBrand != null ? !thatBrand.equals(that.thatBrand) : that.thatBrand != null) return false;
        if (thisBrand != null ? !thisBrand.equals(that.thisBrand) : that.thisBrand != null) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = thisBrand != null ? thisBrand.hashCode() : 0;
        result = 31 * result + (thatBrand != null ? thatBrand.hashCode() : 0);
        return result;
    }

    public int getId()
    {
        return id;

    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getThisBrand()
    {
        return thisBrand;
    }

    public void setThisBrand(String thisBrand)
    {
        this.thisBrand = thisBrand;
    }

    public String getThatBrand()
    {
        return thatBrand;
    }

    public void setThatBrand(String thatBrand)
    {
        this.thatBrand = thatBrand;
    }

    public double getSimilarity()
    {
        return similarity;
    }

    public void setSimilarity(double similarity)
    {
        this.similarity = similarity;
    }
}
