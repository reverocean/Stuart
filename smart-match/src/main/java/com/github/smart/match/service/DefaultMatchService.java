package com.github.smart.match.service;

import com.github.smart.domain.Customer;
import com.github.smart.match.Similarity;
import com.github.smart.match.strategy.MatchStrategy;
import com.github.smart.service.CustomerService;

public class DefaultMatchService implements MatchService{
    public static final int MATCH_THRESHOLD = 100;
    private MatchStrategy matchStrategy;
    private CustomerService customerService;

    @Override
    public boolean matchCustomer(int firstCustomerId, int secondCustomerId) {
        Customer firstCustomer = customerService.findById(firstCustomerId);
        Customer secondCustomer = customerService.findById(secondCustomerId);
        Similarity similarity = matchStrategy.match(firstCustomer, secondCustomer);
        return similarity.greaterThan(MATCH_THRESHOLD);
    }

    public void setMatchStrategy(MatchStrategy matchStrategy) {
        this.matchStrategy = matchStrategy;
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }
}
