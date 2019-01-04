package com.ezreal.service;

import com.ezreal.pojo.EzrealClassify;

import java.util.List;

/**
 * @author: shenke
 * @date: 2018/12/15 17:51
 * @description: 分类接口
 */
public interface EzrealClassifyService {

    /**
     * 查询指定父分类下的子分类
     * @param parentId 父分类id
     * @return 子分类
     */
    List<EzrealClassify> childrenList(Integer parentId);

}
