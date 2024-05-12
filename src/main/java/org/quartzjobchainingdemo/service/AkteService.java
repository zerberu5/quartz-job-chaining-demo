package org.quartzjobchainingdemo.service;

import lombok.extern.slf4j.Slf4j;
import org.quartzjobchainingdemo.job.dto.Akte;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AkteService {

    public boolean akteSuchen(Akte akte) {
        log.info("Edgecase vorerst nicht beachten");
        return true;
    }

}
