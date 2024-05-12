package org.quartzjobchainingdemo.job.cmis;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartzjobchainingdemo.job.dto.CommonDataModel;
import org.quartzjobchainingdemo.service.cmis.VorgangService;

@Slf4j
public class VorgangSuchenJob implements Job {

    private final VorgangService vorgangService;

    public VorgangSuchenJob(VorgangService vorgangService) {
        this.vorgangService = vorgangService;
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.info("in vorgang suchen job");
        try {
            CommonDataModel model = (CommonDataModel) context.getJobDetail().getJobDataMap().get("commonDataModel");
            boolean vorgangFound = vorgangService.vorgangSuchen(model.getVorgang());

            JobDataMap dataMap = context.getJobDetail().getJobDataMap();
            dataMap.put("vorgangFound", vorgangFound);
        } catch (Exception e) {
            throw new JobExecutionException(e);
        }
    }
}
