package com.github.smart.web.resources;

import com.github.smart.recommendation.RecommendationService;
import com.google.common.base.Optional;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class RecommendationResource {
    private RecommendationService service;

    public RecommendationResource(RecommendationService service) {
        this.service = service;
    }

    public List<String> recommendBrands(@QueryParam("id") String customerId,
                                        @QueryParam("limit") Optional<Integer> limit) {
        return service.recommendBrands(customerId, limit.or(3));
    }
}
