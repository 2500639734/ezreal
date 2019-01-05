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
 * @date: 2019/1/5 01:42
 * @description: 菜单实体
 */
@Data
public class EzrealMenu extends BasePojo implements Serializable {

    /**
     * 设置表主键id(自增长)
     */
    @TableId(type = IdType.AUTO)
    @NotNull(message = ParamCheckConstant.ID_NULL_MSG)
    private Integer id;

    /**
     * 父菜单id,默认0无父菜单
     */
    private Integer parentId;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单路径
     */
    private String url;

}
