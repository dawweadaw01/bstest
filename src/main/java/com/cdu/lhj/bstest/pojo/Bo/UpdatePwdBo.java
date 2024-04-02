package com.cdu.lhj.bstest.pojo.Bo;

import lombok.Data;

@Data
public class UpdatePwdBo {
    private Long id;
    private String newPwd;
    private String oldPwd;
}
