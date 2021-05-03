package com.jxw.icharity.config;

import com.jxw.icharity.exception.LoginException;
import com.jxw.icharity.util.JwtUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.jxw.icharity.constants.Constants.*;

public class LoginInterceptor implements HandlerInterceptor {

    JwtUtil jwtUtil =new JwtUtil();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        String authorization=request.getHeader(AUTH);
        if(authorization==null){
            throw new LoginException();
        }
        String token = authorization.replace(AUTH_PREFIX, "");
        if(!jwtUtil.validateToken(token)){
            throw new LoginException();
        }
        return true;
    }
}
