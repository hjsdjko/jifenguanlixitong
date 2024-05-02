package com.service;

import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.GoodsDuihuanEntity;
import java.util.Map;

/**
 * 商品兑换 服务类
 */
public interface GoodsDuihuanService extends IService<GoodsDuihuanEntity> {

    /**
    * @param params 查询参数
    * @return 带分页的查询出来的数据
    */
     PageUtils queryPage(Map<String, Object> params);
}