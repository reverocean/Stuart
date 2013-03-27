package com.github.smart.web.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@Path("/recommend/{id}")
@Produces(MediaType.APPLICATION_JSON)
public class RecommendResource {
    @GET
    public List<String> getCustomers(@PathParam("id") int id) {
        return newArrayList("CTP", "BINGO");
    }
}
