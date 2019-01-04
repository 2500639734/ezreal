layui.use(['form', 'layer'], function(){
    var form = layui.form;
    var layer = layui.layer;

    // 监听提交
    form.on('submit(ezreal_db_edit_submit)', function(data){
        $.ajax({
            url : "/ezreal_db/edit",
            type : "post",
            dataType : "json",
            contentType : "application/json",
            data : JSON.stringify(data.field),
            success : function(response){
                if(response.code == 200){
                    layer.msg(response.msg, {icon: 6});

                    // 成功提示0.5秒后关闭页面,并且更新父级页面(相当于重新渲染)
                    var index = parent.layer.getFrameIndex(window.name);
                    setTimeout(function(){
                        parent.layer.close(index);// 关闭弹出层
                        // parent.location.reload(); // 更新父级页面(修改为在父页面重新渲染table,体验更好)
                    }, 500);

                } else {
                    layer.msg(response.msg, {icon: 5});
                }
            },
            error : function(){
                layer.msg('服务器错误,请稍后重试', {icon: 5});
            }
        });
        // 阻止表单的form提交,因为已经使用了ajax请求
        return false;
    });

    // 初始化数据库类型下拉选项
    initSelect(form);

});

/**
 * 加载下拉选
 */
function initSelect(form){
    $.ajax({
        url : "/ezreal_classify/childrenList/2",
        method : "get",
        dataType : "json",
        success : function(response){
            if(response.code == 200){
                var data = response.data;
                var length = data.length;
                if(length > 0){
                    var dbTypeSelect = $("#ezreal_db_edit_form").find("#dbType");
                    var dbTypeOption = dbTypeSelect.find("option");
                    var selectdDbTypeId = dbTypeOption.val(); // 默认回显的id
                    var selectdDbTypeName = dbTypeOption.text(); // 默认回显的name
                    for(var i = 0; i < length; i ++ ){
                        var ezrealClassify = data[i];
                        var dbTypeId = ezrealClassify.id;
                        var dbTypeName = ezrealClassify.name;
                        // 加载时不包含默认已经回显的数据
                        if(dbTypeId == selectdDbTypeId && dbTypeName == selectdDbTypeName){
                            continue;
                        }
                        dbTypeSelect.append("<option value=" + dbTypeId + ">" + dbTypeName + "</option>");
                    }
                    // 重新渲染表单下拉选
                    form.render('select');
                }
            } else {
                throw "加载数据库类型下拉选失败";
            }
        },
        error : function(){
            layer.msg('服务器错误,请稍后重试', {icon: 5});
        }
    });
}

/**
 * 回显数据,父页面调用
 * @param data 数据
 * @param index 窗口下标
 */
function showData(data){
    FormFun.showLayuiFormData("ezreal_db_edit_form", data);
}