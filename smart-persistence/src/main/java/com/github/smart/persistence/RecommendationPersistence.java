package com.github.smart.persistence;

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

    public List<String> findCustomerBrands(String customerId) {
        return null;
    }

    public double retrieveSimilarity(String customerBrand, String brand) {
        return 0.;
    }
}
