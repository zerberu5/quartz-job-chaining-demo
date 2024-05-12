package org.quartzjobchainingdemo.service;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.listeners.JobChainingJobListener;
import org.quartzjobchainingdemo.job.dto.CmisAdapterJobWrapper;
import org.springframework.stereotype.Service;

@Service
public class JobService {

    private int runCount = 0;
    private final Scheduler scheduler;
    private final JobChainingJobListener jobChainingJobListener;

    public JobService(Scheduler scheduler, JobChainingJobListener jobChainingJobListener) {
        this.scheduler = scheduler;
        this.jobChainingJobListener = jobChainingJobListener;
    }

    public void executeDokumentAblegen(CmisAdapterJobWrapper cmisAdapterJobWrapper) throws SchedulerException {
        runCount++;

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("DokumentAblegenJobTrigger" + runCount)
                .startNow()
                .build();


        JobDetail dokumentAblegenDetail = buildDokumentAblegenDetail(cmisAdapterJobWrapper);

        cmisAdapterJobWrapper.getData().forEach((k, v) -> dokumentAblegenDetail.getJobDataMap().put(k, v));

        scheduler.scheduleJob(dokumentAblegenDetail, trigger);
    }

    private JobDetail buildDokumentAblegenDetail(CmisAdapterJobWrapper cmisAdapterJobWrapper) {
        return JobBuilder.newJob(cmisAdapterJobWrapper.getJob())
                .withDescription("Ablegen eines Dokumentes")
                .withIdentity("DokumentAblegen " + runCount)
                .storeDurably()
                .build();
    }
}
