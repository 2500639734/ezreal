package com.ezreal.pojo.vo;

import com.ezreal.common.pojo.BasePojo;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: shenke
 * @date: 2018/12/12 20:15
 * @description:
 */
@Data
public class EzrealDbVo extends BasePojo implements Serializable {

    /**
     * 主键id,自增长
     */
    private Integer id;

    /**
     * 名称(必填)
     */
    private String name;

    /**
     * 数据库分类名称
     */
    private String typeName;

    /**
     * 数据库分类Id(必填)
     */
    private String typeId;

    /**
     * 数据库名称(必填)
     */
    private String dbName;

    /**
     * 数据库IP地址(必填)
     */
    private String dbHost;

    /**
     * 数据库端口(必填)
     */
    private String dbPort;

    /**
     * 用户名(必填)
     */
    private String username;

    /**
     * 密码(必填)
     */
    private String password;

}
