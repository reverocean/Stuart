package com.github.smart.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface RecommendationService
{
    List<String> retrieveBrands();

    void saveSimilarity(String thisBrand, String thatBrand, double similarity);

    int countBothBrands(String thisBrand, String thatBrand);

    int countEitherBrand(String thisBrand, String thatBrand);

    Set<String> findCustomerBrands(int customerId);

    Double retrieveSimilarity(String thisBrand, String thatBrand);
}
