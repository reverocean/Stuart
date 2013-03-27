package com.github.smart.match.strategy;

import com.github.smart.match.Similarity;
import com.github.smart.domain.Customer;

public class PerfectMatchStrategy implements MatchStrategy {
    @Override
    public Similarity match(Customer firstCustomer, Customer secondCustomer) {
        int total = firstCustomer.totalFields();
        int similarity = firstCustomer.compare(secondCustomer);
        return new Similarity(getSimilarityPercentage(similarity, total));
    }

    private int getSimilarityPercentage(int similarity, int total) {
        if (total > 0) {
            return 100 * similarity / total;
        }
        return 0;
    }
}
