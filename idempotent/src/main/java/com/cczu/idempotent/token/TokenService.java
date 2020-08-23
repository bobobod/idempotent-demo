package com.cczu.idempotent.token;

import com.cczu.idempotent.Exception.IdempotentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * @author jianzhen.yin
 * @date 2020/8/23
 */
@Component
public class TokenService {
    @Autowired
    private RedisService redisService;
    public String createToken(){
        String uuid = UUID.randomUUID().toString();
        redisService.setEX(uuid,uuid,10000L);
        return uuid;
    }
    public boolean checkToken(HttpServletRequest request) throws IdempotentException {
        String token = request.getHeader("token");
        if (StringUtils.isEmpty(token)){
             token = request.getParameter("token");
             if (StringUtils.isEmpty(token)){
                 throw new IdempotentException("token is blank");
             }
        }
        if (!redisService.exists(token)){
            throw new IdempotentException("重复的操作");
        }
        boolean remove = redisService.remove(token);
        if (!remove) {
            throw new IdempotentException("重复的操作");
        }
        return true;
    }
}
