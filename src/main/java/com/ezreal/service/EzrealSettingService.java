package com.ezreal.service;

import com.ezreal.pojo.EzrealSetting;

/**
 * @author: shenke
 * @date: 2018/12/31 08:55
 * @description: 系统设置接口
 */
public interface EzrealSettingService {

    /**
     * 获取系统设置
     * @param userId 用户id
     * @return 用户设置
     */
    EzrealSetting getEzrealSetting(Integer userId);

    /**
     * 系统设置
     * @param ezrealSetting 系统设置实体
     * @return true 成功 | false 失败
     */
    boolean setEzrealSetting(EzrealSetting ezrealSetting);

}
