package com.cdu.lhj.bstest.service.impl;

import com.cdu.lhj.bstest.config.DelayQueueConfig;
import com.cdu.lhj.bstest.service.SendMsgService;
import jakarta.annotation.Resource;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class SendMsgServiceImpl implements SendMsgService {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Override
    public void sendMsg(Object msg, Integer delayTime) {
        rabbitTemplate.convertAndSend(DelayQueueConfig.DELAYED_EXCHANGE_NAME, DelayQueueConfig.DELAYED_ROUTING_KEY, msg, message -> {
            //设置延时消息
            message.getMessageProperties().setDelay(delayTime);
            return message;
        });
    }
}
