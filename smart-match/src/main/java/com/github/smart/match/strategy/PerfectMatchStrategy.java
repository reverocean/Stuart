package com.github.smart.match.strategy;

import com.github.smart.domain.Profile;
import com.github.smart.match.Similarity;
import com.github.smart.domain.Customer;

public class PerfectMatchStrategy implements MatchStrategy {
    @Override
    public Similarity match(Profile firstProfile, Profile secondProfile) {
        int total = firstProfile.totalFields();
        int similarity = firstProfile.compare(secondProfile);
        return new Similarity(getSimilarityPercentage(similarity, total));
    }

    private int getSimilarityPercentage(int similarity, int total) {
        if (total > 0) {
            return 100 * similarity / total;
        }
        return 0;
    }
}
