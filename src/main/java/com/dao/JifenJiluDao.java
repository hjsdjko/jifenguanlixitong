package com.dao;

import com.entity.JifenJiluEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.JifenJiluView;

/**
 * 积分记录 Dao 接口
 *
 * @author 
 */
public interface JifenJiluDao extends BaseMapper<JifenJiluEntity> {

   List<JifenJiluView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
