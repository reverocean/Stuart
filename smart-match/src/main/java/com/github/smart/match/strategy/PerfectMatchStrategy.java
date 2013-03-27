package com.github.smart.match.strategy;

import com.github.smart.match.Similarity;
import com.github.smart.domain.Customer;

public class PerfectMatchStrategy implements MatchStrategy {
    @Override
    public Similarity match(Customer firstCustomer, Customer secondCustomer) {
        return new Similarity(100);
    }
}
