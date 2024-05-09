package com.cdu.lhj.bstest.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class DelayQueueConfig {
    //    队列
    public static final String DELAYED_QUEUE_NAME = "delayed.queue";
    //    交换机
    public static final String DELAYED_EXCHANGE_NAME = "delayed.exchange";
    //  routingKey
    public static final String DELAYED_ROUTING_KEY = "delayed.routingkey";

    //声明队列

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Queue delayedQueue() {
        return new Queue(DELAYED_QUEUE_NAME);
    }

    /**
     * 1.交换机名称
     * 2.交换机的类型
     * 3.持久化与否
     * 4.是否自动删除
     * 5.其它参数
     */
    @Bean
    public CustomExchange delayedExchange() {

        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-delayed-type", "direct");
        return new CustomExchange(DELAYED_EXCHANGE_NAME, "x-delayed-message", true, false, arguments);
    }

    /**
     绑定
     */
    @Bean
    public Binding delayQueueBinding(@Qualifier("delayedQueue") Queue delayedQueue,
                                     @Qualifier("delayedExchange") CustomExchange delayedExchange
    ) {
        return BindingBuilder.bind(delayedQueue).to(delayedExchange).with(DELAYED_ROUTING_KEY).noargs();
    }

}
