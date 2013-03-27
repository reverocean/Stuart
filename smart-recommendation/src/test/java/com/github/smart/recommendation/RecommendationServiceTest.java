package com.github.smart.recommendation;

import com.github.smart.recommendation.persistence.RecommendationPersistence;
import org.junit.Test;

import java.util.List;

import static com.google.common.collect.ImmutableList.of;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RecommendationServiceTest {
    @Test
    public void should_recommend_brand() {

        RecommendationPersistence persistence = mock(RecommendationPersistence.class);
        when(persistence.retrieveBrands()).thenReturn(of("REVER", "DREAMHEAD", "TOM"));
        when(persistence.findCustomerBrands("12345")).thenReturn(of("TOM"));
        when(persistence.retrieveSimilarity("TOM", "DREAMHEAD")).thenReturn(0.2);
        when(persistence.retrieveSimilarity("TOM", "REVER")).thenReturn(0.5);

        RecommendationService service = new RecommendationService(persistence);
        List<String> brands = service.recommendBrands("12345", 1);
        assertThat(brands.size(), is(1));
        assertThat(brands.get(0), is("REVER"));
    }
}
