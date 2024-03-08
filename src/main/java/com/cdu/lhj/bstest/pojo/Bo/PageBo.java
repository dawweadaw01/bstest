package com.cdu.lhj.bstest.pojo.Bo;

import lombok.Data;

@Data
public class PageBo {
    private Integer page;
    private Integer size;
    private String keyword;
    private String order;
}
