package com.github.smart.recommendation;

import com.github.smart.persistence.RecommendationPersistence;
import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class RecommendationService {
    private RecommendationPersistence persistence;

    public RecommendationService(RecommendationPersistence persistence) {
        this.persistence = persistence;
    }

    public List<String> recommendBrands(String customerId, int limit) {
        List<BrandSimilarity> brandSimilarities = getBrandSimilarities(persistence.findCustomerBrands(customerId), persistence.retrieveBrands());
        return FluentIterable.from(brandSimilarities).transform(toBrand()).limit(limit).toList();
    }

    private List<BrandSimilarity> getBrandSimilarities(List<String> customerBrands, List<String> allBrands) {
        List<BrandSimilarity> brandSimilarities = newArrayList();

        for (String brand : allBrands) {
            if (!customerBrands.contains(brand)) {
                double similarity = 0;
                for (String customerBrand : customerBrands) {
                    similarity += persistence.retrieveSimilarity(customerBrand, brand);
                }
                brandSimilarities.add(new BrandSimilarity(brand, similarity));
            }
        }

        Collections.sort(brandSimilarities, compareSimilarity());
        return brandSimilarities;
    }

    private Function<BrandSimilarity, String> toBrand() {
        return new Function<BrandSimilarity, String>() {
            @Override
            public String apply(BrandSimilarity input) {
                return input.getBrand();
            }
        };
    }

    private Comparator<BrandSimilarity> compareSimilarity() {
        return new Comparator<BrandSimilarity>() {
            @Override
            public int compare(BrandSimilarity thisBrandSimilarity, BrandSimilarity thatBrandSimilarity) {
                if (thisBrandSimilarity.getSimilarity() > thatBrandSimilarity.getSimilarity()) {
                    return -1;
                }

                if (thisBrandSimilarity.getSimilarity() < thatBrandSimilarity.getSimilarity()) {
                    return 1;
                }

                return 0;
            }
        };
    }

    private static class BrandSimilarity {
        private String brand;
        private double similarity;

        private BrandSimilarity(String brand, double similarity) {
            this.brand = brand;
            this.similarity = similarity;
        }

        public String getBrand() {
            return brand;
        }

        public double getSimilarity() {
            return similarity;
        }
    }
}
