package com.ezreal.controller.config;

import com.ezreal.common.pojo.Response;
import com.ezreal.common.util.BindingResultUtil;
import com.ezreal.pojo.EzrealSetting;
import com.ezreal.service.EzrealSettingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author: shenke
 * @date: 2018/12/31 09:04
 * @description: 系统设置接口
 */
@Api(description = "系统设置数据接口")
@RestController
@RequestMapping("/ezreal_setting")
public class EzrealSettingController {

    @Autowired
    private EzrealSettingService ezrealSettingService;

    @GetMapping("/getEzrealSetting")
    @ApiOperation(value = "获取系统设置", notes = "获取系统设置")
    public Response<EzrealSetting> getEzrealSetting(){
        // TODO 暂时固定为1
        EzrealSetting ezrealSetting = ezrealSettingService.getEzrealSetting(1);
        if(ezrealSetting == null){
            return Response.buidFailure(ezrealSetting);
        }
        return Response.buidSuccess(ezrealSetting);
    }

    @PostMapping("/setEzrealSetting")
    @ApiOperation(value = "系统设置", notes = "系统设置")
    public Response<EzrealSetting> setEzrealSetting(@ApiParam @RequestBody @Valid EzrealSetting ezrealSetting, BindingResult bindingResult){
        String paramsErrorMsg = BindingResultUtil.checkParamsAdd(bindingResult);
        if(!StringUtils.isEmpty(paramsErrorMsg)){
            return Response.buidParamError(paramsErrorMsg);
        }
        boolean isSet = ezrealSettingService.setEzrealSetting(ezrealSetting);
        if(!isSet){
            return Response.buidFailure(ezrealSetting);
        }
        return Response.buidSuccess(ezrealSetting);
    }

}
