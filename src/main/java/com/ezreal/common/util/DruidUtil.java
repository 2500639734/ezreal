package com.ezreal.common.util;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import com.ezreal.common.exception.DruidException;
import com.ezreal.pojo.DruidConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.sql.*;
import java.util.*;

/**
 * @author: shenke
 * @date: 2018/11/11 15:25
 * @description: Alibaba Druid连接池工具,开启/关闭数据库连接,使用Druid连接池管理
 */
@Slf4j
public class DruidUtil {

    /**
     * 获取表名称，字段类型，注释信息
     */
    private static final String DEFAULT_TABLE_INFO_SQL = "select column_name,data_type,column_comment,column_key from information_schema.columns where table_name = ";

    private static DruidDataSource druidDataSource;
    private static DruidConfig druidConfig;
    private static Connection conn = null;
    private static PreparedStatement pstmt = null;
    private static ResultSet rs = null;

    private DruidUtil(){

    }

    /**
     * 开启数据库连接,自定义连接信息,其它配置项默认
     * @param druidConfig    druid配置实体
     * @return
     */
    public static Connection openConnection(DruidConfig druidConfig){
        if(druidConfig == null){
            return  null;
        }
        DruidUtil.druidConfig = druidConfig;

        String driver = druidConfig.getDriver();
        String url = druidConfig.getUrl();
        String username = druidConfig.getUsername();
        String password = druidConfig.getPassword();

        if(StringUtils.isEmpty(driver) || StringUtils.isEmpty(url) || StringUtils.isEmpty(username) ||
                StringUtils.isEmpty(password)){
            log.error("获取数据库连接失败,参数为空 druidConfig = {}", druidConfig);
            throw new DruidException("获取数据库连接失败,参数为空");
        }

        druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(druidConfig.getDriver());
        druidDataSource.setUrl(druidConfig.getUrl());
        druidDataSource.setUsername(druidConfig.getUsername());
        druidDataSource.setPassword(druidConfig.getPassword());

        if(druidConfig.getMaxWait() == null){
            druidConfig.setMaxWait(DruidConfig.MAX_WAIT);
        }
        druidDataSource.setMaxActive(druidConfig.getMaxWait());

        if(druidConfig.getConnectionErrorRetryAttempts() == null){
            druidConfig.setConnectionErrorRetryAttempts(DruidConfig.CONNECTION_ERROR_RETRY_ATTEMPTS);
        }
        druidDataSource.setConnectionErrorRetryAttempts(druidConfig.getConnectionErrorRetryAttempts());

        if(druidConfig.getBreakAfterAcquireFailure() == null){
            druidConfig.setBreakAfterAcquireFailure(DruidConfig.BREAK_FATER_ACQUIRE_FAILURE);
        }
        druidDataSource.setBreakAfterAcquireFailure(druidConfig.getBreakAfterAcquireFailure());

        try {
            conn = druidDataSource.getConnection();
        } catch (SQLException e) {
            throw new DruidException("开启数据库连接异常", e);
        }

        log.info("开启数据库连接成功, Connection = {}", conn);
        return conn;
    }

    /**
     * 获取当前druid配置信息
     * @return
     */
    public static DruidConfig getCurrentDruidConfig(){
        log.info("DruidConfig = {}", druidConfig);
        return druidConfig;
    }

    /**
     * 获取当前Connection对象
     * @return
     */
    public static Connection getCurrentConnection(){
        log.info("当前Connection = {}", conn);
        return conn;
    }

    /**
     * 获取当前PreparedStatement对象
     * @return
     */
    public static PreparedStatement getCurrentPreparedStatement(){
        log.info("当前PreparedStatement = {}", pstmt);
        return pstmt;
    }

    /**
     * 获取当前ResultSet对象
     * @return
     */
    public static ResultSet getCurrentResultSet(){
        log.info("当前ResultSet = {}", rs);
        return rs;
    }

    /**
     * 关闭当前连接,归还到连接池中
     */
    public static void close(){
        try{
            if(rs != null){
                rs.close();
                rs = null;
            }
            if(pstmt != null){
                pstmt.close();
                pstmt = null;
            }
            if(conn != null){
                conn.close();
                conn = null;
            }
            log.info("关闭数据库连接成功");
        } catch (Exception e){
            throw new DruidException("关闭数据库连接异常", e);
        }
    }

    /**
     *  关闭Druid连接池
     */
    public static void closeDruidDataSource(){
        if(druidDataSource != null){
            druidDataSource.close();
            druidDataSource = null;
        }
        log.info("关闭Druid连接池成功");
    }

    /**
     * 获取表信息，自定义数据库连接信息
     * @param druidConfig druid配置实体
     * @return
     */
    public static Map<String, List<Map<String, Object>>> queryTableInfo(DruidConfig druidConfig){
        if(conn == null){
            conn = openConnection(druidConfig);
        }
        if(conn == null){
            throw new DruidException("获取数据库连接为空");
        }
        String[] tableNames = druidConfig.getTableName();
        if(!ArrayUtils.isEmpty(tableNames)){
            Map<String, List<Map<String, Object>>> tableInfos = new HashMap<>();
            List<Map<String, Object>> tableInfo = null;
            Map<String, Object> map = null;
            String sql = StringBuildUtil.getStringBuilder()
                    .append(DEFAULT_TABLE_INFO_SQL)
                    .append("'").append("%s").append("'").toString();

            for(String tableName : tableNames){
                tableInfo = new LinkedList<>();
                sql = String.format(sql, tableName);
                try {
                    pstmt = conn.prepareStatement(sql);
                    rs = pstmt.executeQuery(sql);

                    while (rs.next()){
                        ResultSetMetaData metaData = rs.getMetaData();
                        int count = metaData.getColumnCount();
                        map = new HashMap<>(count);
                        for (int i = 1; i <= count; i++){
                            String columnName = metaData.getColumnName(i).toLowerCase();
                            Object columnValue = rs.getObject(columnName);
                            map.put(columnName, columnValue);
                        }
                        tableInfo.add(map);
                    }
                    tableInfos.put(tableName, tableInfo);
                } catch (SQLException e) {
                    throw new DruidException("查询表信息异常", e);
                }
            }

            return tableInfos;

        }

        return null;
    }

}
