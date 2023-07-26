package com.xzj.auth;

import com.xzj.exception.ImsAuthException;
import com.xzj.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author 夏子健
 * @version 1.0
 * @date 2023/7/7 17:03
 */
//@Component    //认证器注解注入
public class AuthenticatorImpl implements Authenticator{
    @Value("${jwt.auth.secret}")
    private String secret;
    public AuthenticatorImpl(){}
    public AuthenticatorImpl(String secret){this.secret = secret;}

    /**
     * Bearer xxxxxx 客户端鉴权时添加的Bearer，说明鉴权类型
     * @param token
     * @return
     */
    @Override
    public AuthInfo auth(String token) {
        String authToken;
        int index = token.indexOf(" ");
        if (index == -1){
            authToken = token;
        }else{
            String tokenType = token.substring(0,index);
            if (!"Bearer".equals(tokenType)){
                throw new ImsAuthException(String.format("无法识别的token类型[%s]",token));
            }else{
                authToken = token.substring(index).trim();
            }
        }
        AuthInfo authInfo = JwtUtil.verifyToken(authToken,secret);
        return authInfo;
    }
}
