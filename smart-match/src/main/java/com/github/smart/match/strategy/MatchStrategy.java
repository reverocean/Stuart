package com.github.smart.match.strategy;

import com.github.smart.domain.Profile;
import com.github.smart.match.Similarity;
import com.github.smart.domain.Customer;

public interface MatchStrategy {
    public Similarity match(Profile firstCustomer, Profile secondCustomer);
}
