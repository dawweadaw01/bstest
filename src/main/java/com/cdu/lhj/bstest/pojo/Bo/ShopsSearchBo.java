package com.cdu.lhj.bstest.pojo.Bo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopsSearchBo {
    private String keyword;
    private Integer page;
    private Integer size;
}
