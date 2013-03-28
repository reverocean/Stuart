package com.github.smart.service;

import com.github.smart.domain.Address;
import com.github.smart.domain.Customer;
import com.github.smart.domain.Individual;
import com.github.smart.domain.Profile;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import static com.google.common.collect.Sets.newHashSet;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testHibernateApplicationContext.xml"})
@TestExecutionListeners({DirtiesContextTestExecutionListener.class,
        DependencyInjectionTestExecutionListener.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class DefaultCustomerServiceTest
{
    @Autowired
    CustomerService defaultCustomerService;

    @Autowired
    RecommendationService recommendationService;

    @Autowired
    LessThanService lessThanService;

    @Test
    public void test(){
//        defaultCustomerService.saveIndividual();
//        defaultCustomerService.saveIndividual("suncorp");
//        defaultCustomerService.saveIndividual("aami");
//        defaultCustomerService.saveIndividual("bingle");
        recommendationService.saveSimilarity("suncorp", "bingle", 12.0);
        Customer customer = new Customer();
        customer.setName("davenkin");
        customer.setProfiles(newHashSet(createProfile("suncorp"),createProfile("bingle")));
        defaultCustomerService.save(customer);
        List<String> strings = recommendationService.retrieveBrands();
        Double aDouble = recommendationService.retrieveSimilarity("suncorp", "bingle");
        List<String> brands = recommendationService.findCustomerBrands(1);
        List<Customer> lessBrandsCustomers = lessThanService.getLessBrandsCustomers(2);
        System.out.println("dd");
        System.out.println(strings.size());
        System.out.println(aDouble);
        System.out.println(brands.get(0));
        System.out.println(lessBrandsCustomers.size());
    }

    private Profile createProfile(String brand)
    {
        Individual individual = new Individual();
        individual.setName("name");
        Address address = new Address();
        address.setAddressLine("line");
        address.setCity("hechuan");
        address.setPostCode("12345");
        address.setState("chongqing");
        address.setStreet("tianfu");
        individual.setAddress(address);
        individual.setEmail("davenkin");
        individual.setDateOfBirth(new Date(System.currentTimeMillis()));
        individual.setRegisterTime(new Timestamp(System.currentTimeMillis()));
        Profile profile = new Profile();
        profile.setBrand(brand);
        profile.setIndividual(individual);
        return profile;
    }
}
