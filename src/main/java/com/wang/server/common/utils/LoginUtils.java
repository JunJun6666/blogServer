package com.wang.server.common.utils;

import com.wang.server.entity.UserInfo;
import com.wang.server.vo.UserInfoVo;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @program: blogServer
 * @description: 用户工具类
 * @author: Mr.Wang
 * @create: 2021-12-05 11:33
 **/
public class LoginUtils {

    public static UserInfoVo getUser(){
        UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserInfoVo userInfoVo = new UserInfoVo();
        BeanUtils.copyProperties(userInfo , userInfoVo);
        userInfoVo.setPassword(null);

        //小bug，userName不知道为啥转不过来,所以手动放
        userInfoVo.setUserName(userInfo.getUsername());
        return userInfoVo;
    }

    public static String getUserId(){
        UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userInfo.getId();
    }

}
