package com.cdu.lhj.bstest.pojo.Bo;

import lombok.Data;

@Data
public class CatDeBo {
    private Long catId;
    private String name;
    private Integer age;
    private Integer healthStatusCode;
    private Boolean availableForAdoption;
    private Long shopId;
    private Long categoryId;
    private Integer size;
    private Integer page;
}
