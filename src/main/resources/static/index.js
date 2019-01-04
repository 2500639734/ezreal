layui.use(['element','colorpicker', 'layer'], function(){

    var element = layui.element;
    var colorpicker = layui.colorpicker;
    var layer = layui.layer;

    var config = SystemFun.getConfig();
    var systemThemeColorElement = config.systemThemeColorElement;
    var defaultThemeColor = config.defaultThemeColor;
    var defaultSelect = config.defaultSelect;
    var viewConfig = config.view;

    // 首先初始化颜色选择器,避免后续渲染主题颜色时还未生成组件
    colorpicker.render({
        elem: '#theme_color_selector',
        color: defaultThemeColor,
        format: 'rgb',
        predefine: true,
        alpha: true,
        size: 'xs',
        done: function(color){
            // 确定时修改系统主题
            if(color != null && color != '' && color != undefined){
                // 修改系统主题颜色 TODO 暂时固定为1
                var ezrealSetting = {
                    userId : 1,
                    themeColor : color
                }
                var setThemeColorResponse = SystemFun.setThemeColor(systemThemeColorElement, ezrealSetting);
                if(setThemeColorResponse != null && setThemeColorResponse != '' && setThemeColorResponse != undefined){
                    if(setThemeColorResponse.code == 200){
                        // 设置系统主题颜色
                        $(systemThemeColorElement).css('background-color', setThemeColorResponse.data.themeColor);
                    } else {
                        layer.msg("设置主题颜色失败,保持当前主题颜色", {icon: 5});
                    }
                } else {
                    layer.msg('设置主题颜色失败,保持当前主题颜色', {icon: 5});
                }
            }
        }
    });

    // 获取主题背景颜色
    var getThemeColorResponse = SystemFun.getThemeColor();
    if(getThemeColorResponse != null && getThemeColorResponse != '' && getThemeColorResponse != undefined){
        if(getThemeColorResponse.code == 200){
            var themeColor = getThemeColorResponse.data.themeColor;
            // 设置用户系统主题颜色
            $(systemThemeColorElement).css('background-color', themeColor);
        } else {
            // 请求失败时采用默认主题颜色
            $(systemThemeColorElement).css('background-color', defaultThemeColor + " !important");
            layer.msg('获取用户主题颜色失败,采用默认主题颜色', {icon: 5});
        }
    } else {
        // 请求失败时采用默认主题颜色
        $(systemThemeColorElement).css('background-color', defaultThemeColor);
        layer.msg('获取用户主题颜色失败,采用默认主题颜色', {icon: 5});
    }

    // 导航标签绑定iframe
    $(".layui-nav-child").find(".left_nav").each(function(){
        var that = $(this);
        var id = that.attr("id");
        var tabTitle = that.text();
        var src = viewConfig[id];

        // 监听左侧菜单导航点击事件
        that.on("click", function () {
            var tabContent = $("#" + id + "_tabContent");
            // 如果当前tab不存在
            if(tabContent.length <= 0){
                // 新增用户选择的tab项
                element.tabAdd('tab', {
                    title: tabTitle,
                    content: '<iframe id="' + id + '_tabContent" src="' + src + '" scrolling="auto" frameborder="0" width="100%" height="100%"></iframe>',
                    id: id
                })
            }
            // 切换到新增的tab项
            element.tabChange('tab', id);
        });
    });

    // 监听tab选项卡切换事件
    element.on('tab(tab)', function(data){
        var lay_id = $(this).attr("lay-id");
        // 获取左侧导航菜单的dd元素(当前点击a标签的的父元素)
        var leftDD = $("#" + lay_id).parent("dd");
        // 如果当前点击的左侧导航菜单未高亮,添加高亮效果,同时移除其它高亮
        var leftNavHighlightClass = "layui-this lay-this";
        if(!leftDD.hasClass(leftNavHighlightClass)){
            $(".layui-nav-child dd").removeClass(leftNavHighlightClass);
            leftDD.addClass(leftNavHighlightClass);
        }
    });

    // 监听tab选项卡删除事件
    element.on('tabDelete(tab)', function(data){
        var index = data.index;
        if(index <= 0){
            // 如果当前关闭的选项卡是仅有的一个,那么同时移除左侧菜单的所有高亮效果
            var leftNavHighlightClass = "layui-this lay-this";
            $(".layui-nav-child dd").removeClass(leftNavHighlightClass);
        }
    });

    // logo点击事件(刷新整个页面)
    $("#logo").bind("click", function () {
        window.location.reload();
    });

    // 默认左侧点击标签
    $(defaultSelect).click();

 });


