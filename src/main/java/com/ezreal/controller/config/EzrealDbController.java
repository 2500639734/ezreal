package com.ezreal.controller.config;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ezreal.common.pojo.LayuiResponse;
import com.ezreal.common.pojo.Response;
import com.ezreal.common.util.BindingResultUtil;
import com.ezreal.pojo.EzrealDb;
import com.ezreal.pojo.dto.EzrealDbDto;
import com.ezreal.pojo.vo.EzrealDbVo;
import com.ezreal.service.EzrealDbService;
import io.swagger.annotations.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: shenke
 * @date: 2018/12/9 19:53
 * @description:
 */
@Api(description = "数据库配置数据接口")
@RestController
@RequestMapping("/ezreal_db")
public class EzrealDbController {

    @Autowired
    private EzrealDbService ezrealDbService;

    @GetMapping("/list")
    @ApiOperation(value = "获取数据库配置列表", notes = "获取数据库配置列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "起始页", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "limit", value = "每页记录数", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "search", value = "查询条件(名称)", required = false, dataType = "String", paramType = "query")
    })
    public LayuiResponse<List<EzrealDbVo>> list(
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer limit,
            @RequestParam(required = false) String search){
        Page<EzrealDbVo> pageObj = new Page<>(page, limit);
        List<EzrealDbVo> ezrealDbVoList = ezrealDbService.findList(pageObj, search);
        return LayuiResponse.buidSuccess(pageObj.getTotal(), ezrealDbVoList);
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增数据库配置", notes = "新增数据库配置")
    public Response<Integer> add(@ApiParam @RequestBody @Valid EzrealDb ezrealDb, BindingResult bindingResult){
        String paramsErrorMsg = BindingResultUtil.checkParamsAdd(bindingResult);
        if(!StringUtils.isEmpty(paramsErrorMsg)){
            return Response.buidParamError(paramsErrorMsg);
        }
        boolean isDel = ezrealDbService.add(ezrealDb);
        return isDel ? Response.buidSuccess(ezrealDb.getId())
                : Response.buidFailure(null);
    }

    @PostMapping("/edit")
    @ApiOperation(value = "修改数据库配置", notes = "修改数据库配置")
    public Response<Integer> edit(@ApiParam @RequestBody @Valid EzrealDb ezrealDb, BindingResult bindingResult){
        String paramsErrorMsg = BindingResultUtil.checkParamsEdit(bindingResult);
        if(!StringUtils.isEmpty(paramsErrorMsg)){
            return Response.buidParamError(paramsErrorMsg);
        }
        boolean isDel = ezrealDbService.edit(ezrealDb);
        return isDel ? Response.buidSuccess(ezrealDb.getId())
                : Response.buidFailure(null);
    }

    @GetMapping("/del/{ids}")
    @ApiOperation(value = "删除数据库配置", notes = "删除数据库配置")
    @ApiImplicitParam(name = "ids", value = "数据库配置id数组", required = true, dataType = "int", paramType = "path")
    public Response<Integer[]> del(@PathVariable Integer[] ids){
        boolean isDel = ezrealDbService.del(ids);
        return isDel ? Response.buidSuccess(ids)
                : Response.buidFailure(null);
    }

    @GetMapping("/connection/{id}")
    @ApiOperation(value = "测试数据库连接", notes = "测试数据库连接")
    @ApiImplicitParam(name = "id", value = "测试数据库连接", required = true, dataType = "int", paramType = "path")
    public Response<Boolean> connection(@PathVariable Integer id){
        boolean isConnection = ezrealDbService.connection(id);
        return isConnection ? Response.buidSuccess(isConnection)
                : Response.buidFailure(isConnection);
    }

}
