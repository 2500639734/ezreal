package com.ezreal.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ezreal.common.util.DruidUtil;
import com.ezreal.mapper.EzrealDbMapper;
import com.ezreal.pojo.DbConfig;
import com.ezreal.pojo.DruidConfig;
import com.ezreal.pojo.EzrealDb;
import com.ezreal.pojo.dto.EzrealDbDto;
import com.ezreal.pojo.vo.EzrealDbVo;
import com.ezreal.service.DbConfigService;
import com.ezreal.service.EzrealDbService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @author: shenke
 * @date: 2018/12/9 19:48
 * @description: 数据库配置接口实现
 */
@Service
public class EzrealDbServiceImpl extends ServiceImpl<EzrealDbMapper, EzrealDb> implements EzrealDbService {

    @Autowired
    private EzrealDbMapper ezrealDbMapper;

    @Autowired
    private DbConfigService dbConfigService;

    @Override
    public List<EzrealDbVo> findList(Page<EzrealDbVo> page, String search) {
        return ezrealDbMapper.findList(page, search);
    }

    @Override
    public boolean add(EzrealDb ezrealDb) {
        if(ezrealDb == null){
            return false;
        }
        return save(ezrealDb);
    }

    @Override
    public boolean edit(EzrealDb ezrealDb) {
        if(ezrealDb == null || StringUtils.isEmpty(ezrealDb.getId())){
            return false;
        }
        return updateById(ezrealDb);
    }

    @Override
    public boolean del(Integer[] ids) {
        if(ids == null || ids.length <= 0){
            return false;
        }
        return removeByIds(Arrays.asList(ids));
    }

    @Override
    public boolean connection(Integer id) {
        EzrealDbVo ezrealDbVo = ezrealDbMapper.findById(id);
        EzrealDbDto ezrealDbDto = new EzrealDbDto();
        BeanUtils.copyProperties(ezrealDbVo, ezrealDbDto);
        DbConfig dbConfig = dbConfigService.buildDbConfig(ezrealDbDto);
        DruidConfig druidConfig = (DruidConfig) dbConfig;
        DruidUtil.openConnection(druidConfig);
        if(DruidUtil.getCurrentConnection() != null){
            DruidUtil.close();
            return true;
        } else {
            return false;
        }
    }

}
