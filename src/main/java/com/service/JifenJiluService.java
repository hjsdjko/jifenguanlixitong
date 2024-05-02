package com.service;

import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.JifenJiluEntity;
import java.util.Map;

/**
 * 积分记录 服务类
 */
public interface JifenJiluService extends IService<JifenJiluEntity> {

    /**
    * @param params 查询参数
    * @return 带分页的查询出来的数据
    */
     PageUtils queryPage(Map<String, Object> params);
}