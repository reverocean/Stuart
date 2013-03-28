package com.github.smart.domain;

public class Profile implements SimilarityComparator<Profile>, FieldsCounter, MatchIndicator {
    private int id;
    private String brand;
    private Individual individual;
    private boolean isMatched = false;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Individual getIndividual() {
        return individual;
    }

    public void setIndividual(Individual individual) {
        this.individual = individual;
    }

    @Override
    public int totalFields() {
        return this.individual.totalFields();
    }

    @Override
    public boolean matched() {
        return isMatched;
    }

    @Override
    public boolean equals(Object o) {
        return this.getId() == ((Profile)o).getId();
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public void hit() {

        isMatched = true;
    }

    @Override
    public int compare(Profile target) {
        return this.individual.compare(target.getIndividual());
    }
}
