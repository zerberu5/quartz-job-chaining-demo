package org.quartzjobchainingdemo.job.listener;

import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartzjobchainingdemo.job.cmis.AkteSuchenJob;
import org.quartzjobchainingdemo.job.cmis.DokumentAblegenJob;

public class ConditionalChainingListener implements JobListener {

    @Override
    public String getName() {
        return "conditionalChainListener";
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext context) {

    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {

    }

    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        JobDataMap dataMap = context.getJobDetail().getJobDataMap();
        boolean vorgangFound = dataMap.getBoolean("vorgangFound");

        JobDetail nextJobDetail;
        if (vorgangFound) {
            nextJobDetail = JobBuilder.newJob(DokumentAblegenJob.class)
                    .withIdentity("dokumentAblegenJob")
                    .build();
        } else {
            nextJobDetail = JobBuilder.newJob(AkteSuchenJob.class)
                    .withIdentity("akteSuchenJob")
                    .build();
        }

        Trigger trigger = TriggerBuilder.newTrigger().startNow().build();

        try {
            context.getScheduler().scheduleJob(nextJobDetail, trigger);
        } catch (SchedulerException e) {
            System.err.println("Failed to schedule conditional job.");
        }
    }
}

