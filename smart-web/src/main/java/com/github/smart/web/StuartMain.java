package com.github.smart.web;

import com.github.smart.web.config.StuartConfiguration;
import com.github.smart.web.resources.StuartResource;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.views.ViewBundle;

/**
 * User: Haiyang
 * Date: 3/27/13
 * Time: 3:05 PM
 */
public class StuartMain extends Service<StuartConfiguration> {
    public static void main(String[] args) throws Exception {
        new StuartMain().run(new String[]{"server"});
    }

    @Override
    public void initialize(Bootstrap<StuartConfiguration> bootstrap) {
        bootstrap.setName("Stuart");
        bootstrap.addBundle(new ViewBundle());
    }

    @Override
    public void run(StuartConfiguration configuration, Environment environment) throws Exception {
        environment.addResource(new StuartResource());

    }
}
