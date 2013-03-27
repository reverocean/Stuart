package com.github.smart.recommendation;

import com.github.smart.service.RecommendationService;

public class SimilarityCalculator {
    private RecommendationService service;

    public SimilarityCalculator(com.github.smart.service.RecommendationService service) {
        this.service = service;
    }

    public double calculate(String thisBrand, String thatBrand) {
        int eitherBrandCount = service.countEitherBrand(thisBrand, thatBrand);
        if (eitherBrandCount == 0) {
            return 0;
        }

        int bothBrandsCount = service.countBothBrands(thisBrand, thatBrand);
        return ((double)bothBrandsCount)/((double)eitherBrandCount);
    }
}
