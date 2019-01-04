package com.ezreal.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ezreal.pojo.EzrealDb;
import com.ezreal.pojo.dto.EzrealDbDto;
import com.ezreal.pojo.vo.EzrealDbVo;

import java.util.List;

/**
 * @author: shenke
 * @date: 2018/12/9 19:34
 * @description: 数据库配置接口
 */
public interface EzrealDbService {

    /**
     * 获取所有数据库配置
     * @param page 分页对象，可以作为 xml 参数直接使用，传递参数 Page 即自动分页
     * @param search 查询条件(名称)
     * @return 数据库配置列表
     */
    List<EzrealDbVo> findList(Page<EzrealDbVo> page, String search);

    /**
     * 新增数据库配置
     * @param ezrealDb 数据库配置实体
     * @return 成功||失败
     */
    boolean add(EzrealDb ezrealDb);

    /**
     * 修改数据库配置
     * @param ezrealDb 数据库配置实体
     * @return 成功||失败
     */
    boolean edit(EzrealDb ezrealDb);

    /**
     * 删除数据库配置
      * @param ids 删除id数组
     * @return 成功||失败
     */
    boolean del(Integer[] ids);

    /**
     * 测试连接
     * @param id 数据库配置主键
     * @return
     */
    boolean connection(Integer id);

}
