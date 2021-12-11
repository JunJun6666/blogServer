package com.wang.server.controller;

import com.wang.server.common.Result.R;
import com.wang.server.common.Result.ResultEnum;
import com.wang.server.common.utils.Oss;
import com.wang.server.common.utils.UuidUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: blogServer
 * @description: 上传文件
 * @author: Mr.Wang
 * @create: 2021-12-05 21:57
 **/
@RestController
@RequestMapping("/files")
@Slf4j
@Api(tags = "文件模块")
//@CrossOrigin
public class FilesController {

    private static List<String> whiteFile = new ArrayList<>();

    @Value("${url}")
    private String url;

    @Autowired
    private Oss oss;

    /**
     * 合法上传文件类型
     */
    static {
        whiteFile.add(".png");
        whiteFile.add(".jpeg");
        whiteFile.add(".jpg");
    }

    /**
     * 文件上传
     */

    @PostMapping ("/uploadFileToOss")
    @ApiOperation(value = "文件上传")
    public R uploadFile(@RequestParam(value = "file") MultipartFile file) {

        try {
            //截取文件名
            String[] split = file.getOriginalFilename().split("\\.");
            //获取文件名以及后缀
            String fileRealName = split[0];
            String suffix = "." + split[split.length - 1].toLowerCase();
            // 判断文件后缀是否为限制文件格式
            if (!whiteFile.contains(suffix)) {
                return R.error().resultEnum(ResultEnum.FILE_FORMAT_ERROR);
            }
            //用uuid代替源文件名称进行保存
            String id = UuidUtil.get32UUID();
            //上传文件
            oss.upload(file.getInputStream() , id);
            String src = url + "showFile/" + id;

            return R.ok().data("url", src);
        } catch (Exception e) {
            log.error("文件上传失败");
        }
        return R.ok();
    }


    /**
     * 显示图片
     *
     * @param id
     * @param response
     * @return
     */
    @GetMapping("/showFile/{id}")
    public R showFile(@PathVariable("id") String id, HttpServletResponse response) {
        InputStream is = null;
        ServletOutputStream os = null;
        try {
            is = oss.getOut(id);
            //设置相关参数
            response.setHeader("Accept-Ranges", "bytes");
            os = response.getOutputStream();
            int count;
            byte[] buffer = new byte[4096];
            while ((count = is.read(buffer)) != -1) {
                os.write(buffer, 0, count);
            }
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (null != is) {
                    is.close();
                }
                if (null != os) {
                    os.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return R.ok();
    }
}
