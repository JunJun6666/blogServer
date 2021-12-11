package com.wang.server.controller;


import com.wang.server.common.Result.R;
import com.wang.server.entity.Blog;
import com.wang.server.service.BlogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/")
    @ApiOperation(value = "所有博客")
    public R queryPage(@RequestParam Map<String, Object> param){
        return blogService.queryPage(param);
    }


    @PostMapping("/")
    @ApiOperation(value = "写博客")
    public R add(@RequestBody Blog blog){
        return blogService.add(blog);
    }

}
