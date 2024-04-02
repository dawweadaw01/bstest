package com.cdu.lhj.bstest.pojo.Bo;

import lombok.Data;

@Data
public class CatCategoriesBo {
    private Long categoryId;
    private Integer page;
    private Integer size;
    private String name;
    private String description;
    private String origin;
}
