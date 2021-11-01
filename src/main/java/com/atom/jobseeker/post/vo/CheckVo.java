package com.atom.jobseeker.post.vo;

import com.atom.jobseeker.search.es.JobEs;
import lombok.Data;

/**
 * @author wayan
 */
@Data
public class CheckVo {
    private Long[] ids;
    private String status;
}
