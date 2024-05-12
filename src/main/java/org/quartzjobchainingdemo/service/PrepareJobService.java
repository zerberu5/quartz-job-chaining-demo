package org.quartzjobchainingdemo.service;


import org.quartzjobchainingdemo.job.DokumentAblegenJob;
import org.quartzjobchainingdemo.job.dto.CmisAdapterJobWrapper;
import org.quartzjobchainingdemo.job.dto.CommonDataModel;
import org.quartzjobchainingdemo.job.dto.Dokument;

public class PrepareJobService {

    public static int identityCount = 0;

    public static CmisAdapterJobWrapper prepareJobWrapper(CommonDataModel commonDataModel, Dokument dokument) {

        return CmisAdapterJobWrapper.builder()
                .identity("identity dokumentAblegen" + ++identityCount)
                .group("CMIS_JOB")
                .job(DokumentAblegenJob.class)
                .data("dokument", dokument)
                .data("vorgang", commonDataModel.getVorgang())
                .data("akte", commonDataModel.getAkte())
                .build();
    }
}
