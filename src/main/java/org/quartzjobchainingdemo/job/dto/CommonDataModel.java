package org.quartzjobchainingdemo.job.dto;

import lombok.Data;

import java.util.List;

@Data
public class CommonDataModel {

    private Vorgang vorgang;
    private Akte akte;
    private List<Dokument> dokumente;
}
