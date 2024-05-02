package com.controller;


import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.StringUtil;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;

import com.entity.GonggaoEntity;

import com.service.GonggaoService;
import com.entity.view.GonggaoView;

import com.utils.PageUtils;
import com.utils.R;

/**
 * 公告
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/gonggao")
public class GonggaoController {
    private static final Logger logger = LoggerFactory.getLogger(GonggaoController.class);

    @Autowired
    private GonggaoService gonggaoService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;



    //级联表service


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
     
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(StringUtil.isNotEmpty(role) && "用户".equals(role)){
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        }
        params.put("orderBy","id");
        PageUtils page = gonggaoService.queryPage(params);

        //字典表数据转换
        List<GonggaoView> list =(List<GonggaoView>)page.getList();
        for(GonggaoView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        GonggaoEntity gonggao = gonggaoService.selectById(id);
        if(gonggao !=null){
            //entity转view
            GonggaoView view = new GonggaoView();
            BeanUtils.copyProperties( gonggao , view );//把实体数据重构到view中

            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody GonggaoEntity gonggao, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,gonggao:{}",this.getClass().getName(),gonggao.toString());
        Wrapper<GonggaoEntity> queryWrapper = new EntityWrapper<GonggaoEntity>()
            .eq("gonggao_name", gonggao.getGonggaoName())
            .eq("gonggao_types", gonggao.getGonggaoTypes())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        GonggaoEntity gonggaoEntity = gonggaoService.selectOne(queryWrapper);
        if(gonggaoEntity==null){
            gonggao.setInsertTime(new Date());
            gonggao.setCreateTime(new Date());
        //  String role = String.valueOf(request.getSession().getAttribute("role"));
        //  if("".equals(role)){
        //      gonggao.set
        //  }
            gonggaoService.insert(gonggao);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody GonggaoEntity gonggao, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,gonggao:{}",this.getClass().getName(),gonggao.toString());
        //根据字段查询是否有相同数据
        Wrapper<GonggaoEntity> queryWrapper = new EntityWrapper<GonggaoEntity>()
            .notIn("id",gonggao.getId())
            .andNew()
            .eq("gonggao_name", gonggao.getGonggaoName())
            .eq("gonggao_types", gonggao.getGonggaoTypes())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        GonggaoEntity gonggaoEntity = gonggaoService.selectOne(queryWrapper);
        if(gonggaoEntity==null){
            //  String role = String.valueOf(request.getSession().getAttribute("role"));
            //  if("".equals(role)){
            //      gonggao.set
            //  }
            gonggaoService.updateById(gonggao);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        gonggaoService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }






}

