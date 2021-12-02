package com.wang.server.common.security;

import com.alibaba.fastjson.JSON;
import com.wang.server.common.Result.R;
import com.wang.server.common.Result.ResultEnum;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: blotServer
 * @description: 权限不足
 * @author: Mr.Wang
 * @create: 2021-12-01 10:27
 **/
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(R.error().resultEnum(ResultEnum.ILLEGAL_OPERATION)));
    }

}