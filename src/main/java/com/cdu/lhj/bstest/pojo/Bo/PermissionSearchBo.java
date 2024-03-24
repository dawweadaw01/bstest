package com.cdu.lhj.bstest.pojo.Bo;

import lombok.Data;

@Data
public class PermissionSearchBo {
    private String name;
    private String description;
    private String url;
    private String method;
    private Integer page;
    private Integer size;
}
