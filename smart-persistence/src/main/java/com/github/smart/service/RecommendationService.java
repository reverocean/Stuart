package com.github.smart.service;

import java.util.List;

public interface RecommendationService
{
    List<String> retrieveBrands();

    void saveSimilarity(String thisBrand, String thatBrand, double similarity);

    int countBothBrands(String thisBrand, String thatBrand);

    int countEitherBrand(String thisBrand, String thatBrand);

    List<String> findCustomerBrands(String customerId);

    Double retrieveSimilarity(String thisBrand, String thatBrand);
}
