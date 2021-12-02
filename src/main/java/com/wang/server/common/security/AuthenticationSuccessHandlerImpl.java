package com.wang.server.common.security;

import com.alibaba.fastjson.JSON;
import com.wang.server.common.Result.R;
import com.wang.server.common.Result.ResultEnum;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: blotServer
 * @description: 登录成功
 * @author: Mr.Wang
 * @create: 2021-12-01 10:32
 **/
@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {
//    @Autowired
//    private UserAuthDao userAuthDao;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
        // 返回登录信息
//        UserInfoDTO userLoginDTO = BeanCopyUtils.copyObject(UserUtils.getLoginUser(), UserInfoDTO.class);
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(R.ok().resultEnum(ResultEnum.LOGIN_SUCCESS)));
        // 更新用户ip，最近登录时间
//        updateUserInfo();
    }

    /**
     * 更新用户信息
     */
//    @Async
//    public void updateUserInfo() {
//        UserAuth userAuth = UserAuth.builder()
//                .id(UserUtils.getLoginUser().getId())
//                .ipAddress(UserUtils.getLoginUser().getIpAddress())
//                .ipSource(UserUtils.getLoginUser().getIpSource())
//                .lastLoginTime(UserUtils.getLoginUser().getLastLoginTime())
//                .build();
//        userAuthDao.updateById(userAuth);
//    }

}
