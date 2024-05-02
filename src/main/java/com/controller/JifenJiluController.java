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

import com.entity.JifenJiluEntity;

import com.service.JifenJiluService;
import com.entity.view.JifenJiluView;
import com.service.HuiyuanService;
import com.entity.HuiyuanEntity;
import com.service.YonghuService;
import com.entity.YonghuEntity;

import com.utils.PageUtils;
import com.utils.R;

/**
 * 积分记录
 * 后端接口
 * @author
 * @email
 */
@RestController
@Controller
@RequestMapping("/jifenJilu")
public class JifenJiluController {
    private static final Logger logger = LoggerFactory.getLogger(JifenJiluController.class);

    @Autowired
    private JifenJiluService jifenJiluService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;



    //级联表service
    @Autowired
    private HuiyuanService huiyuanService;
    @Autowired
    private YonghuService yonghuService;


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
        PageUtils page = jifenJiluService.queryPage(params);

        //字典表数据转换
        List<JifenJiluView> list =(List<JifenJiluView>)page.getList();
        for(JifenJiluView c:list){
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
        JifenJiluEntity jifenJilu = jifenJiluService.selectById(id);
        if(jifenJilu !=null){
            //entity转view
            JifenJiluView view = new JifenJiluView();
            BeanUtils.copyProperties( jifenJilu , view );//把实体数据重构到view中

            //级联表
            HuiyuanEntity huiyuan = huiyuanService.selectById(jifenJilu.getHuiyuanId());
            if(huiyuan != null){
                BeanUtils.copyProperties( huiyuan , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                view.setHuiyuanId(huiyuan.getId());
            }
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(jifenJilu.getYonghuId());
            if(yonghu != null){
                BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                view.setYonghuId(yonghu.getId());
            }
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
    public R save(@RequestBody JifenJiluEntity jifenJilu, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,jifenJilu:{}",this.getClass().getName(),jifenJilu.toString());
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if("用户".equals(role)){
            jifenJilu.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        }else{
            jifenJilu.setYonghuId(0);//操作人id为0的  就是管理员操作
        }
        jifenJilu.setInsertTime(new Date());
        jifenJilu.setCreateTime(new Date());
        jifenJiluService.insert(jifenJilu);

        HuiyuanEntity huiyuanEntity = huiyuanService.selectById(jifenJilu.getHuiyuanId());

        if(jifenJilu.getJifenJiluTypes() == 1){//使用积分
            huiyuanEntity.setJifen(huiyuanEntity.getJifen()-jifenJilu.getJifenJiluJifen());
        }else if(jifenJilu.getJifenJiluTypes() == 2){//增加积分
            huiyuanEntity.setJifen(huiyuanEntity.getJifen()+jifenJilu.getJifenJiluJifen());
        }
        huiyuanService.updateById(huiyuanEntity);

        return R.ok();
    }

    /**
     * 后端修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody JifenJiluEntity jifenJilu, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,jifenJilu:{}",this.getClass().getName(),jifenJilu.toString());
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if("用户".equals(role)){
            jifenJilu.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        }else{
            jifenJilu.setYonghuId(0);//操作人id为0的  就是管理员操作
        }


        // 处理原先积分
        JifenJiluEntity oldJifenJiluEntity = jifenJiluService.selectById(jifenJilu.getId());//查出来原始数据
        Integer oldJifenJiluJifen = oldJifenJiluEntity.getJifenJiluJifen();// 老的积分
        Integer oldJifenJiluTypes = oldJifenJiluEntity.getJifenJiluTypes();// 老的积分类型 1:使用  2:增加
        HuiyuanEntity oldHuiyuanEntity = huiyuanService.selectById(oldJifenJiluEntity.getHuiyuanId());
        boolean oldFlag = true;
        if(oldHuiyuanEntity != null){
            if(oldJifenJiluTypes == 1){//使用
                oldHuiyuanEntity.setJifen(oldJifenJiluJifen + oldHuiyuanEntity.getJifen());//原先使用的 加上
            }else if(oldJifenJiluTypes == 2){//增加积分
                oldHuiyuanEntity.setJifen(oldJifenJiluJifen - oldHuiyuanEntity.getJifen());//原先加上的 减掉
            }
        }else{
            oldFlag = false;
        }

        boolean newFlag = true; // 修改的是同一个人,就不更改新的,不是同一个人,就更改新的
        HuiyuanEntity huiyuanEntity = huiyuanService.selectById(jifenJilu.getHuiyuanId());
        if(jifenJilu.getHuiyuanId() ==  oldJifenJiluEntity.getHuiyuanId()){//修改的是同一个人
            if(jifenJilu.getJifenJiluTypes() == 1){// 1:使用  2:增加
                oldHuiyuanEntity.setJifen( oldHuiyuanEntity.getJifen() - jifenJilu.getJifenJiluJifen());
            }else if(jifenJilu.getJifenJiluTypes() == 2){
                oldHuiyuanEntity.setJifen( oldHuiyuanEntity.getJifen() + jifenJilu.getJifenJiluJifen());
            }
            newFlag = false;
        }else{ // 修改的不是同一个人
            // 处理现在积分
            if(jifenJilu.getJifenJiluTypes() == 1){// 1:使用  2:增加
                huiyuanEntity.setJifen( huiyuanEntity.getJifen() - jifenJilu.getJifenJiluJifen());
            }else if(jifenJilu.getJifenJiluTypes() == 2){
                huiyuanEntity.setJifen( huiyuanEntity.getJifen() + jifenJilu.getJifenJiluJifen());
            }
        }

        if(oldFlag){
            huiyuanService.updateById(oldHuiyuanEntity);// 更新原先
        }
        if(newFlag){
            huiyuanService.updateById(huiyuanEntity);// 更新现在

        }
        jifenJiluService.updateById(jifenJilu);//根据id更新
        return R.ok();

    }



    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        jifenJiluService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }






}

