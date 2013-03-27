package com.github.smart.recommendation;

import com.github.smart.recommendation.persistence.RecommendationPersistence;

public class SimilarityCalculator {
    private RecommendationPersistence persistence;

    public SimilarityCalculator(RecommendationPersistence persistence) {
        this.persistence = persistence;
    }

    public double calculate(String thisBrand, String thatBrand) {
        int eitherBrandCount = persistence.countEitherBrand(thisBrand, thatBrand);
        if (eitherBrandCount == 0) {
            return 0;
        }

        int bothBrandsCount = persistence.countBothBrands(thisBrand, thatBrand);
        return ((double)bothBrandsCount)/((double)eitherBrandCount);
    }
}
