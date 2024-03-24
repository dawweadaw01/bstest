package com.cdu.lhj.bstest.pojo.Bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleSearchBo {
    private String name;
    private String description;
    private Integer page;
    private Integer size;
}
