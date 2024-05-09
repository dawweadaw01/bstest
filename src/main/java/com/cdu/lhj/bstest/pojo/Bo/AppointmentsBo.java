package com.cdu.lhj.bstest.pojo.Bo;


import lombok.Data;

@Data
public class AppointmentsBo {
    private Integer page;
    private Integer size;
    private Long userId;
    private Long orderStatus;
}
