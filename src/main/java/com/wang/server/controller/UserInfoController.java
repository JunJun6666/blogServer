package com.wang.server.controller;


import com.wang.server.common.Result.R;
import com.wang.server.entity.LoginUser;
import com.wang.server.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2021-12-01
 */
@Api(tags = "用户模块")
@RestController
public class UserInfoController {

    @Autowired
    private UserInfoService userService;

    @ApiOperation(value = "登录之后返回token")
    @PostMapping("/login")
    public R login(LoginUser loginUser){
        return userService.login(loginUser);
    }

}
