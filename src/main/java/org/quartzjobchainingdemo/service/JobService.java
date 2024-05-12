package org.quartzjobchainingdemo.service;

import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.listeners.JobChainingJobListener;
import org.quartzjobchainingdemo.job.cmis.VorgangSuchenJob;
import org.quartzjobchainingdemo.job.dto.CmisAdapterJobWrapper;
import org.quartzjobchainingdemo.job.dto.CommonDataModel;
import org.quartzjobchainingdemo.job.dto.Vorgang;
import org.springframework.stereotype.Service;

@Service
public class JobService {

    private int runCount = 0;
    private final Scheduler scheduler;

    public JobService(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public void executeDynamicVerakten(CommonDataModel commonDataModel) throws SchedulerException {
        runCount++;

        JobDetail jobDetail = JobBuilder.newJob(VorgangSuchenJob.class)
                .withIdentity("vorgangSuchenJob")
                .storeDurably()
                .build();

        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("commonDataModel", commonDataModel);

        jobDetail.getJobDataMap().putAll(jobDataMap);

        Trigger trigger = TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .startNow()
                .build();

        scheduler.scheduleJob(jobDetail, trigger);
    }
}
