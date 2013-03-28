package com.github.smart.match.service;

import com.github.smart.domain.Profile;
import com.github.smart.match.Similarity;

public class DefaultMatchService implements MatchService{

    @Override
    public boolean matchCustomer(Profile firstProfile, Profile secondProfile) {
        Similarity match = match(firstProfile, secondProfile);
        return match.satisfied();
    }

    public Similarity match(Profile firstProfile, Profile secondProfile) {
        if (!perfectMatchName(firstProfile, secondProfile)) return new Similarity(0);

        int total = firstProfile.totalFields();
        int similarity = firstProfile.compare(secondProfile);
        return new Similarity(getSimilarityPercentage(similarity, total));
    }

    private boolean perfectMatchName(Profile firstProfile, Profile secondProfile) {
        if (isNullObject(firstProfile)) {
            return false;
        }

        if (isNullObject(secondProfile)) {
            return false;
        }

        String firstCustomerName = firstProfile.getIndividual().getName();
        String secondCustomerName = secondProfile.getIndividual().getName();
        if (firstCustomerName != null && secondCustomerName != null) {
            if (firstCustomerName.equalsIgnoreCase(secondCustomerName)) {
                return true;
            }
        }
        return false;
    }

    private boolean isNullObject(Profile firstProfile) {
        return firstProfile == null || firstProfile.getIndividual() == null;
    }

    private int getSimilarityPercentage(int similarity, int total) {
        if (total > 0) {
            return 100 * similarity / total;
        }
        return 0;
    }
}
