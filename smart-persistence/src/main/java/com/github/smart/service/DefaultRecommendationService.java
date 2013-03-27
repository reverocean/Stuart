package com.github.smart.service;

import java.util.List;

public class DefaultRecommendationService implements RecommendationService
{
    @Override
    public List<String> retrieveBrands() {
        return null;
    }

    @Override
    public void saveSimilarity(String thisBrand, String thatBrand, double similarity) {
        
    }

    @Override
    public int countBothBrands(String thisBrand, String thatBrand) {
        return 1;
    }

    @Override
    public int countEitherBrand(String thisBrand, String thatBrand) {
        return 1;
    }

    @Override
    public List<String> findCustomerBrands(String customerId) {
        return null;
    }

    @Override
    public double retrieveSimilarity(String customerBrand, String brand) {
        return 0.;
    }
}
