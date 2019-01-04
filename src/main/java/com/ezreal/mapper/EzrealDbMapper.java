package com.ezreal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ezreal.pojo.EzrealDb;
import com.ezreal.pojo.vo.EzrealDbVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: shenke
 * @date: 2018/12/9 19:24
 * @description: 数据库配置Mapper接口
 */
public interface EzrealDbMapper extends BaseMapper<EzrealDb> {

    /**
     * 根据主键查询数据库配置
     * @param id 数据库配置主键
     * @return 数据库配置
     */
    EzrealDbVo findById(@Param("id") Integer id);

    /**
     * 获取所有数据库配置
     * @param page 分页对象，可以作为 xml 参数直接使用，传递参数 Page 即自动分页
     * @param search 查询条件(名称)
     * @return 数据库配置列表
     */
    List<EzrealDbVo> findList(Page page, @Param("search") String search);

}
