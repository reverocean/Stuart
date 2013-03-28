package com.github.smart.web.resources;

import com.github.smart.domain.Customer;
import com.github.smart.domain.Profile;
import com.github.smart.service.LessThanService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

import static com.google.common.collect.ImmutableSet.of;

@Path("/lessThan/{limit}")
@Produces(MediaType.APPLICATION_JSON)
public class LessThanResource {
    private LessThanService service;

    public LessThanResource(LessThanService service) {
        this.service = service;
    }

    @GET
    public List<Customer> getCustomers(@PathParam("limit") int limit) {
        return service.getLessBrandsCustomers(limit);
    }

    private Customer createCustomer(int id, String name) {
        Customer customer = new Customer();
        customer.setId(id);
        customer.setName(name);
        customer.setProfiles(of(createProfile("SUNCORP"), createProfile("GIO"), createProfile("AAMI")));
        return customer;
    }

    private Profile createProfile(String brand) {
        Profile profile = new Profile();
        profile.setBrand(brand);
        return profile;
    }
}
