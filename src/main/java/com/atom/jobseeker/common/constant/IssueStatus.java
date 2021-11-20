package com.atom.jobseeker.common.constant;

import java.util.HashMap;

public class IssueStatus extends HashMap<Short, String> {

    public IssueStatus(){
        put((short) 0, "待审核");
        put((short) 1, "通过");
        put((short) 2, "未通过");
    }

    public static String getStatus(Short code){
        return new IssueStatus().get(code);
    }

}
