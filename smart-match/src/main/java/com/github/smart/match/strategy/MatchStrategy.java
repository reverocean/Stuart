package com.github.smart.match.strategy;

import com.github.smart.match.Similarity;
import com.github.smart.domain.Customer;

public interface MatchStrategy {
    Similarity match(Customer firstCustomer, Customer secondCustomer);
}
