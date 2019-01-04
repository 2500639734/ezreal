/**
 * 系统相关函数
 * 需要提前引入layui css,layui js,jquery
 */
var SystemFun = {
    /**
     * 获取系统配置
     * @param $
     */
    getConfig : function(){
        var config;
        $.ajax({
            url : "config.json",
            type : "get",
            async : false,
            dataType : "json",
            success : function(result){
                if (result == null || result == "" || result == undefined){
                    console.error("获取ezreal系统配置失败");
                }
                config = result;
            },
            error : function(){
                console.error("获取ezreal系统配置时网络错误");
            }
        });
        return config;
    },
    /**
     * 获取系统主题颜色
     */
    getThemeColor : function(){
        var res;
        $.ajax({
            url : "/ezreal_setting/getEzrealSetting",
            type : "get",
            async : false,
            dataType : "json",
            success : function(response){
                res = response;
            },
            error : function(){
                res = null;
            }
        });
        return res;
    },
    /**
     * 设置系统主题颜色
     * @param systemThemeColorElement 需要修改样式的元素
     * @param ezrealSetting 系统设置实体
     */
    setThemeColor : function(systemThemeColorElement, ezrealSetting){
        var res;
        $.ajax({
            url : "/ezreal_setting/setEzrealSetting",
            type : "post",
            async : false,
            dataType : "json",
            contentType : "application/json",
            data : JSON.stringify(ezrealSetting),
            success : function(response){
                res = response;
            },
            error : function(){
                res = null;
            }
        });
        return res;
    }
}