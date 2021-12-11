//package com.wang.server.common.dataoriginconfig;
//
//import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.stereotype.Component;
//
//import javax.sql.DataSource;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @program: reportServer-fy
// * @description: 注入数据源
// * @author: Mr.Wang
// * @create: 2021-12-04 09:37
// **/
//@Component
//@Configuration
//public class DynamicDataSourceConfig {
//
//
//    //配置数据源bean，有几个配几个
//    @Bean
//    @ConfigurationProperties("spring.datasource.a-database")
//    public DataSource aMasterDataSource() {
//        return DruidDataSourceBuilder.create().build();
//    }
//
//    @Bean
//    @ConfigurationProperties("spring.datasource.b-database")
//    public DataSource bMasterDataSource() {
//        return DruidDataSourceBuilder.create().build();
//    }
//
//
//
//
//    //Primary注解作用是自动装配时当出现多个Bean候选者时，被注解为@Primary的Bean将作为首选者，否则将抛出异常
//    @Bean
//    @Primary
//    public DynamicDataSource dataSource(@Qualifier(value = "aMasterDataSource") DataSource aMasterDataSource,@Qualifier(value = "bMasterDataSource") DataSource bMasterDataSource) {
//        Map<Object,Object> map = new HashMap<>();
//        map.put("a-database",aMasterDataSource);
//        map.put("b-database",bMasterDataSource);
//        return new DynamicDataSource(aMasterDataSource,map);
//
//    }
//
//}
