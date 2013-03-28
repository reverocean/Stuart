package com.github.smart.web;

import com.bazaarvoice.dropwizard.assets.ConfiguredAssetsBundle;
import com.github.smart.domain.Customer;
import com.github.smart.domain.Profile;
import com.github.smart.match.job.CustomerWriter;
import com.github.smart.recommendation.RecommendationService;
import com.github.smart.recommendation.RecommendationTask;
import com.github.smart.recommendation.SimilarityCalculator;
import com.github.smart.service.DefaultLessThanService;
import com.github.smart.service.DefaultRecommendationService;
import com.github.smart.web.config.StuartConfiguration;
import com.github.smart.web.resources.*;
import com.google.common.collect.ImmutableList;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.db.DatabaseConfiguration;
import com.yammer.dropwizard.hibernate.HibernateBundle;
import com.yammer.dropwizard.views.ViewBundle;
import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.SimpleJob;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.core.step.item.ChunkOrientedTasklet;
import org.springframework.batch.core.step.item.SimpleChunkProcessor;
import org.springframework.batch.core.step.item.SimpleChunkProvider;
import org.springframework.batch.core.step.tasklet.TaskletStep;
import org.springframework.batch.item.database.HibernateCursorItemReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

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

        ApplicationContext context = new ClassPathXmlApplicationContext(
                "hibernateApplicationContext.xml",
                "dataServiceApplicationContext.xml",
                "matchSercieApplicationContext.xml",
                "matchJobApplicationContext.xml");


//        environment.addResource(new LessThanResource(new DefaultLessThanService(hibernate.getSessionFactory())));
//        DefaultRecommendationService daoRecommendationService = createDaoRecommendationService();
//        environment.addResource(createRecommendationResource(daoRecommendationService));
//        environment.addResource(new RecommendResource());
//
//        environment.addResource(createMatchJobResource(configuration));
//        environment.addResource(createRecommendationJobResource(daoRecommendationService));
    }

    private RecommendationJobResource createRecommendationJobResource(DefaultRecommendationService daoRecommendationService) {
        RecommendationTask task = new RecommendationTask(daoRecommendationService, new SimilarityCalculator(daoRecommendationService));
        return new RecommendationJobResource(task);
    }

    private MatchJobResource createMatchJobResource(StuartConfiguration configuration) {
        Job job = createJob();
        JobLauncher jobLauncher = createJobLauncher(configuration);
        return new MatchJobResource(jobLauncher, job);


//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("matchJobApplicationContext.xml");
//        JobLauncher jobLauncher = context.getBean("jobLauncher", JobLauncher.class);
//        Job job = context.getBean("matchJob", Job.class);
//        return new MatchJobResource(jobLauncher, job);
    }

    private SimpleJob createJob() {
        SimpleJob job = new SimpleJob();
        job.setName("MatchJob");
        TaskletStep taskletStep = new TaskletStep();
        HibernateCursorItemReader itemReader = new HibernateCursorItemReader();
        itemReader.setSessionFactory(hibernate.getSessionFactory());
        SimpleChunkProvider<Profile> chunkProvider = new SimpleChunkProvider<Profile>(itemReader, null);
        SimpleChunkProcessor<Profile, Profile> chunkProcessor = new SimpleChunkProcessor<Profile, Profile>(null, new CustomerWriter());
        ChunkOrientedTasklet<Profile> tasklet = new ChunkOrientedTasklet<Profile>(chunkProvider, chunkProcessor);
        taskletStep.setTasklet(tasklet);
        Step step = taskletStep;
        job.setSteps(ImmutableList.of(step));
        return job;
    }

    private SimpleJobLauncher createJobLauncher(StuartConfiguration configuration) {
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        JobRepositoryFactoryBean jobRepositoryFactoryBean = new JobRepositoryFactoryBean();
        BasicDataSource dataSource = createDataSource(configuration);
        jobRepositoryFactoryBean.setDataSource(dataSource);
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);
        jobRepositoryFactoryBean.setTransactionManager(transactionManager);

        JobRepository jobRepository = null;
        try {
            jobRepository = (JobRepository)jobRepositoryFactoryBean.getObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        jobLauncher.setJobRepository(jobRepository);
        return jobLauncher;
    }

    private BasicDataSource createDataSource(StuartConfiguration configuration) {
        BasicDataSource dataSource = new BasicDataSource();
        DatabaseConfiguration databaseConfiguration = configuration.getDatabaseConfiguration();
        dataSource.setDriverClassName(databaseConfiguration.getDriverClass());
        dataSource.setUrl(databaseConfiguration.getUrl());
        dataSource.setUsername(databaseConfiguration.getUser());
        dataSource.setPassword(databaseConfiguration.getPassword());
        return dataSource;
    }

    private RecommendationResource createRecommendationResource(DefaultRecommendationService daoService) {
        RecommendationService service = new RecommendationService(daoService);
        return new RecommendationResource(service);
    }

    private DefaultRecommendationService createDaoRecommendationService() {
        DefaultRecommendationService daoService = new DefaultRecommendationService();
        daoService.setSessionFactory(hibernate.getSessionFactory());
        return daoService;
    }
}
