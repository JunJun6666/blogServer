package com.wang.server.controller;

import com.wang.server.common.Result.R;
import com.wang.server.service.TypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: blogServer
 * @description: 类型
 * @author: Mr.Wang
 * @create: 2021-12-11 15:21
 **/
@Api(tags = "博客类型模块")
@RestController
@RequestMapping("/type")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/")
    @ApiOperation(value = "所有分类")
    public R getAllType(){
        return R.ok().data("type" , typeService.list());
    }

}
