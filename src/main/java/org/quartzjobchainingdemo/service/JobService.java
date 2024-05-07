package org.quartzjobchainingdemo.service;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobListener;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.listeners.JobChainingJobListener;
import org.quartzjobchainingdemo.job.EnterBuildingJob;
import org.quartzjobchainingdemo.job.TurnOnPcJob;
import org.quartzjobchainingdemo.job.dto.JobWrapper;
import org.springframework.stereotype.Service;

@Service
public class JobService {

    private int runCount = 0;
    private final Scheduler scheduler;
    JobChainingJobListener chainingJobListener = new JobChainingJobListener("asdf");

    public JobService(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public void executeJob() throws SchedulerException {

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("A cool trigger")
                //.startNow()
                .build();

        runCount++;
        JobDetail enteringJob = JobBuilder.newJob(EnterBuildingJob.class)
                .withDescription("Hacker enters the building")
                .withIdentity("Entering " + runCount)
                .build();

        JobDetail turningOnJob = JobBuilder.newJob(TurnOnPcJob.class)
                .withDescription("Hacker turns PC on")
                .withIdentity("Turning on " + runCount)
                .storeDurably()
                .build();

        scheduler.scheduleJob(enteringJob, trigger);
        scheduler.addJob(turningOnJob, true);

        chainingJobListener.addJobChainLink(enteringJob.getKey(), turningOnJob.getKey());
        scheduler.getListenerManager().addJobListener(chainingJobListener);
        scheduler.start();
    }
}
