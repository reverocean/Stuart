package com.github.smart.match.strategy;

import com.github.smart.domain.Profile;
import com.github.smart.match.Similarity;
import com.github.smart.domain.Customer;

public class PerfectMatchStrategy implements MatchStrategy {
    @Override
    public Similarity match(Profile firstProfile, Profile secondProfile) {
        if (perfectMatchName(firstProfile, secondProfile)) return new Similarity(0);
        int total = firstProfile.totalFields();
        int similarity = firstProfile.compare(secondProfile);
        return new Similarity(getSimilarityPercentage(similarity, total));
    }

    private boolean perfectMatchName(Profile firstProfile, Profile secondProfile) {
        String firstCustomerName = firstProfile.getIndividual().getName();
        String secondCustomerName = secondProfile.getIndividual().getName();
        if (firstCustomerName != null && secondCustomerName != null) {
            if (firstCustomerName.equalsIgnoreCase(secondCustomerName)) {
                return true;
            }
        }
        return false;
    }

    private int getSimilarityPercentage(int similarity, int total) {
        if (total > 0) {
            return 100 * similarity / total;
        }
        return 0;
    }
}
