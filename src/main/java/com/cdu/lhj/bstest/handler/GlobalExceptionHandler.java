package com.cdu.lhj.bstest.handler;


import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.util.SaResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 全局异常拦截
    @ExceptionHandler
    public SaResult handlerException(Exception e) {
        e.printStackTrace();
        return SaResult.error("系统异常");
    }
    @ExceptionHandler
    public SaResult handleNotLoginException(NotLoginException e){
        SaResult saResult = new SaResult();
        saResult.setCode(401);
        saResult.setMsg(e.getMessage());
        saResult.setData(null);
        return saResult;
    }
}
