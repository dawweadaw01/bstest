package com.cdu.lhj.bstest.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cdu.lhj.bstest.mapper.AppointmentsMapper;
import com.cdu.lhj.bstest.pojo.Appointments;
import com.cdu.lhj.bstest.pojo.Bo.AppointmentsBo;
import com.cdu.lhj.bstest.service.AppointmentsService;
import com.cdu.lhj.bstest.service.ShopsService;
import com.cdu.lhj.bstest.util.SimpleTimestampIdGenerator;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class AppointmentsServiceImpl extends ServiceImpl<AppointmentsMapper, Appointments> implements AppointmentsService{

    @Resource
    private ShopsService shopsService;

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
    public boolean updateAppointmentsStatus(Long orderId, Long status) {
        LambdaUpdateWrapper<Appointments> set = new LambdaUpdateWrapper<Appointments>().eq(Appointments::getId, orderId).set(Appointments::getOrderStatus, status);
        // 实现重试机制
        for (int i = 0; i < 3; i++) {
            boolean update = update(set);
            if(update){
                return true;
            }
        }
        return false;
    }

    @Override
    public IPage<Appointments> getAppointmentsByUserIdAndStatus(AppointmentsBo appointmentsBo) {
        if(appointmentsBo.getPage() == null){
            appointmentsBo.setPage(1);
        }
        if(appointmentsBo.getSize() == null){
            appointmentsBo.setSize(10);
        }
        Page<Appointments> page = new Page<>(appointmentsBo.getPage(), appointmentsBo.getSize());
        LambdaUpdateWrapper<Appointments> eq = new LambdaUpdateWrapper<>();
        if(appointmentsBo.getOrderStatus() == null||appointmentsBo.getOrderStatus()==2){
            eq.eq(Appointments::getUserId, appointmentsBo.getUserId());
        }else {
            eq.eq(Appointments::getUserId, appointmentsBo.getUserId()).eq(Appointments::getOrderStatus, appointmentsBo.getOrderStatus());
        }
        Page<Appointments> appointmentsPage = page(page, eq);
        List<Appointments> records = appointmentsPage.getRecords();
        for (Appointments record : records) {
            record.setShops(shopsService.getById(record.getStoreId()));
            // 时间转换
            record.setAppointmentTimeStr(DateUtil.format(record.getAppointmentTime(), "yyyy-MM-dd HH:mm"));
        }
        appointmentsPage.setRecords(records);
        return appointmentsPage;
    }

    @Override
    public Appointments getAppointmentsByUserIdAndId(Long loginIdAsLong, Long id) {
        LambdaUpdateWrapper<Appointments> eq = new LambdaUpdateWrapper<Appointments>().eq(Appointments::getUserId, loginIdAsLong).eq(Appointments::getId, id);
        return getOne(eq);
    }

}
