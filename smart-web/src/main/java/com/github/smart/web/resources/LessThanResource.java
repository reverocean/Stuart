package com.github.smart.web.resources;

import com.github.smart.web.domains.Customer;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * User: Haiyang
 * Date: 3/27/13
 * Time: 6:05 PM
 */

@Path("/lessThan/{limit}")
@Produces(MediaType.APPLICATION_JSON)
public class LessThanResource {
    @GET
    public List<Customer> getCustomers(@PathParam("limit") int limit) {
        return newArrayList(
                new Customer(1, "Jonathan Colleman", newArrayList("SUNCORP", "GIO", "AAMI")),
                new Customer(2, "Michael P", newArrayList("SUNCORP", "GIO")),
                new Customer(3, "Rebecca C", newArrayList("SUNCORP", "AAMI"))
        );
    }
}
