package com.entity.view;

import com.entity.JifenJiluEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 积分记录
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("jifen_jilu")
public class JifenJiluView extends JifenJiluEntity implements Serializable {
    private static final long serialVersionUID = 1L;
		/**
		* 类型的值
		*/
		private String jifenJiluValue;



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

	public JifenJiluView() {

	}

	public JifenJiluView(JifenJiluEntity jifenJiluEntity) {
		try {
			BeanUtils.copyProperties(this, jifenJiluEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



			/**
			* 获取： 类型的值
			*/
			public String getJifenJiluValue() {
				return jifenJiluValue;
			}
			/**
			* 设置： 类型的值
			*/
			public void setJifenJiluValue(String jifenJiluValue) {
				this.jifenJiluValue = jifenJiluValue;
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
