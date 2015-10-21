package com.dataenergy.areapredict.bean;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 
 * 能耗统计
 *
 */
public class OTSBillEnergyConvert implements Serializable {

	/**
	 * 标识
	 */
	private static final long serialVersionUID = 2624321231667508700L;

	/** 对象ID(合同或住宅) */
	private String objectId;

	/** 住宅Id */
	private String houseId;

	/** 对象类型(水，电，气，标准煤) */
	private Long objectType;

	/** 年份 */
	private Long year;

	/** 月份 */
	private Long month;

	/** 是否完整值 */
	private Long isComplete;

	/** 截止日期 */
	private Date endDate;

	/** 计算时间 */
	private Date createTime;

	/** 计量 */
	private Double measure;

	/** 金额 */
	private Double cost;

	/**
	 * 构造函数
	 */
	public OTSBillEnergyConvert() {
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public String getHouseId() {
		return houseId;
	}

	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}

	public Long getObjectType() {
		return objectType;
	}

	public void setObjectType(Long type) {
		this.objectType = type;
	}

	public Long getYear() {
		return year;
	}

	public void setYear(Long year) {
		this.year = year;
	}

	public Long getMonth() {
		return month;
	}

	public void setMonth(Long month) {
		this.month = month;
	}

	public Long getIsComplete() {
		return isComplete;
	}

	public void setIsComplete(Long isComplete) {
		this.isComplete = isComplete;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Double getMeasure() {
		return measure;
	}

	public void setMeasure(Double measure) {
		this.measure = measure;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
