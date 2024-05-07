package com.cdu.lhj.bstest.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "alipay")
public class AlipayConfig {
    // 支付宝的AppId
    private String appId;
    // 应用私钥
    private String appPrivateKey;
    // 支付宝公钥
    private String alipayPublicKey;
    //支付宝通知本地的接口完整地址
    private String notifyUrl;

}
