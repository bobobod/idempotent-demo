package com.cczu.idempotent.Exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author jianzhen.yin
 * @date 2020/8/23
 */
@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(IdempotentException.class)
    public String ex(IdempotentException e){
        return e.getMessage();
    }
}
