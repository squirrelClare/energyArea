package com.dataenergy.areapredict.bean;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class BuldingBean {
	private String id;	//��ͬ���
	double baseElectric;	//�����õ���
	double airElectric;		//�յ��õ���
	double area;	//���������
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getBaseElectric() {
		return baseElectric;
	}
	public void setBaseElectric(double baseElectric) {
		this.baseElectric = baseElectric;
	}
	public double getAirElectric() {
		return airElectric;
	}
	public void setAirElectric(double airElectric) {
		this.airElectric = airElectric;
	}
	public double getArea() {
		return area;
	}
	public void setArea(double area) {
		this.area = area;
	}
	
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
