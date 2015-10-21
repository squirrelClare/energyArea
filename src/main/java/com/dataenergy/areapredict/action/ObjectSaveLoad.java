package com.dataenergy.areapredict.action;

import java.util.Vector;

public interface ObjectSaveLoad {
	//序列化存储各属性的均值和标准差
	public abstract void meanSdValueSave(Vector<double[]> meansd, String filePath);
	//反序列化各属性的均值和标准差
	public abstract Vector<double[]> meanSdValueSaveLoad(String filePath);
	//序列化存储建筑物面积的均值和标准差
	public abstract void meanSdValueOfAreaSave(double[] meansd, String filePath);
	//反序列化建筑物面积的均值和标准差
	public abstract double[] meanSdValueOfAreaLoad(String filePath);
}