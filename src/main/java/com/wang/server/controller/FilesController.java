package com.wang.server.controller;

import com.wang.server.common.Result.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @program: blogServer
 * @description: 上传文件
 * @author: Mr.Wang
 * @create: 2021-12-05 21:57
 **/
@RestController
@RequestMapping("/files")
public class FilesController {

    /**
     * 文件上传
     */

    @PostMapping("/uploadFile")
    public R uploadFile(@RequestParam(value = "file") MultipartFile file){
        return R.ok();
    }

}
