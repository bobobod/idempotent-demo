package com.cczu.idempotent.controller;

import com.cczu.idempotent.annotation.AutoIdempotent;
import com.cczu.idempotent.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jianzhen.yin
 * @date 2020/8/23
 */
@RestController
public class IdempotentController {

    @Autowired
    private TokenService tokenService;

    @GetMapping("/getToken")
    public String getToken(){
        return tokenService.createToken();
    }

    @GetMapping("hello1")
    @AutoIdempotent
    public String hello1(){
        return "hello1";
    }

    @GetMapping("hello2")
    public String hello2(){
        return "hello2";
    }
}
