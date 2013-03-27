package com.github.smart.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

import java.util.List;

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

    @Test
    public void test(){
//        defaultCustomerService.saveIndividual();
        defaultCustomerService.saveIndividual("suncorp");
        defaultCustomerService.saveIndividual("aami");
        defaultCustomerService.saveIndividual("bingle");
        recommendationService.saveSimilarity("suncorp", "bingle", 12.0);
        List<String> strings = recommendationService.retrieveBrands();
        System.out.println("dd");
        System.out.println(strings.size());
    }
}