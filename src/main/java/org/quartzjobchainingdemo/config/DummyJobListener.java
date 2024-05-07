package org.quartzjobchainingdemo.config;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

@Slf4j
public class DummyJobListener implements JobListener {

    private final String name = "DummyListener";

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
        log.info("Wird ausgeführt: " + context.getJobDetail().getDescription());
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {
        log.error("Wird abgelehnt: " + context.getJobDetail().getDescription());

    }

    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        log.info("War erfolgreich: " + context.getJobDetail().getDescription());
    }
}
