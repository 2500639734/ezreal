package com.ezreal.common.constant;

/**
 * @author: shenke
 * @date: 2018/12/15 16:57
 * @description:
 */
public class ParamCheckConstant {

    private ParamCheckConstant(){

    }

    public static final String ID_NULL_MSG = "主键不能为空";
    public static final String NAME_NULL_MSG = "名称不能为空";

    public static final String DB_NAME_NULL_MSG = "数据库名称不能为空";
    public static final String DB_HOST_NULL_REGEXP = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";
    public static final String DB_HOST_NULL_MSG = "数据库地址格式错误";
    public static final String DB_PORT_NULL_REGEXP = "[1-9]{1}\\d{0,4}";
    public static final String DB_PORT_NULL_MSG = "数据库端口只能是0-99999范围的数字";
    public static final String DB_USERNAME_NULL_MSG = "数据库用户不能为空";
    public static final String DB_PASSWORD_NULL_MSG = "数据库密码不能为空";
    public static final String DB_TYPE_NULL_MSG = "数据库类型不能为空";

    public static final String SETTING_THEME_COLOR_NULL_MSG = "主题颜色不能为空";

}
