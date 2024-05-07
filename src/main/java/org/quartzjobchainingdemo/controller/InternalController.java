package org.quartzjobchainingdemo.controller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.quartz.SchedulerException;
import org.quartzjobchainingdemo.service.JobService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/job")
    public ResponseEntity<String> produceActiveMqMessage() {

        try {
            jobService.executeJob();
        } catch (SchedulerException e) {
            log.error("Something went wrong with the scheduler");
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body("Madig");
        }

        return ResponseEntity.ok("Erfolgreich");
    }
}
