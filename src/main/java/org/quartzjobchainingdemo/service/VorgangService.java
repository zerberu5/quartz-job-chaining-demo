package org.quartzjobchainingdemo.service;


import lombok.extern.slf4j.Slf4j;
import org.quartzjobchainingdemo.job.dto.Vorgang;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class VorgangService {

    public boolean vorgangSuchen(Vorgang vorgang) {
        log.info("vorgang mit vorgangszeichen " + vorgang.getVorgangszeichen() + " gefunden");
        return true;
    }

}
