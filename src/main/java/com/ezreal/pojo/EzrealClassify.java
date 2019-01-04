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
 * @date: 2018/12/12 20:08
 * @description: 基础分类实体
 */
@Data
public class EzrealClassify extends BasePojo implements Serializable {

    /**
     * 分类id,自增长
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 分类父id(0为顶级父类)
     */
    private Integer parentId;

    /**
     * 分类名称(必填)
     */
    @NotNull(message = ParamCheckConstant.NAME_NULL_MSG)
    private String name;

}
