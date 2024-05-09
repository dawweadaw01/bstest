package com.cdu.lhj.bstest.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cdu.lhj.bstest.pojo.Appointments;
import com.cdu.lhj.bstest.pojo.Bo.AppointmentsBo;

public interface AppointmentsService extends IService<Appointments>{


    Appointments insertAppointments(Appointments appointments);

    boolean updateAppointmentsStatus(Long orderId,Long status);

    IPage<Appointments> getAppointmentsByUserIdAndStatus(AppointmentsBo appointmentsBo);

    Appointments getAppointmentsByUserIdAndId(Long loginIdAsLong, Long id);
}
