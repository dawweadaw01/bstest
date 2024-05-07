package com.cdu.lhj.bstest.controller;


import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.cdu.lhj.bstest.pojo.Appointments;
import com.cdu.lhj.bstest.service.AppointmentsService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentsController {
    @Resource
    private AppointmentsService appointmentsService;

    @PostMapping("/insertAppointments")
    public SaResult insertAppointments(@RequestBody Appointments appointments) {
        try {
            // 拿到登录用户的id
            long loginIdAsLong = StpUtil.getLoginIdAsLong();
            appointments.setUserId(loginIdAsLong);
            appointments.setOrderStatus(0L);
            Appointments appointments1 = appointmentsService.insertAppointments(appointments);
            return SaResult.data(appointments1);
        }catch (Exception e){
            return SaResult.error(e.getMessage());
        }
    }
}
