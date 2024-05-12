package org.quartzjobchainingdemo.config;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartzjobchainingdemo.job.listener.DefaultJobListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import static org.quartz.impl.matchers.EverythingMatcher.allJobs;

@Configuration
public class SchedulerConfig {

    @Bean
    public Scheduler scheduler(SchedulerFactoryBean factory) throws SchedulerException {
        Scheduler scheduler = factory.getScheduler();
        scheduler.getListenerManager().addJobListener(new DefaultJobListener(), allJobs());
        return scheduler;
    }
}
