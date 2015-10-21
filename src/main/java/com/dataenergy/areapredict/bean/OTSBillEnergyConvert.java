package com.dataenergy.areapredict.bean;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 
 * �ܺ�ͳ��
 *
 */
public class OTSBillEnergyConvert implements Serializable {

	/**
	 * ��ʶ
	 */
	private static final long serialVersionUID = 2624321231667508700L;

	/** ����ID(��ͬ��סլ) */
	private String objectId;

	/** סլId */
	private String houseId;

	/** ��������(ˮ���磬������׼ú) */
	private Long objectType;

	/** ��� */
	private Long year;

	/** �·� */
	private Long month;

	/** �Ƿ�����ֵ */
	private Long isComplete;

	/** ��ֹ���� */
	private Date endDate;

	/** ����ʱ�� */
	private Date createTime;

	/** ���� */
	private Double measure;

	/** ��� */
	private Double cost;

	/**
	 * ���캯��
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
