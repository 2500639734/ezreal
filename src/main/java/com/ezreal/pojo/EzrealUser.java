package com.ezreal.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ezreal.common.constant.ParamCheckConstant;
import com.ezreal.common.pojo.BasePojo;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author: shenke
 * @date: 2019/1/5 01:35
 * @description: 用户实体
 */
@Data
public class EzrealUser extends BasePojo implements Serializable {

    /**
     * 设置表主键id(自增长)
     */
    @TableId(type = IdType.AUTO)
    @NotNull(message = ParamCheckConstant.ID_NULL_MSG)
    private Integer id;

    /**
     * 昵称(用户输入,必填)
     */
    private String nickname;

    /**
     * 用户名(用户输入,登录注册时必填)
     */
    private String username;

    /**
     * 密码(用户输入,登录注册时必填)
     */
    private String password;

    /**
     * 头像(用户上传,不上传使用默认头像)
     */
    private String portrait;

    /**
     * 手机号(用户输入)
     */
    private String phone;

    /**
     * 邮箱地址(用户输入)
     */
    private String email;

    /**
     * 简介(用户输入)
     */
    private String introduce;

    /**
     * 用户类型(系统生成,0为超级管理员,默认1为普通用户)
     */
    private Boolean type;

}
