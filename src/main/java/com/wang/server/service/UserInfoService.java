package com.wang.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wang.server.common.Result.R;
import com.wang.server.entity.LoginUser;
import com.wang.server.entity.Role;
import com.wang.server.entity.UserInfo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2021-12-01
 */
public interface UserInfoService extends IService<UserInfo> {

    R login(LoginUser loginUser);

    UserInfo getAdminByUserName(String username);

    List<Role> getRolesByAdminId(String id);

    R getUserInfo();
}
