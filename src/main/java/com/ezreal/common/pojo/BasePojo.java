package com.ezreal.common.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: shenke
 * @date: 2018/12/9 19:26
 * @description: 通用字段实体
 */
@Data
public class BasePojo implements Serializable {

    /**
     * 创建时间,系统生成
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间,系统生成
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 创建人id,系统生成
     */
    private Integer createUserId;

    /**
     * 更新人id,系统生成
     */
    private Integer updateUserId;

    /**
     * 是否有效,默认1
     */
    @TableLogic
    private Boolean isValid;

    /**
     * 备注信息(选填)
     */
    private String remark;

}
