package com.cdu.lhj.bstest.pojo.Bo;

import lombok.Data;

@Data
public class UserSearchBo {

    private String username;
    private String phone;
    private Integer page;
    private Integer size;
}
