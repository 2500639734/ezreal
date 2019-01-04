package com.ezreal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ezreal.mapper.EzrealSettingMapper;
import com.ezreal.pojo.EzrealSetting;
import com.ezreal.service.EzrealSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author: shenke
 * @date: 2018/12/31 08:58
 * @description: 系统设置接口实现
 */
@Service
public class EzrealSettingServiceImpl extends ServiceImpl<EzrealSettingMapper, EzrealSetting> implements EzrealSettingService {

    @Autowired
    private EzrealSettingMapper ezrealSettingMapper;

    @Override
    public EzrealSetting getEzrealSetting(Integer userId) {
        QueryWrapper<EzrealSetting> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        return ezrealSettingMapper.selectOne(queryWrapper);
    }

    @Override
    public boolean setEzrealSetting(EzrealSetting ezrealSetting) {
        Integer userId = ezrealSetting.getUserId();
        if(userId == null){
            return false;
        }
        UpdateWrapper updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("user_id", userId);
        return update(ezrealSetting, updateWrapper);
    }

}
