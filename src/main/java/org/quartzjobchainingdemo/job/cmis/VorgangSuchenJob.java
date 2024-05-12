package org.quartzjobchainingdemo.job.cmis;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartzjobchainingdemo.job.dto.CommonDataModel;
import org.quartzjobchainingdemo.service.cmis.VorgangService;

public class VorgangSuchenJob implements Job {

    private final VorgangService vorgangService;

    public VorgangSuchenJob(VorgangService vorgangService) {
        this.vorgangService = vorgangService;
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
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
