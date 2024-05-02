package com.entity.view;

import com.entity.GoodsDuihuanEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品兑换
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("goods_duihuan")
public class GoodsDuihuanView extends GoodsDuihuanEntity implements Serializable {
    private static final long serialVersionUID = 1L;



		//级联表 goods
			/**
			* 商品名字
			*/
			private String goodsName;
			/**
			* 商品种类
			*/
			private Integer goodsTypes;
				/**
				* 商品种类的值
				*/
				private String goodsValue;
			/**
			* 商品数量
			*/
			private Integer goodsNumber;
			/**
			* 商品图片
			*/
			private String goodsPhoto;
			/**
			* 单位
			*/
			private String danwei;
			/**
			* 价格
			*/
			private Double danjia;
			/**
			* 所需积分
			*/
			private Integer suoxuJifen;
			/**
			* 商品详情
			*/
			private String goodsContent;
			/**
			* 是否删除
			*/
			private Integer flag;

		//级联表 huiyuan
			/**
			* 会员姓名
			*/
			private String huiyuanName;
			/**
			* 会员手机号
			*/
			private String huiyuanPhone;
			/**
			* 会员身份证号
			*/
			private String huiyuanIdNumber;
			/**
			* 会员照片
			*/
			private String huiyuanPhoto;
//			/**
//			* 性别
//			*/
//			private Integer sexTypes;
//				/**
//				* 性别的值
//				*/
//				private String sexValue;
			/**
			* 积分
			*/
			private Integer jifen;

		//级联表 yonghu
			/**
			* 员工姓名
			*/
			private String yonghuName;
			/**
			* 员工手机号
			*/
			private String yonghuPhone;
			/**
			* 员工身份证号
			*/
			private String yonghuIdNumber;
			/**
			* 员工照片
			*/
			private String yonghuPhoto;
//			/**
//			* 性别
//			*/
//			private Integer sexTypes;
//				/**
//				* 性别的值
//				*/
//				private String sexValue;

	public GoodsDuihuanView() {

	}

