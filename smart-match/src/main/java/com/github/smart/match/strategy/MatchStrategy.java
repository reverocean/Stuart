package com.github.smart.match.strategy;

import com.github.smart.match.Similarity;
import com.github.smart.domain.Customer;

public interface MatchStrategy {
    public Similarity match(Customer firstCustomer, Customer secondCustomer);
}
