package com.ezreal.controller.config;

import com.ezreal.common.pojo.Response;
import com.ezreal.pojo.EzrealClassify;
import com.ezreal.service.EzrealClassifyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: shenke
 * @date: 2018/12/15 18:42
 * @description: 分类数据接口
 */
@Api(description = "分类数据接口")
@RestController
@RequestMapping("/ezreal_classify")
public class EzrealClassifyController {

    @Autowired
    private EzrealClassifyService ezrealClassifyService;

    @GetMapping("/childrenList/{parentId}")
    @ApiOperation(value = "获取指定父分类下的子分类", notes = "获取指定父分类下的子分类")
    @ApiImplicitParam(name = "parentId", value = "父分类id", required = true, dataType = "int", paramType = "path")
    public Response<List<EzrealClassify>> childrenList(@PathVariable Integer parentId){
        return Response.buidSuccess(ezrealClassifyService.childrenList(parentId));
    }

}
