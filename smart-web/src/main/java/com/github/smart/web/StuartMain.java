package com.github.smart.web;

import com.bazaarvoice.dropwizard.assets.ConfiguredAssetsBundle;
import com.github.smart.domain.Customer;
import com.github.smart.web.config.StuartConfiguration;
import com.github.smart.web.resources.StuartResource;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.db.DatabaseConfiguration;
import com.yammer.dropwizard.hibernate.HibernateBundle;
import com.yammer.dropwizard.views.ViewBundle;

public class StuartMain extends Service<StuartConfiguration> {
    private final HibernateBundle<StuartConfiguration> hibernate = new HibernateBundle<StuartConfiguration>(Customer.class) {
        @Override
        public DatabaseConfiguration getDatabaseConfiguration(StuartConfiguration configuration) {
            return configuration.getDatabaseConfiguration();
        }
    };

    public static void main(String[] args) throws Exception {
        new StuartMain().run(args);
    }

    @Override
    public void initialize(Bootstrap<StuartConfiguration> bootstrap) {
        bootstrap.setName("Stuart");
        bootstrap.addBundle(new ViewBundle());
        bootstrap.addBundle(new ConfiguredAssetsBundle("/assets/", "/assets/"));
        bootstrap.addBundle(hibernate);
    }

    @Override
    public void run(StuartConfiguration configuration, Environment environment) throws Exception {
        environment.addResource(new StuartResource());
    }
}
