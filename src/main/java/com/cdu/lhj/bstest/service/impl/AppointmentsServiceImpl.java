package com.cdu.lhj.bstest.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cdu.lhj.bstest.mapper.AppointmentsMapper;
import com.cdu.lhj.bstest.pojo.Appointments;
import com.cdu.lhj.bstest.service.AppointmentsService;
import com.cdu.lhj.bstest.util.SimpleTimestampIdGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class AppointmentsServiceImpl extends ServiceImpl<AppointmentsMapper, Appointments> implements AppointmentsService{

    @Override
    @Transactional
    public Appointments insertAppointments(Appointments appointments) {
        //转换时间
        Date parse = DateUtil.date(Long.parseLong(appointments.getAppointmentTimeStr()));
        appointments.setAppointmentTime(parse);
        //设置id
        appointments.setId(SimpleTimestampIdGenerator.nextId());
        boolean save = save(appointments);
        if(!save){
            throw new RuntimeException("插入失败");
        }
        return appointments;
    }

    @Override
    @Transactional
    public boolean updateAppointmentsStatus(Long orderId) {
        LambdaUpdateWrapper<Appointments> set = new LambdaUpdateWrapper<Appointments>().eq(Appointments::getId, orderId).set(Appointments::getOrderStatus, 1L);
        // 实现重试机制
        for (int i = 0; i < 3; i++) {
            boolean update = update(set);
            if(update){
                return true;
            }
        }
        return false;
    }

}
