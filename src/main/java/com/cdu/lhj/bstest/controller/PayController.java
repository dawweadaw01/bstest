package com.cdu.lhj.bstest.controller;


import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.cdu.lhj.bstest.config.AlipayConfig;
import com.cdu.lhj.bstest.service.AppointmentsService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/pay")
public class PayController {
    private static final String GATEWAY_URL = "https://openapi-sandbox.dl.alipaydev.com/gateway.do";
    private static final String FORMAT = "JSON";
    private static final String CHARSET = "UTF-8";
    private static final String SIGN_TYPE = "RSA2";

    @Resource
    private AlipayConfig alipayConfig;

    @Resource
    private AppointmentsService appointmentsService;

    @GetMapping("/pay")
    public void pay(@RequestParam String oid, @RequestParam BigDecimal fixedPrice, @RequestParam String name, HttpServletResponse httpResponse) throws Exception {
        //查询订单信息
        //Orders orders = ordersService.selectByOrderID(oid);
        //if(orders==null) return;
        //1. 创建CLinet，通过SDK提供的Client，负责调用支付宝的API
        AlipayClient alipayClient = new DefaultAlipayClient(GATEWAY_URL, alipayConfig.getAppId(),
                alipayConfig.getAppPrivateKey(), FORMAT, CHARSET, alipayConfig.getAlipayPublicKey(), SIGN_TYPE);

        //2. 创建Request并设置Request参数
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest(); //发送请求的类
        request.setNotifyUrl(alipayConfig.getNotifyUrl());
        JSONObject bizContent = new JSONObject();
        bizContent.put("out_trade_no", oid);  //订单编号
        bizContent.put("total_amount", fixedPrice);   //订单总金额
        bizContent.put("subject", name);  //支付的商品名称
        bizContent.put("product_code", "FAST_INSTANT_TRADE_PAY");
        request.setBizContent(bizContent.toString());
        request.setReturnUrl("http://localhost:4444/order"); //支付成功返回至订单界面
        //执行请求，拿到响应的结果，返回浏览器
        String form = "";
        try {
            form = alipayClient.pageExecute(request).getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        httpResponse.setContentType("text/html;charset=" + CHARSET);
        httpResponse.getWriter().write(form);
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();
    }

    @PostMapping("/notify")  // 注意这里必须是POST接口
    public void payNotify(HttpServletRequest request) throws Exception {
        if (request.getParameter("trade_status").equals("TRADE_SUCCESS")) {
            System.out.println("=========支付宝异步回调========");
            Map<String, String> params = new HashMap<>();
            Map<String, String[]> requestParams = request.getParameterMap();
            for (String name : requestParams.keySet()) {
                params.put(name, request.getParameter(name));
            }
            String sign = params.get("sign");
            String content = AlipaySignature.getSignCheckContentV1(params);
            boolean checkSignature = AlipaySignature.rsa256CheckContent(content, sign, alipayConfig.getAlipayPublicKey(), "UTF-8");
            //支付宝验证
            if (checkSignature) {
                //输出一些支付信息
                String oid = params.get("out_trade_no");
                // 转换为Long
                Long orderId = Long.valueOf(oid);
                // 更新订单状态
                appointmentsService.updateAppointmentsStatus(orderId);
            }
        }
    }
}
