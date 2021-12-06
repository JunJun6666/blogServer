package com.wang.server;

import com.wang.server.common.dataoriginconfig.DynamicDataSourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

//定时器开关
@EnableScheduling
//取消自动加载的数据源
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
//导入自定义的配置类
@Import(DynamicDataSourceConfig.class)
public class BlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class , args);
    }
}
