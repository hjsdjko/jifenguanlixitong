package com.service.impl;

import com.utils.StringUtil;
import org.springframework.stereotype.Service;
import java.lang.reflect.Field;
import java.util.*;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import com.utils.PageUtils;
import com.utils.Query;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;

import com.dao.GoodsDuihuanDao;
import com.entity.GoodsDuihuanEntity;
import com.service.GoodsDuihuanService;
import com.entity.view.GoodsDuihuanView;

/**
 * 商品兑换 服务实现类
 */
@Service("goodsDuihuanService")
@Transactional
public class GoodsDuihuanServiceImpl extends ServiceImpl<GoodsDuihuanDao, GoodsDuihuanEntity> implements GoodsDuihuanService {

    @Override
    public PageUtils queryPage(Map<String,Object> params) {
        if(params != null && (params.get("limit") == null || params.get("page") == null)){
            params.put("page","1");
            params.put("limit","10");
        }
        Page<GoodsDuihuanView> page =new Query<GoodsDuihuanView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page,params));
        return new PageUtils(page);
    }


}
