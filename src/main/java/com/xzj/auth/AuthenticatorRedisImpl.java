package com.xzj.auth;

import com.xzj.exception.ImsAuthException;
import com.xzj.utils.JwtUtil;
import com.xzj.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author 夏子健
 * @version 1.0
 * @date 2023/7/7 17:03
 */
//@Component    //认证器注解注入
public class AuthenticatorRedisImpl implements Authenticator{
    private RedisUtil util;
    public AuthenticatorRedisImpl(){}
    public AuthenticatorRedisImpl(RedisUtil util){this.util = util;}

    /**
     * Bearer xxxxxx 客户端鉴权时添加的Bearer，说明鉴权类型
     * @param token
     * @return
     */
    @Override
    public AuthInfo auth(String token) {
        if (util == null){
            return null;
        }
        Object obj = util.get(token);
        if (obj == null){
            throw new ImsAuthException("找不到token对应的用户信息");
        }else{
            AuthInfo authInfo = (AuthInfo)obj;
            if (authInfo.getExpired() < System.currentTimeMillis()){
                throw new ImsAuthException("token已过期");
            }
            return authInfo;
        }

    }
}
