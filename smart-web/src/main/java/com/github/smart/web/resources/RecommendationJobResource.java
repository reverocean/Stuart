package com.github.smart.web.resources;

import com.github.smart.recommendation.RecommendationTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/recommendationjob")
@Produces(MediaType.TEXT_PLAIN)
public class RecommendationJobResource {
    private Logger logger = LoggerFactory.getLogger(RecommendationJobResource.class);
    private RecommendationTask task;

    public RecommendationJobResource(RecommendationTask task) {
        this.task = task;
    }

    @GET
    public String run() {
        try {
            task.run();
            return "Succeed to run job";
        } catch (Exception e) {
            logger.error("Fail to run job", e);
            return "Fail to run job";
        }
    }
}
