package com.ezreal.pojo;

import lombok.Data;

/**
 * @author: shenke
 * @date: 2019/1/1 00:04
 * @description:
 */
@Data
public class DbConfig {

    private String[] tableName;
    private String driver;
    private String url;
    private String username;
    private String password;

}
