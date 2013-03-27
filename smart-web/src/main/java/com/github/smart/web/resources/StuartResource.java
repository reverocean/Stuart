package com.github.smart.web.resources;

import com.github.smart.web.views.IndexView;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/index.html")
@Produces(MediaType.TEXT_HTML)
public class StuartResource {
    @GET
    public IndexView index() {
        return new IndexView();
    }
}
