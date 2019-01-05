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
 * @date: 2019/1/5 01:40
 * @description: 角色实体
 */
@Data
public class EzrealRole extends BasePojo implements Serializable {

    /**
     * 设置表主键id(自增长)
     */
    @TableId(type = IdType.AUTO)
    @NotNull(message = ParamCheckConstant.ID_NULL_MSG)
    private Integer id;

    /**
     * 角色名称(用户输入,必填)
     */
    private String name;

}
