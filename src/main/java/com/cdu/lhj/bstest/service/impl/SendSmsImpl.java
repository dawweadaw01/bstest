package com.cdu.lhj.bstest.service.impl;

import com.apistd.uni.Uni;
import com.apistd.uni.UniException;
import com.apistd.uni.UniResponse;
import com.apistd.uni.sms.UniMessage;
import com.apistd.uni.sms.UniSMS;
import com.cdu.lhj.bstest.service.SendSms;
import com.cdu.lhj.bstest.util.RedisUtil;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SendSmsImpl implements SendSms {

    @Resource
    private RedisUtil redisUtil;

    @Value("${sms.accessKeyId}")
    public String ACCESS_KEY_ID;

    @Value("${sms.accessKeySecret}")
    private String ACCESS_KEY_SECRET;

    @Value("${sms.signature}")
    private String SIGNATURE;
    @Override
    public boolean send(String phoneNum, String templateCode, String code,Long ttl) {
        // 判断是否已经发送过验证码
        if (redisUtil.hasKey("code:"+ phoneNum)) {
            throw new RuntimeException("验证码已发送，请稍后再试");
        }
        Uni.init(ACCESS_KEY_ID, ACCESS_KEY_SECRET); // 若使用简易验签模式仅传入第一个参数即可
        // 设置自定义参数 (变量短信)
        Map<String, String> templateData = new HashMap<>();
        templateData.put("code", code);
        templateData.put("ttl", ttl.toString());
        // 构建信息
        UniMessage message = UniSMS.buildMessage()
                .setTo(phoneNum)
                .setSignature(SIGNATURE)
                .setTemplateId(templateCode)
                .setTemplateData(templateData);
        // 发送短信
        try {
            UniResponse res = message.send();
            System.out.println(res);
        } catch (UniException e) {
            System.out.println("Error: " + e);
            System.out.println("RequestId: " + e.requestId);
            throw e;
        }
        // 将验证码存入redis
        redisUtil.set("code:"+ phoneNum, code, ttl*60L);
        return true;
    }
}
