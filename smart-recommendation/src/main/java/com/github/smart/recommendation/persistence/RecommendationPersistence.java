package com.github.smart.recommendation.persistence;

import java.util.List;

public class RecommendationPersistence {
    public List<String> retrieveBrands() {
        return null;
    }

    public void saveSimilarity(String thisBrand, String thatBrand, double similarity) {
        
    }

    public int countBothBrands(String thisBrand, String thatBrand) {
        return 1;
    }

    public int countEitherBrand(String thisBrand, String thatBrand) {
        return 1;
    }
}
