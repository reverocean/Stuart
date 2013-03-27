package com.github.smart.web.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/matchjob")
@Produces(MediaType.TEXT_PLAIN)
public class MatchJobResource {
    private Logger logger = LoggerFactory.getLogger(MatchJobResource.class);
    private JobLauncher launcher;
    private final Job job;

    public MatchJobResource(JobLauncher launcher, Job job) {
        this.launcher = launcher;
        this.job = job;
    }

    @GET
    public String matchJob() {
        try {
            launcher.run(this.job, new JobParameters());
            return "Succeed to run job";
        } catch (Exception e) {
            logger.error("fail to run job", e);
            return "Fail to run job";
        }
    }
}
