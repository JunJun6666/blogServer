package com.wang.server.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: blotServer
 * @description: 用来登录的实体类
 * @author: Mr.Wang
 * @create: 2021-12-02 08:33
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginUser{

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 验证码
     */
    private String code;

}
