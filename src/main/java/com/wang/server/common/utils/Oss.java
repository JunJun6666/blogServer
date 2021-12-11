package com.wang.server.common.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;

/**
 * @program: blogServer
 * @description: 阿里云oss
 * @author: Mr.Wang
 * @create: 2021-12-05 21:21
 **/

@Component
public class Oss {

    @Value("${oss.endpoint}")
    private String endPoint;

    @Value("${oss.accessKeyId}")
    private String accessKeyId;

    @Value("${oss.accessKeySecret}")
    private String accessKeySecret;

    @Value("${oss.bucketName}")
    private String bucketName;

    public void upload(InputStream inputStream, String objectName) {

        //创建ossClient实例
        OSS ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);

        //创建PutObjectRequest对象
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, inputStream);

        ossClient.putObject(putObjectRequest);

        ossClient.shutdown();

    }

    public URL getTempUrl(String objectName) {

        //创建ossClient实例
        OSS ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);

        //获取5分钟有效链接
        URL url = ossClient.generatePresignedUrl(bucketName, objectName, new Date(System.currentTimeMillis() + 300 * 1000));

        ossClient.shutdown();

        return url;

    }

    /**
     * 获取文件输入流
     * @param objectName
     * @return
     */
    public InputStream getOut(String objectName) throws IOException {

        //创建ossClient实例
        OSS ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);
        OSSObject object = ossClient.getObject(bucketName, objectName);
        try {
            InputStream is = object.getObjectContent();
            byte[] bytes = toByteArray(is);
            return new ByteArrayInputStream(bytes);
        }finally {
            ossClient.shutdown();
        }
    }

    /**
     * InputStream流转byte数组
     *
     * @param input
     * @return
     * @throws IOException
     */
    public static byte[] toByteArray(InputStream input) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[input.available()];
        int n = 0;
        while (-1 != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
        }
        return output.toByteArray();
    }

}
