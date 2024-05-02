package com.entity.model;

import com.entity.JifenJiluEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 积分记录
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class JifenJiluModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 会员
     */
    private Integer huiyuanId;


    /**
     * 操作人
     */
    private Integer yonghuId;


    /**
     * 类型
     */
    private Integer jifenJiluTypes;


    /**
     * 积分
     */
    private Integer jifenJiluJifen;


    /**
     * 备注
     */
    private String jifenJiluContent;


    /**
     * 操作时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 设置：主键
	 */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：会员
	 */
    public Integer getHuiyuanId() {
        return huiyuanId;
    }


    /**
	 * 设置：会员
	 */
    public void setHuiyuanId(Integer huiyuanId) {
        this.huiyuanId = huiyuanId;
    }
    /**
	 * 获取：操作人
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 设置：操作人
	 */
    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 获取：类型
	 */
    public Integer getJifenJiluTypes() {
        return jifenJiluTypes;
    }


    /**
	 * 设置：类型
	 */
    public void setJifenJiluTypes(Integer jifenJiluTypes) {
        this.jifenJiluTypes = jifenJiluTypes;
    }
    /**
	 * 获取：积分
	 */
    public Integer getJifenJiluJifen() {
        return jifenJiluJifen;
    }


    /**
	 * 设置：积分
	 */
    public void setJifenJiluJifen(Integer jifenJiluJifen) {
        this.jifenJiluJifen = jifenJiluJifen;
    }
    /**
	 * 获取：备注
	 */
    public String getJifenJiluContent() {
        return jifenJiluContent;
    }


    /**
	 * 设置：备注
	 */
    public void setJifenJiluContent(String jifenJiluContent) {
        this.jifenJiluContent = jifenJiluContent;
    }
    /**
	 * 获取：操作时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：操作时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
