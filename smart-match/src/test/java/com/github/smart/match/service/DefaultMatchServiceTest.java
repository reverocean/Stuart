package com.github.smart.match.service;

import com.github.smart.domain.Address;
import com.github.smart.domain.Individual;
import com.github.smart.domain.Profile;
import org.junit.Test;

import java.sql.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class DefaultMatchServiceTest {
    @Test
    public void should_be_hundred_percent_match_given_same_profile() {
        Profile profile = createProfile("test",
                "male",
                "test@smart.com",
                "cn",
                "400000",
                "chengdu",
                "tianfu",
                "100");

        DefaultMatchService matchService = new DefaultMatchService();
        assertThat(matchService.matchCustomer(profile, profile), is(true));
    }

    @Test
    public void should_be_less_than_hundred_percent_match_given_different_profile() {
        Profile firstProfile = createProfile("test",
                "male",
                "test@smart.com",
                "cn",
                "400000",
                "chengdu",
                "tianfu",
                "100");

        Profile secondProfile = createProfile("test",
                "female",
                "test@smart.com",
                "cn",
                "400000",
                "chengdu",
                "tianfu",
                "100");

        DefaultMatchService matchService = new DefaultMatchService();
        assertThat(matchService.matchCustomer(firstProfile, secondProfile), is(true));
    }

    private Profile createProfile(String name, String gender, String email, String state, String postCode, String city, String street, String addressLine) {
        Profile profile = new Profile();
        Individual individual = new Individual();
        individual.setName(name);
        individual.setGender(gender);
        individual.setEmail(email);
        individual.setDateOfBirth(new Date(1000));
        Address address = new Address();
        address.setState(state);
        address.setPostCode(postCode);
        address.setCity(city);
        address.setStreet(street);
        address.setAddressLine(addressLine);
        individual.setAddress(address);
        profile.setIndividual(individual);
        return profile;
    }
}
