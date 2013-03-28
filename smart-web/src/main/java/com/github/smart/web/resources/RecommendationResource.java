package com.github.smart.web.resources;

import com.github.smart.recommendation.RecommendationService;
import com.google.common.base.Optional;
import com.mysql.jdbc.log.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;


@Path("/recommendFor/{id}")
@Produces(MediaType.APPLICATION_JSON)
public class RecommendationResource {
    private Logger logger = LoggerFactory.getLogger(RecommendationResource.class);
    private RecommendationService service;

    public RecommendationResource(RecommendationService service) {
        this.service = service;
    }

    @GET
    public List<String> recommendBrands(@PathParam("id") String customerId,
                                        @QueryParam("limit") Optional<Integer> limit) {
        logger.info("reommend for {} with limit {}", customerId, limit.or(3));
        return service.recommendBrands(customerId, limit.or(3));
//        return newArrayList("CTP", "BINGO");
    }
}
