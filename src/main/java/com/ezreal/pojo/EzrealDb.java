package com.ezreal.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ezreal.common.constant.ParamCheckConstant;
import com.ezreal.common.pojo.BasePojo;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @author: shenke
 * @date: 2018/12/9 19:31
 * @description: 数据库配置实体
 */
@Data
public class EzrealDb extends BasePojo implements Serializable {

    /**
     * 主键id,自增长
     */
    @TableId(type = IdType.AUTO)
    @NotNull(message = ParamCheckConstant.ID_NULL_MSG)
    private Integer id;

    /**
     * 名称(必填)
     */
    @NotEmpty(message = ParamCheckConstant.NAME_NULL_MSG)
    private String name;

    /**
     * 数据库名称(必填)
     */
    @NotEmpty(message = ParamCheckConstant.DB_NAME_NULL_MSG)
    private String dbName;

    /**
     * 数据库IP地址(必填)
     */
    @Pattern(regexp = ParamCheckConstant.DB_HOST_NULL_REGEXP, message = ParamCheckConstant.DB_HOST_NULL_MSG)
    private String dbHost;

    /**
     * 数据库端口(必填)
     */
    @Pattern(regexp = ParamCheckConstant.DB_PORT_NULL_REGEXP, message = ParamCheckConstant.DB_PORT_NULL_MSG)
    private String dbPort;

    /**
     * 用户名(必填)
     */
    @NotEmpty(message = ParamCheckConstant.DB_USERNAME_NULL_MSG)
    private String username;

    /**
     * 密码(必填)
     */
    @NotEmpty(message = ParamCheckConstant.DB_PASSWORD_NULL_MSG)
    private String password;

    /**
     * 数据库类型(必填)
     */
    @NotNull(message = ParamCheckConstant.DB_TYPE_NULL_MSG)
    private Integer typeId;

}
