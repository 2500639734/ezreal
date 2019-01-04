layui.use(['table','layer'], function(){
    var table = layui.table;
    var layer = layui.layer;

    // table配置项
    var options = {
        elem: '#ezreal_db_table',
        height: 'full-10',
        url: '/ezreal_db/list',
        toolbar: '#toolbar',
        page: true,
        cols: [[
            {field: 'row_checkbox', type: 'checkbox',width: '4%'},
            {field: 'id', title: 'ID',width: '4%'},
            {field: 'name', title: '名称',width: '8%'},
            {field: 'dbName', title: '数据库名称',width: '8%'},
            {field: 'dbHost', title: '数据库地址',width: '10%'},
            {field: 'dbPort', title: '数据库端口',width: '8%'},
            {field: 'typeName', title: '数据库类型',width: '8%'},
            {field: 'username', title: '用户名',width: '8%'},
            {field: 'createTime', title: '创建时间',width: '10%'},
            {field: 'updateTime', title: '更新时间',width: '10%'},
            {field: 'remark', title: '备注',width: '12%'},
            {toolbar: '#toolrow', title: '操作',width: '10%'}
        ]],
        loading: true
    }

    // 加载table
    table.render(options);

    // 监听头部工具条
    table.on('toolbar(ezreal_db_table)', function(obj){
        switch(obj.event){
            case 'search':
                // 重载表格
                table.reload('ezreal_db_table', {
                    where: {
                        search : $("#search").val()
                    },
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                });
                break;
            case 'add':
                layer.open({
                    title: '新增',
                    type: 2,
                    content: [
                        '/view/config/ezreal_db_add',
                        'no'
                    ],
                    resize: false,
                    move: false,
                    area: ['600px', '600px'],
                    end: function(){
                        // 关闭后重新渲染table数据
                        table.reload("ezreal_db_table", options);
                    }
                });
                break;
            case 'delBatch':
                // 获取checkbox
                var checkbox = table.checkStatus(obj.config.id);
                debugger;
                var data = checkbox.data;
                var length = data.length;
                if(length <= 0){
                    layer.msg("至少选择一行数据才能删除", {icon: 5});
                    return;
                }
                var idsArr = new Array(length);
                for(var i = 0; i < length; i++){
                    idsArr[i] = data[i].id;
                }
                idsArr.join();
                // 提交删除请求
                layer.confirm('确认删除(删除后不可恢复)?', {
                    icon: 3,
                    title:'提示',
                    resize: false,
                    move: false
                }, function(index){
                    $.ajax({
                        url : "/ezreal_db/del/" + idsArr,
                        dataType : "json",
                        success : function(response){
                            if(response.code == 200){
                                layer.msg(response.msg, {icon: 6});
                                // 重载table
                                table.reload("ezreal_db_table", options);
                            } else {
                                layer.msg(response.msg, {icon: 5});
                            }
                        },
                        error : function(){
                            layer.msg('服务器错误,请稍后重试', {icon: 5});
                        }
                    });
                    layer.close(index);
                });
                break;
        };
    });

    // 监听列工具条
    table.on('tool(ezreal_db_table)', function(obj){
        var data = obj.data;
        var layEvent = obj.event;

        switch(layEvent){
            case 'connection': // 连接
                $.ajax({
                    url : "/ezreal_db/connection/" + data.id,
                    type : "get",
                    dataType: "json",
                    success : function(response){
                        if(response.code == 200 && response.data == true){
                            layer.msg("数据库可以连接", {icon: 6});
                        } else {
                            layer.msg("数据库无法连接", {icon: 5});
                        }
                    },
                    error : function(){
                        layer.msg('服务器错误,请稍后重试', {icon: 5});
                    }
                });
                break;
            case 'edit': // 编辑
                layer.open({
                    title: '修改',
                    type: 2,
                    content: [
                        '/view/config/ezreal_db_edit',
                        'no'
                    ],
                    resize: false,
                    move: false,
                    area: ['600px', '600px'],
                    success: function(layero, index){
                        // 获取弹出页面的iframe对象
                        var iframeWin = window[layero.find('iframe')[0]['name']];
                        // 回显数据
                        iframeWin.showData(data, index);
                    },
                    end: function(){
                        // 关闭后重新渲染table数据
                        table.reload("ezreal_db_table", options);
                    }
                });
                break;
            case 'del': // 删除
                layer.confirm('确认删除(删除后不可恢复)?', {
                    icon: 3,
                    title:'提示',
                    resize: false,
                    move: false
                },
                function(index){
                    $.ajax({
                        url : "/ezreal_db/del/" + data.id,
                        dataType : "json",
                        success : function(response){
                            if(response.code == 200){
                                layer.msg(response.msg, {icon: 6});
                                // 重载table
                                table.reload("ezreal_db_table", options);
                            } else {
                                layer.msg(response.msg, {icon: 5});
                            }
                        },
                        error : function(){
                            layer.msg('服务器错误,请稍后重试', {icon: 5});
                        }
                    });
                    layer.close(index);
                });
                break;
            default :
                break;
        }

    });

});