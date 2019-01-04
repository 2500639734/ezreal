package com.ezreal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ezreal.mapper.EzrealClassifyMapper;
import com.ezreal.pojo.EzrealClassify;
import com.ezreal.service.EzrealClassifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: shenke
 * @date: 2018/12/15 17:52
 * @description: 分类接口实现
 */
@Service
public class EzrealClassifyServiceImpl extends ServiceImpl<EzrealClassifyMapper, EzrealClassify> implements EzrealClassifyService {

    @Autowired
    private EzrealClassifyMapper ezrealClassifyMapper;

    @Override
    public List<EzrealClassify> childrenList(Integer parentId) {
        QueryWrapper<EzrealClassify> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", parentId);
        return ezrealClassifyMapper.selectList(queryWrapper);
    }
}
