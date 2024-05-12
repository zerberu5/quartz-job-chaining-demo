package org.quartzjobchainingdemo.service.cmis;

import lombok.extern.slf4j.Slf4j;
import org.quartzjobchainingdemo.job.CmisAdapterResponse;
import org.quartzjobchainingdemo.job.dto.Dokument;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DokumentService {

    public ResponseEntity<CmisAdapterResponse> dokumentAblegen(Dokument dokument) {
        String result = "Dokument " + dokument.getDokumentenId() + " wurde im DokumentService abgelegt";
        log.info(result);
        return ResponseEntity.ok(new CmisAdapterResponse(result));
    }
}
