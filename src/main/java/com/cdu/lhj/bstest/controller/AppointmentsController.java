package com.cdu.lhj.bstest.controller;


import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.cdu.lhj.bstest.config.DelayQueueConfig;
import com.cdu.lhj.bstest.pojo.Appointments;
import com.cdu.lhj.bstest.pojo.Bo.AppointmentsBo;
import com.cdu.lhj.bstest.service.AppointmentsService;
import com.cdu.lhj.bstest.service.SendMsgService;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentsController {
    private static final Logger log = LoggerFactory.getLogger(AppointmentsController.class);
    @Resource
    private AppointmentsService appointmentsService;

    @Resource
    private SendMsgService sendMsgService;

    @PostMapping("/insertAppointments")
    public SaResult insertAppointments(@RequestBody Appointments appointments) {
        try {
            // 拿到登录用户的id
            long loginIdAsLong = StpUtil.getLoginIdAsLong();
            appointments.setUserId(loginIdAsLong);
            appointments.setOrderStatus(0L);
            Appointments appointments1 = appointmentsService.insertAppointments(appointments);
            // 发送延迟取消订单消息
            sendMsgService.sendMsg(appointments1.getId(), 1000*60*10);
            return SaResult.data(appointments1);
        } catch (Exception e) {
            return SaResult.error(e.getMessage());
        }
    }

    @PostMapping("/getAppointmentsByUserIdAndStatus")
    public SaResult getAppointmentsByUserIdAndStatus(@RequestBody AppointmentsBo appointmentsBo) {
        try {
            long loginIdAsLong = StpUtil.getLoginIdAsLong();
            appointmentsBo.setUserId(loginIdAsLong);
            return SaResult.data(appointmentsService.getAppointmentsByUserIdAndStatus(appointmentsBo));
        } catch (Exception e) {
            return SaResult.error(e.getMessage());
        }
    }

    @PostMapping("/cancelAppointment")
    public SaResult cancelAppointment(@RequestParam Long id) {
        try{
            //拿到登录用户的id
            Long loginIdAsLong = StpUtil.getLoginIdAsLong();
            // 查询订单是否属于当前用户
            if(appointmentsService.getAppointmentsByUserIdAndId(loginIdAsLong, id)==null){
                return SaResult.error("订单不属于当前用户");
            }
            return SaResult.data(appointmentsService.updateAppointmentsStatus(id, 3L));
        }catch (Exception e){
            return SaResult.error(e.getMessage());
        }
    }

    @RabbitListener(queues = DelayQueueConfig.DELAYED_QUEUE_NAME)
    public void cancelOrder(Message msg) {
        log.info("收到消息:{},开始处理超时取消", msg);
        // 强转为Long
        byte[] body = msg.getBody();
        Long id = Long.valueOf(new String(body));
        Appointments appointments = appointmentsService.getById(id);
        if (appointments.getOrderStatus() == 0) {
            appointments.setOrderStatus(3L);
            appointmentsService.updateAppointmentsStatus(id, 3L);
        }
    }
}
