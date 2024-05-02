package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * 积分记录
 *
 * @author 
 * @email
 */
@TableName("jifen_jilu")
public class JifenJiluEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public JifenJiluEntity() {

	}

	public JifenJiluEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @TableField(value = "id")

    private Integer id;


    /**
     * 会员
     */
    @TableField(value = "huiyuan_id")

    private Integer huiyuanId;


    /**
     * 操作人
     */
    @TableField(value = "yonghu_id")

    private Integer yonghuId;


    /**
     * 类型
     */
    @TableField(value = "jifen_jilu_types")

    private Integer jifenJiluTypes;


    /**
     * 积分
     */
    @TableField(value = "jifen_jilu_jifen")

    private Integer jifenJiluJifen;


    /**
     * 备注
     */
    @TableField(value = "jifen_jilu_content")

    private String jifenJiluContent;


    /**
     * 操作时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "create_time",fill = FieldFill.INSERT)

    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：会员
	 */
    public Integer getHuiyuanId() {
        return huiyuanId;
    }


    /**
	 * 获取：会员
	 */

    public void setHuiyuanId(Integer huiyuanId) {
        this.huiyuanId = huiyuanId;
    }
    /**
	 * 设置：操作人
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 获取：操作人
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 设置：类型
	 */
    public Integer getJifenJiluTypes() {
        return jifenJiluTypes;
    }


    /**
	 * 获取：类型
	 */

    public void setJifenJiluTypes(Integer jifenJiluTypes) {
        this.jifenJiluTypes = jifenJiluTypes;
    }
    /**
	 * 设置：积分
	 */
    public Integer getJifenJiluJifen() {
        return jifenJiluJifen;
    }


    /**
	 * 获取：积分
	 */

    public void setJifenJiluJifen(Integer jifenJiluJifen) {
        this.jifenJiluJifen = jifenJiluJifen;
    }
    /**
	 * 设置：备注
	 */
    public String getJifenJiluContent() {
        return jifenJiluContent;
    }


    /**
	 * 获取：备注
	 */

    public void setJifenJiluContent(String jifenJiluContent) {
        this.jifenJiluContent = jifenJiluContent;
    }
    /**
	 * 设置：操作时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：操作时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "JifenJilu{" +
            "id=" + id +
            ", huiyuanId=" + huiyuanId +
            ", yonghuId=" + yonghuId +
            ", jifenJiluTypes=" + jifenJiluTypes +
            ", jifenJiluJifen=" + jifenJiluJifen +
            ", jifenJiluContent=" + jifenJiluContent +
            ", insertTime=" + insertTime +
            ", createTime=" + createTime +
        "}";
    }
}
