package com.ezreal.service.impl;

import com.ezreal.common.enums.DbTypeEnum;
import com.ezreal.common.util.StringBufferUtil;
import com.ezreal.pojo.DbConfig;
import com.ezreal.pojo.DruidConfig;
import com.ezreal.pojo.dto.EzrealDbDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author: shenke
 * @date: 2019/1/1 00:10
 * @description:
 */
@Component
public class DruidConfigImpl extends DbConfigAbstract {

    @Override
    public DbConfig buildDbConfig(EzrealDbDto ezrealDbDto) {
        String host = ezrealDbDto.getDbHost();
        String dbName = ezrealDbDto.getDbName();
        String port = ezrealDbDto.getDbPort();
        String username = ezrealDbDto.getUsername();
        String password = ezrealDbDto.getPassword();
        String typeName = ezrealDbDto.getTypeName();
        if(StringUtils.isEmpty(host) || StringUtils.isEmpty(dbName) || port == null ||
                StringUtils.isEmpty(username) || StringUtils.isEmpty(password) || StringUtils.isEmpty(typeName)){
            return null;
        }
        if(DbTypeEnum.mysql.toString().equals(typeName)){
            DruidConfig druidConfig = new DruidConfig();
            BeanUtils.copyProperties(ezrealDbDto, druidConfig);

            String driver = "com.mysql.jdbc.Driver";
            String url = StringBufferUtil.getStringBuffer().append("jdbc:mysql://").append(host).append(":")
                    .append(port).append("/").append(dbName).append("?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true")
                    .toString();

            druidConfig.setDriver(driver);
            druidConfig.setUrl(url);

            return druidConfig;
        }
        return null;
    }

}
