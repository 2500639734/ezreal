package com.ezreal.service;

import com.ezreal.pojo.DbConfig;
import com.ezreal.pojo.EzrealDb;
import com.ezreal.pojo.dto.EzrealDbDto;

/**
 * @author: shenke
 * @date: 2018/12/31 23:33
 * @description: db接口
 */
public interface DbConfigService {

    /**
     *
     * @param ezrealDbDto
     * @return
     */
    DbConfig buildDbConfig(EzrealDbDto ezrealDbDto);

}
