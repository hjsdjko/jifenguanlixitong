package com.controller;


import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;

import com.entity.*;
import com.service.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;

import com.utils.StringUtil;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;

import com.entity.view.GoodsDuihuanView;

import com.utils.PageUtils;
import com.utils.R;

/**
 * 商品兑换
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/goodsDuihuan")
public class GoodsDuihuanController {
    private static final Logger logger = LoggerFactory.getLogger(GoodsDuihuanController.class);

    @Autowired
    private GoodsDuihuanService goodsDuihuanService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;



    //级联表service
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private HuiyuanService huiyuanService;
    @Autowired
    private YonghuService yonghuService;
    @Autowired
    private JifenJiluService jifenJiluService;


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
        PageUtils page = goodsDuihuanService.queryPage(params);

        //字典表数据转换
        List<GoodsDuihuanView> list =(List<GoodsDuihuanView>)page.getList();
        for(GoodsDuihuanView c:list){
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
        GoodsDuihuanEntity goodsDuihuan = goodsDuihuanService.selectById(id);
        if(goodsDuihuan !=null){
            //entity转view
            GoodsDuihuanView view = new GoodsDuihuanView();
            BeanUtils.copyProperties( goodsDuihuan , view );//把实体数据重构到view中

            //级联表
            GoodsEntity goods = goodsService.selectById(goodsDuihuan.getGoodsId());
            if(goods != null){
                BeanUtils.copyProperties( goods , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                view.setGoodsId(goods.getId());
            }
            //级联表
            HuiyuanEntity huiyuan = huiyuanService.selectById(goodsDuihuan.getHuiyuanId());
            if(huiyuan != null){
                BeanUtils.copyProperties( huiyuan , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                view.setHuiyuanId(huiyuan.getId());
            }
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(goodsDuihuan.getYonghuId());
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
    public R save(@RequestBody GoodsDuihuanEntity goodsDuihuan, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,goodsDuihuan:{}",this.getClass().getName(),goodsDuihuan.toString());
        Date date = new Date();

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if("用户".equals(role)){
            goodsDuihuan.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        }else{
            goodsDuihuan.setYonghuId(0);//操作人id为0的  就是管理员操作
        }

        GoodsEntity goodsEntity = goodsService.selectById(goodsDuihuan.getGoodsId());
        if(goodsEntity == null){
            return R.error(511,"查不到商品");
        }
        if(goodsEntity.getGoodsNumber() <= 0){
            return R.error(511,"商品没有了");
        }

        HuiyuanEntity huiyuanEntity = huiyuanService.selectById(goodsDuihuan.getHuiyuanId());
        if(huiyuanEntity == null){
            return R.error(511,"查不到会员");
        }

        Integer balance = huiyuanEntity.getJifen() - goodsEntity.getSuoxuJifen();
        if(balance < 0){
            return R.error(511,"会员积分不够商品所需积分");
        }

        goodsDuihuan.setInsertTime(date);
        goodsDuihuan.setCreateTime(date);
        goodsDuihuanService.insert(goodsDuihuan);

        huiyuanEntity.setJifen(balance);
        huiyuanService.updateById(huiyuanEntity);

        JifenJiluEntity jifenJiluEntity = new JifenJiluEntity();
        if("用户".equals(role)){
            jifenJiluEntity.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        }else{
            jifenJiluEntity.setYonghuId(0);//操作人id为0的  就是管理员操作
        }
        jifenJiluEntity.setCreateTime(date);
        jifenJiluEntity.setInsertTime(date);
        jifenJiluEntity.setHuiyuanId(huiyuanEntity.getId());
        jifenJiluEntity.setJifenJiluTypes(1);
        jifenJiluEntity.setJifenJiluJifen(goodsEntity.getSuoxuJifen());
        jifenJiluEntity.setJifenJiluContent("购买"+goodsEntity.getGoodsName()+"使用了"+goodsEntity.getSuoxuJifen()+"积分");
        jifenJiluService.insert(jifenJiluEntity);

        goodsEntity.setGoodsNumber(goodsEntity.getGoodsNumber()-1);
        goodsService.updateById(goodsEntity); //库存减一
        return R.ok();
    }

//    /**
//    * 后端修改
//    */
//    @RequestMapping("/update")
//    public R update(@RequestBody GoodsDuihuanEntity goodsDuihuan, HttpServletRequest request){
//        logger.debug("update方法:,,Controller:{},,goodsDuihuan:{}",this.getClass().getName(),goodsDuihuan.toString());
//        //根据字段查询是否有相同数据
//        Wrapper<GoodsDuihuanEntity> queryWrapper = new EntityWrapper<GoodsDuihuanEntity>()
//            .notIn("id",goodsDuihuan.getId())
//            .andNew()
//            .eq("huiyuan_id", goodsDuihuan.getHuiyuanId())
//            .eq("goods_id", goodsDuihuan.getGoodsId())
//            .eq("yonghu_id", goodsDuihuan.getYonghuId())
//            ;
//        logger.info("sql语句:"+queryWrapper.getSqlSegment());
//        GoodsDuihuanEntity goodsDuihuanEntity = goodsDuihuanService.selectOne(queryWrapper);
//        if(goodsDuihuanEntity==null){
//            //  String role = String.valueOf(request.getSession().getAttribute("role"));
//            //  if("".equals(role)){
//            //      goodsDuihuan.set
//            //  }
//            goodsDuihuanService.updateById(goodsDuihuan);//根据id更新
//            return R.ok();
//        }else {
//            return R.error(511,"表中有相同数据");
//        }
//    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        goodsDuihuanService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }






}

