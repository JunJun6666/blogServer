package com.wang.server.common.security;

import com.alibaba.fastjson.JSON;
import com.wang.server.common.Result.R;
import com.wang.server.common.Result.ResultEnum;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: blotServer
 * @description: 登录失败
 * @author: Mr.Wang
 * @create: 2021-12-01 10:34
 **/
@Component
public class AuthenticationFailHandlerImpl implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(R.error().resultEnum(ResultEnum.ERROR)));
    }

}
