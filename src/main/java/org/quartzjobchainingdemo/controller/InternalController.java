package org.quartzjobchainingdemo.controller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.quartz.SchedulerException;
import org.quartzjobchainingdemo.job.dto.CmisAdapterJobWrapper;
import org.quartzjobchainingdemo.job.dto.CommonDataModel;
import org.quartzjobchainingdemo.job.dto.Dokument;
import org.quartzjobchainingdemo.service.JobService;
import org.quartzjobchainingdemo.service.PrepareJobService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Tag(name = "Active MQ Test", description = "just for fun")
@RestController
@OpenAPIDefinition(info = @Info(title = "ActiveMqProducer", description = "Produces ActiveMq-Queue and puts message given over controller to it"))
@RequestMapping("/api")
@Slf4j
@EnableWebMvc
public class InternalController {

    private final JobService jobService;

    public InternalController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping("/ablegen")
    public ResponseEntity<String> produceActiveMqMessage(@RequestBody CommonDataModel commonDataModel) {

        for (Dokument dokument : commonDataModel.getDokumente()) {

            CmisAdapterJobWrapper vorgangSuchenWrapper = PrepareJobService.prepareJobWrapper(commonDataModel, dokument);
            try {
                jobService.executeDokumentAblegen(vorgangSuchenWrapper);
            } catch (SchedulerException e) {
                throw new RuntimeException(e);
            }
        }

        return ResponseEntity.ok("Erfolgreich");
    }
}
