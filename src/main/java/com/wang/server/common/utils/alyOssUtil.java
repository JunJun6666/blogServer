package com.wang.server.common.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Value;

import java.io.InputStream;
import java.net.URL;
import java.util.Date;

/**
 * @program: blogServer
 * @description: 阿里云oss
 * @author: Mr.Wang
 * @create: 2021-12-05 21:21
 **/
public class alyOssUtil {

    @Value("upload.oss.endPoint")
    private static  String endPoint;
    @Value("upload.oss.accessKeyId")
    private static  String accessKeyId;
    @Value("upload.oss.accessKeySecret")
    private static  String accessKeySecret;
    @Value("upload.oss.bucketName")
    private static  String bucketName;

    public static void upload(InputStream inputStream , String objectName){

        //创建ossClient实例
        OSS ossClient = new OSSClientBuilder().build(endPoint , accessKeyId , accessKeySecret);

        //创建PutObjectRequest对象
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, inputStream);

        ossClient.putObject(putObjectRequest);

        ossClient.shutdown();

    }

    public static URL getTempUrl(String objectName){

        //创建ossClient实例
        OSS ossClient = new OSSClientBuilder().build(endPoint , accessKeyId , accessKeySecret);

        //获取5分钟有效链接
        URL url = ossClient.generatePresignedUrl(bucketName, objectName, new Date(System.currentTimeMillis() + 300 * 1000));

        ossClient.shutdown();

        return url;

    }

}
