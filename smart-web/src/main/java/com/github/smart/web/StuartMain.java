package com.github.smart.web;

import com.bazaarvoice.dropwizard.assets.ConfiguredAssetsBundle;
import com.github.smart.domain.Customer;
import com.github.smart.recommendation.RecommendationService;
import com.github.smart.service.DefaultLessThanService;
import com.github.smart.service.DefaultRecommendationService;
import com.github.smart.web.config.StuartConfiguration;
import com.github.smart.web.resources.*;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.db.DatabaseConfiguration;
import com.yammer.dropwizard.hibernate.HibernateBundle;
import com.yammer.dropwizard.views.ViewBundle;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
        environment.addResource(new LessThanResource(new DefaultLessThanService(hibernate.getSessionFactory())));
        environment.addResource(createRecommendationResource());
        environment.addResource(new RecommendResource());

//        environment.addResource(createMatchJobResource());
    }

    private MatchJobResource createMatchJobResource() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("matchJobApplicationContext.xml");
        JobLauncher jobLauncher = context.getBean("jobLauncher", JobLauncher.class);
        Job job = context.getBean("matchJob", Job.class);
        return new MatchJobResource(jobLauncher, job);
    }

    private RecommendationResource createRecommendationResource() {
        DefaultRecommendationService daoService = new DefaultRecommendationService();
        daoService.setSessionFactory(hibernate.getSessionFactory());
        RecommendationService service = new RecommendationService(daoService);
        return new RecommendationResource(service);
    }
}
