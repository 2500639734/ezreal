package com.ezreal.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ezreal.common.constant.ParamCheckConstant;
import com.ezreal.common.pojo.BasePojo;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author: shenke
 * @date: 2018/12/31 08:49
 * @description: 系统设置实体
 */
@Data
public class EzrealSetting extends BasePojo {

    /**
     * 设置表主键id(自增长)
     */
    @TableId(type = IdType.AUTO)
    @NotNull(message = ParamCheckConstant.ID_NULL_MSG)
    private Integer id;

    /**
     * 用户表主键id(系统同步)
     */
    private Integer userId;

    /**
     * 主题颜色(用户选择,一般为rgb格式,默认为rgba(35,38,46,1))
     */
    @NotNull(message = ParamCheckConstant.DB_SETTING_THEME_COLOR_NULL_MSG)
    private String themeColor;

}
