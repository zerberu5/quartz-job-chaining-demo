package org.quartzjobchainingdemo.job.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CmisJobDataContainer {

    private String requestId;
    private CommonDataModel commonDataModel;

}
