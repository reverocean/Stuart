package com.github.smart.recommendation;

import com.github.smart.recommendation.persistence.RecommendationPersistence;

import java.util.List;

public class RecommendationTask {
    private RecommendationPersistence persistence;
    private SimilarityCalculator calculator;

    public RecommendationTask(RecommendationPersistence persistence) {
        this.persistence = persistence;
    }

    public void run() {
        List<String> brands = persistence.retrieveBrands();
        for (String thisBrand : brands) {
            for (String thatBrand : brands) {
                if (!thisBrand.equalsIgnoreCase(thatBrand)) {
                    double similarity = calculator.calculate(thisBrand, thatBrand);
                    this.persistence.saveSimilarity(thisBrand, thatBrand, similarity);
                }
            }
        }
    }
}
