package com.dataenergy.areapredict.action;

import java.util.List;
import java.util.Vector;

import libsvm.svm_node;

import com.dataenergy.areapredict.bean.AreaBean;
import com.dataenergy.areapredict.bean.BuldingBean;
import com.dataenergy.areapredict.bean.OTSBillEnergyConvert;

public interface DataTrans {
	//单个建筑物用电数据转换
	public abstract svm_node[] buildingBean2SvmNode(BuldingBean buldingBean);
	//多个建筑物用电数据转换
	public abstract svm_node[][] buildingBeans2SvmNodes(List<BuldingBean> buldingBeans);
	//获取单个建筑物的用电面积
	public abstract double getBuldingArea(BuldingBean buldingBean);
	//获取多个建筑物的用电面积
	public abstract double[] getBuldingsArea(List<BuldingBean> buldingBeans);
	
	//标准化一个样本
	public abstract svm_node[] scaleSvmNode(svm_node[] node, double[] meanValues, double[] sdValues);
	//标准化多个样本
	public abstract svm_node[][] scaleSvmNode(svm_node[][] node, double[] meanValues, double[] sdValues);
	//获取样本中各属性的中值和标准差
	public abstract Vector<double[]> getMeanSd(svm_node[][] node);
	
	//多个OTSBillEnergyConvert转化为多个BuldingBean
	public abstract List<BuldingBean> generateBuldingBeans(List<OTSBillEnergyConvert> otsBillEnergyConverts);
	//单个OTSBillEnergyConvert转化为BuldingBean
	public abstract BuldingBean generateBuldingBean(List<OTSBillEnergyConvert> otsBillEnergyConverts);
	
	//多个字符串数组转化为多个BuldingBean
	public abstract List<BuldingBean> stringArrays2BuildBeans(List<String[]> list);
	//单个字符串数组转化为单个BuldingBean
	public abstract BuldingBean stringArray2BuildBean(String[] strings);
	//单个BuldingBean转化为单个字符串数组
	public abstract String[] buldingbean2StringArray(BuldingBean bean);
	//多个BuldingBean转化为多个字符串数组
	public abstract List<String[]> buldingbeans2StringArrays(List<BuldingBean> beans);
	
	//单个字符串数组转化为单个OTSBillEnergyConvert
	public abstract OTSBillEnergyConvert stringArray2OTSBillEnergyConvert(String[] strings);
	//多个字符串数组转化为多个OTSBillEnergyConvert
	public abstract List<OTSBillEnergyConvert> stringArrays2OTSBillEnergyConverts(List<String[]> list);
	
	//单个字符串数组转化为单个AreaBean
	public abstract AreaBean stringArray2AreaBean(String[] strings);
	//多个字符串数组转化为单个AreaBean
	public abstract List<AreaBean> stringArrays2AreaBeans(List<String[]> list);
	
	//整合用电量信息和面积信息生成训练样本和测试样本
	public abstract List<BuldingBean> generateSamples(List<BuldingBean> buldingBeans, List<AreaBean> areaBeans);
}