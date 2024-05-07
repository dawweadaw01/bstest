package com.cdu.lhj.bstest.service;

import com.cdu.lhj.bstest.pojo.Appointments;
import com.baomidou.mybatisplus.extension.service.IService;
public interface AppointmentsService extends IService<Appointments>{


    Appointments insertAppointments(Appointments appointments);

    boolean updateAppointmentsStatus(Long orderId);
}
