package com.wang.server.controller;


import com.wang.server.common.Result.R;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
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
@Api(tags = "博客信息模块")
@RestController
public class BlogController {


    @GetMapping("/blog")
    public R queryPage(){
        return R.ok();
    }


    @PostMapping("/blog")
    public R add(){
        return R.ok();
    }

}
