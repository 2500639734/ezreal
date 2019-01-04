package com.ezreal.controller.view;

import com.ezreal.common.util.StringBufferUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: shenke
 * @date: 2018/12/7 23:15
 * @description: 模块视图控制器
 */
@Api(description = "模块视图接口")
@Controller
@RequestMapping("/view")
public class ViewController {

    /**
     * 跳转到指定模块页面
     * @param pageName 模块名称
     * @param pageName 页面名称
     * @return
     */
    @ApiOperation(value = "跳转到指定模块的指定页面", notes = "跳转到指定模块的指定页面")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "moduleName", value = "模块名称", required = true, dataType = "String", paramType = "path"),
        @ApiImplicitParam(name = "pageName", value = "页面名称", required = true, dataType = "String", paramType = "path")
    })
    @GetMapping("/{moduleName}/{pageName}")
    public String showConfigPage(@PathVariable String moduleName, @PathVariable String pageName){
        return StringBufferUtil.getStringBuffer()
                .append("/").append(moduleName)
                .append("/").append(pageName).toString();
    }

}
