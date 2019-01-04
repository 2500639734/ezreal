package com.ezreal.controller.view;

import com.ezreal.common.util.StringBufferUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author: shenke
 * @date: 2018/12/8 17:27
 * @description: 首页视图控制器
 */
@Api(description = "首页视图接口")
@Controller
public class IndexController {

    /**
     * 跳转到首页
     * @return
     */
    @ApiOperation(value = "跳转到首页", notes = "跳转到首页")
    @GetMapping("/")
    public String showIndex(){
        return StringBufferUtil.getStringBuffer().append("/index").toString();
    }

}
