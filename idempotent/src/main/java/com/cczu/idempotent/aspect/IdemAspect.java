package com.cczu.idempotent.aspect;

import com.cczu.idempotent.Exception.IdempotentException;
import com.cczu.idempotent.token.TokenService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author jianzhen.yin
 * @date 2020/8/23
 */
@Component
@Aspect
public class IdemAspect {
    @Autowired
    private TokenService tokenService;
    @Pointcut(value = "@annotation(com.cczu.idempotent.annotation.AutoIdempotent)")
    public void pointcut(){

    }
    @Before("pointcut()")
    public void before(JoinPoint joinPoint) throws IdempotentException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        try {
            tokenService.checkToken(request);
        } catch (IdempotentException e) {
            throw e;
        }
    }


}
