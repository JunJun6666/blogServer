package com.wang.server.common.dataoriginconfig;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @program: reportServer-fy
 * @description: aop查看有没有自定义的注解，没有就用默认数据源
 * @author: Mr.Wang
 * @create: 2021-12-04 09:42
 **/
@Aspect
@Component
public class DataSourceAspect {

    @Pointcut("@annotation(com.wang.server.common.dataoriginconfig.DataSource)")
    public void dataSourcePointCut() {

    }

    @Around("dataSourcePointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();

        DataSource dataSource = method.getAnnotation(com.wang.server.common.dataoriginconfig.DataSource.class);
        if (dataSource == null) {
            DynamicDataSource.setDataSource("a-database");
        } else {
            DynamicDataSource.setDataSource(dataSource.name());
        }

        try {
            return point.proceed();
        } finally {
            DynamicDataSource.clearDataSource();
        }
    }
}