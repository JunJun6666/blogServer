package com.wang.server.vo;

import com.wang.server.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @program: blogServer
 * @description: 用户详情
 * @author: Mr.Wang
 * @create: 2021-12-05 11:50
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoVo implements Serializable {

    private String id;

    private String userName;

    private String password;

    private String account;

    private String eMail;

    private String nickname;

    private List<Role> roles;

}
