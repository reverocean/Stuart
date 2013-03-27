package com.github.smart.recommendation;

import com.github.smart.service.RecommendationService;

import java.util.List;

public class RecommendationTask {
    private RecommendationService service;
    private SimilarityCalculator calculator;

    public RecommendationTask(RecommendationService service, SimilarityCalculator calculator) {
        this.service = service;
        this.calculator = calculator;
    }

    public void run() {
        List<String> brands = service.retrieveBrands();
        for (String thisBrand : brands) {
            for (String thatBrand : brands) {
                if (!thisBrand.equalsIgnoreCase(thatBrand)) {
                    double similarity = calculator.calculate(thisBrand, thatBrand);
                    this.service.saveSimilarity(thisBrand, thatBrand, similarity);
                }
            }
        }
    }
}
