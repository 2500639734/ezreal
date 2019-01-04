/**
 * 表单工具方法
 * 依赖jquery
 * 依赖layui js
 * @type {{showData: FormFun.showData}}
 */
var FormFun = {
    /**
     * 回显layui表单数据
     * @param formId 表单id
     * @param data 数据
     */
    showLayuiFormData : function(formId, data){
        var form = $("#" + formId);
        if(form == undefined){
            throw "没有对应表单";
        }

        // 获取表单元素回显
        form.find("input,textarea,select").each(function () {
            var that = $(this);
            var tagName = that[0].tagName;
            // 输入框&文本框,直接匹配name赋值
            if(tagName == 'INPUT' || tagName == 'TEXTAREA'){
                var name = that.attr("name");
                var val = data[name];
                if(val != null && val != '' && val != undefined){
                    that.val(val);
                }
            } else if(tagName == 'SELECT'){
                // 下拉选,匹配name赋值,显示值为select的showText属性对应的值
                form.find("select").each(function () {
                    var that = $(this);
                    var name = that.attr("name");
                    var showText = that.attr("showText");
                    that.append("<option value=" + data[name] + " selected>" + data[showText] + "</option>");
                });
            }

        });
    }
}