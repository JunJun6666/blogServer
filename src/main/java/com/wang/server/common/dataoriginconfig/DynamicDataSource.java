//package com.wang.server.common.dataoriginconfig;
//
//import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
//
//import javax.sql.DataSource;
//import java.util.Map;
//
///**
// * @program: reportServer-fy
// * @description: 数据源配置
// * @author: Mr.Wang
// * @create: 2021-12-04 09:35
// **/
//public class DynamicDataSource extends AbstractRoutingDataSource {
//
//
//    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();
//
//    public DynamicDataSource(DataSource defaultSource, Map<Object, Object> targetSource) {
//        super.setDefaultTargetDataSource(defaultSource);
//        super.setTargetDataSources(targetSource);
//        super.afterPropertiesSet();
//    }
//
//    @Override
//    protected Object determineCurrentLookupKey() {
//        return getDataSource();
//    }
//
//    public static void setDataSource(String dataSource) {
//        contextHolder.set(dataSource);
//    }
//
//    public static String getDataSource() {
//        return contextHolder.get();
//    }
//
//    public static void clearDataSource() {
//        contextHolder.remove();
//    }
//}
