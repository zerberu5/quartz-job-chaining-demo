package org.quartzjobchainingdemo.job.listener;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.quartzjobchainingdemo.job.CmisAdapterResponse;
import org.springframework.http.ResponseEntity;

@Slf4j
public class DefaultJobListener implements JobListener {

    private final String name = "DefaultJobListener";

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
    @SneakyThrows
    public void jobWasExecuted(JobExecutionContext jobExecutionContext, JobExecutionException jobException) {

        ResponseEntity<CmisAdapterResponse> result = (ResponseEntity<CmisAdapterResponse>) jobExecutionContext.getResult();
        log.info("EXECUTED LIFECYCLE: " + jobExecutionContext.getJobDetail().getDescription() + " result:" + result.getStatusCode());
    }
}
