package com.github.smart.web;

import com.bazaarvoice.dropwizard.assets.ConfiguredAssetsBundle;
import com.github.smart.web.config.StuartAssetsConfiguration;
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
public class StuartMain extends Service<StuartAssetsConfiguration> {
    public static void main(String[] args) throws Exception {
        new StuartMain().run(new String[]{"server"});
    }

    @Override
    public void initialize(Bootstrap<StuartAssetsConfiguration> bootstrap) {
        bootstrap.setName("Stuart");
        bootstrap.addBundle(new ViewBundle());
        bootstrap.addBundle(new ConfiguredAssetsBundle("/assets/", "/assets/"));
    }

    @Override
    public void run(StuartAssetsConfiguration configuration, Environment environment) throws Exception {
        environment.addResource(new StuartResource());

    }
}
