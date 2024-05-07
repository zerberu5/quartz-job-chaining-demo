package org.quartzjobchainingdemo.job.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import org.quartz.Job;

import java.util.Map;

@Builder
@Data
public class JobWrapper {

    private Class<? extends Job> job;
    private String identity;
    private String group;

    @Singular("data")
    private Map<String, Object> data;
}
