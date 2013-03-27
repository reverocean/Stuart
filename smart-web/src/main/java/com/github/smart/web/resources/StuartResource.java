package com.github.smart.web.resources;

import com.github.smart.web.domains.Saying;
import com.github.smart.web.views.IndexView;
import com.google.common.base.Optional;
import com.yammer.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicLong;

/**
 * User: Haiyang
 * Date: 3/27/13
 * Time: 3:26 PM
 */
@Path("/index.html")
@Produces(MediaType.TEXT_HTML)
public class StuartResource {
    @GET
    public IndexView index() {
        return new IndexView();
    }
}
