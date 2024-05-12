package org.quartzjobchainingdemo.config;

import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.KeyMatcher;
import org.quartz.listeners.JobChainingJobListener;
import org.quartzjobchainingdemo.job.listener.DefaultJobListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import static org.quartz.impl.matchers.EverythingMatcher.allJobs;

@Configuration
public class SchedulerConfig {

    @Bean
    public Scheduler scheduler() throws SchedulerException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        JobChainingJobListener chainingListener = new JobChainingJobListener("documentChain");
        chainingListener.addJobChainLink(JobKey.jobKey("vorgangSuchenJob"), JobKey.jobKey("dokumentAblegenJob"));
        chainingListener.addJobChainLink(JobKey.jobKey("vorgangSuchenJob"), JobKey.jobKey("akteSuchenJob"));

        scheduler.getListenerManager().addJobListener(chainingListener, KeyMatcher.keyEquals(JobKey.jobKey("vorgangSuchenJob")));

        return scheduler;
    }
}
