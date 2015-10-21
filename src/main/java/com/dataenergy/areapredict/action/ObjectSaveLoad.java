package com.dataenergy.areapredict.action;

import java.util.Vector;

public interface ObjectSaveLoad {
	//���л��洢�����Եľ�ֵ�ͱ�׼��
	public abstract void meanSdValueSave(Vector<double[]> meansd, String filePath);
	//�����л������Եľ�ֵ�ͱ�׼��
	public abstract Vector<double[]> meanSdValueSaveLoad(String filePath);
	//���л��洢����������ľ�ֵ�ͱ�׼��
	public abstract void meanSdValueOfAreaSave(double[] meansd, String filePath);
	//�����л�����������ľ�ֵ�ͱ�׼��
	public abstract double[] meanSdValueOfAreaLoad(String filePath);
}