package com.cczu.idempotent.Exception;

/**
 * @author jianzhen.yin
 * @date 2020/8/23
 */
public class IdempotentException  extends Exception {
    public IdempotentException(String message) {
        super(message);
    }
}
