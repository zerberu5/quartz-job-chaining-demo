package org.quartzjobchainingdemo.config;

import org.quartz.listeners.JobChainingJobListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChainingConfig {

    @Bean
    public JobChainingJobListener jobChainingJobListener(){
        return new JobChainingJobListener("Default Listener");
    }
}
