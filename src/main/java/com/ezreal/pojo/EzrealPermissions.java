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
 * @date: 2019/1/5 01:43
 * @description: 权限实体
 */
@Data
public class EzrealPermissions extends BasePojo implements Serializable {

    /**
     * 设置表主键id(自增长)
     */
    @TableId(type = IdType.AUTO)
    @NotNull(message = ParamCheckConstant.ID_NULL_MSG)
    private Integer id;

    /**
     * 菜单表id
     */
    private Integer menuId;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 权限标记,指定角色所拥有的权限
     */
    private String tag;

}