	public GoodsDuihuanView(GoodsDuihuanEntity goodsDuihuanEntity) {
		try {
			BeanUtils.copyProperties(this, goodsDuihuanEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
















				//级联表的get和set goods
					/**
					* 获取： 商品名字
					*/
					public String getGoodsName() {
						return goodsName;
					}
					/**
					* 设置： 商品名字
					*/
					public void setGoodsName(String goodsName) {
						this.goodsName = goodsName;
					}
					/**
					* 获取： 商品种类
					*/
					public Integer getGoodsTypes() {
						return goodsTypes;
					}
					/**
					* 设置： 商品种类
					*/
					public void setGoodsTypes(Integer goodsTypes) {
						this.goodsTypes = goodsTypes;
					}


						/**
						* 获取： 商品种类的值
						*/
						public String getGoodsValue() {
							return goodsValue;
						}
						/**
						* 设置： 商品种类的值
						*/
						public void setGoodsValue(String goodsValue) {
							this.goodsValue = goodsValue;
						}
					/**
					* 获取： 商品数量
					*/
					public Integer getGoodsNumber() {
						return goodsNumber;
					}
					/**
					* 设置： 商品数量
					*/
					public void setGoodsNumber(Integer goodsNumber) {
						this.goodsNumber = goodsNumber;
					}
					/**
					* 获取： 商品图片
					*/
					public String getGoodsPhoto() {
						return goodsPhoto;
					}
					/**
					* 设置： 商品图片
					*/
					public void setGoodsPhoto(String goodsPhoto) {
						this.goodsPhoto = goodsPhoto;
					}
					/**
					* 获取： 单位
					*/
					public String getDanwei() {
						return danwei;
					}
					/**
					* 设置： 单位
					*/
					public void setDanwei(String danwei) {
						this.danwei = danwei;
					}
					/**
					* 获取： 价格
					*/
					public Double getDanjia() {
						return danjia;
					}
					/**
					* 设置： 价格
					*/
					public void setDanjia(Double danjia) {
						this.danjia = danjia;
					}
					/**
					* 获取： 所需积分
					*/
					public Integer getSuoxuJifen() {
						return suoxuJifen;
					}
					/**
					* 设置： 所需积分
					*/
					public void setSuoxuJifen(Integer suoxuJifen) {
						this.suoxuJifen = suoxuJifen;
					}
					/**
					* 获取： 商品详情
					*/
					public String getGoodsContent() {
						return goodsContent;
					}
					/**
					* 设置： 商品详情
					*/
					public void setGoodsContent(String goodsContent) {
						this.goodsContent = goodsContent;
					}
					/**
					* 获取： 是否删除
					*/
					public Integer getFlag() {
						return flag;
					}
					/**
					* 设置： 是否删除
					*/
					public void setFlag(Integer flag) {
						this.flag = flag;
					}







				//级联表的get和set huiyuan
					/**
					* 获取： 会员姓名
					*/
					public String getHuiyuanName() {
						return huiyuanName;
					}
					/**
					* 设置： 会员姓名
					*/
					public void setHuiyuanName(String huiyuanName) {
						this.huiyuanName = huiyuanName;
					}
					/**
					* 获取： 会员手机号
					*/
					public String getHuiyuanPhone() {
						return huiyuanPhone;
					}
					/**
					* 设置： 会员手机号
					*/
					public void setHuiyuanPhone(String huiyuanPhone) {
						this.huiyuanPhone = huiyuanPhone;
					}
					/**
					* 获取： 会员身份证号
					*/
					public String getHuiyuanIdNumber() {
						return huiyuanIdNumber;
					}
					/**
					* 设置： 会员身份证号
					*/
					public void setHuiyuanIdNumber(String huiyuanIdNumber) {
						this.huiyuanIdNumber = huiyuanIdNumber;
					}
					/**
					* 获取： 会员照片
					*/
					public String getHuiyuanPhoto() {
						return huiyuanPhoto;
					}
					/**
					* 设置： 会员照片
					*/
					public void setHuiyuanPhoto(String huiyuanPhoto) {
						this.huiyuanPhoto = huiyuanPhoto;
					}
//					/**
//					* 获取： 性别
//					*/
//					public Integer getSexTypes() {
//						return sexTypes;
//					}
//					/**
//					* 设置： 性别
//					*/
//					public void setSexTypes(Integer sexTypes) {
//						this.sexTypes = sexTypes;
//					}


//						/**
//						* 获取： 性别的值
//						*/
//						public String getSexValue() {
//							return sexValue;
//						}
//						/**
//						* 设置： 性别的值
//						*/
//						public void setSexValue(String sexValue) {
//							this.sexValue = sexValue;
//						}
					/**
					* 获取： 积分
					*/
					public Integer getJifen() {
						return jifen;
					}
					/**
					* 设置： 积分
					*/
					public void setJifen(Integer jifen) {
						this.jifen = jifen;
					}










				//级联表的get和set yonghu
					/**
					* 获取： 员工姓名
					*/
					public String getYonghuName() {
						return yonghuName;
					}
					/**
					* 设置： 员工姓名
					*/
					public void setYonghuName(String yonghuName) {
						this.yonghuName = yonghuName;
					}
					/**
					* 获取： 员工手机号
					*/
					public String getYonghuPhone() {
						return yonghuPhone;
					}
					/**
					* 设置： 员工手机号
					*/
					public void setYonghuPhone(String yonghuPhone) {
						this.yonghuPhone = yonghuPhone;
					}
					/**
					* 获取： 员工身份证号
					*/
					public String getYonghuIdNumber() {
						return yonghuIdNumber;
					}
					/**
					* 设置： 员工身份证号
					*/
					public void setYonghuIdNumber(String yonghuIdNumber) {
						this.yonghuIdNumber = yonghuIdNumber;
					}
					/**
					* 获取： 员工照片
					*/
					public String getYonghuPhoto() {
						return yonghuPhoto;
					}
					/**
					* 设置： 员工照片
					*/
					public void setYonghuPhoto(String yonghuPhoto) {
						this.yonghuPhoto = yonghuPhoto;
					}
//					/**
//					* 获取： 性别
//					*/
//					public Integer getSexTypes() {
//						return sexTypes;
//					}
//					/**
//					* 设置： 性别
//					*/
//					public void setSexTypes(Integer sexTypes) {
//						this.sexTypes = sexTypes;
//					}
//
//
//						/**
//						* 获取： 性别的值
//						*/
//						public String getSexValue() {
//							return sexValue;
//						}
//						/**
//						* 设置： 性别的值
//						*/
//						public void setSexValue(String sexValue) {
//							this.sexValue = sexValue;
//						}




}
