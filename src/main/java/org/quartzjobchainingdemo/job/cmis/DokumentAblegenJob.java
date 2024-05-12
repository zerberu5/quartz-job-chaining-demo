package org.quartzjobchainingdemo.job.cmis;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartzjobchainingdemo.job.CmisAdapterResponse;
import org.quartzjobchainingdemo.job.dto.Dokument;
import org.quartzjobchainingdemo.service.cmis.AkteService;
import org.quartzjobchainingdemo.service.cmis.DokumentService;
import org.quartzjobchainingdemo.service.cmis.VorgangService;
import org.springframework.http.ResponseEntity;

@Slf4j
public class DokumentAblegenJob implements Job {

    private final DokumentService dokumentService;
    private final VorgangService vorgangService;
    private final AkteService akteService;

    public DokumentAblegenJob(DokumentService dokumentService, VorgangService vorgangService, AkteService akteService) {
        this.dokumentService = dokumentService;
        this.vorgangService = vorgangService;
        this.akteService = akteService;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        log.info("Dokument ablegen job wird ausgeführt mit folgender Id");
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        Dokument dokument = (Dokument) jobDataMap.get("dokument");
        ResponseEntity<CmisAdapterResponse> result = dokumentService.dokumentAblegen(dokument);

    }
}
