package com.dataenergy.areapredict.action;

import java.util.List;
import java.util.Vector;

import libsvm.svm_node;

import com.dataenergy.areapredict.bean.AreaBean;
import com.dataenergy.areapredict.bean.BuldingBean;
import com.dataenergy.areapredict.bean.OTSBillEnergyConvert;

public interface DataTrans {
	//�����������õ�����ת��
	public abstract svm_node[] buildingBean2SvmNode(BuldingBean buldingBean);
	//����������õ�����ת��
	public abstract svm_node[][] buildingBeans2SvmNodes(List<BuldingBean> buldingBeans);
	//��ȡ������������õ����
	public abstract double getBuldingArea(BuldingBean buldingBean);
	//��ȡ�����������õ����
	public abstract double[] getBuldingsArea(List<BuldingBean> buldingBeans);
	
	//��׼��һ������
	public abstract svm_node[] scaleSvmNode(svm_node[] node, double[] meanValues, double[] sdValues);
	//��׼���������
	public abstract svm_node[][] scaleSvmNode(svm_node[][] node, double[] meanValues, double[] sdValues);
	//��ȡ�����и����Ե���ֵ�ͱ�׼��
	public abstract Vector<double[]> getMeanSd(svm_node[][] node);
	
	//���OTSBillEnergyConvertת��Ϊ���BuldingBean
	public abstract List<BuldingBean> generateBuldingBeans(List<OTSBillEnergyConvert> otsBillEnergyConverts);
	//����OTSBillEnergyConvertת��ΪBuldingBean
	public abstract BuldingBean generateBuldingBean(List<OTSBillEnergyConvert> otsBillEnergyConverts);
	
	//����ַ�������ת��Ϊ���BuldingBean
	public abstract List<BuldingBean> stringArrays2BuildBeans(List<String[]> list);
	//�����ַ�������ת��Ϊ����BuldingBean
	public abstract BuldingBean stringArray2BuildBean(String[] strings);
	//����BuldingBeanת��Ϊ�����ַ�������
	public abstract String[] buldingbean2StringArray(BuldingBean bean);
	//���BuldingBeanת��Ϊ����ַ�������
	public abstract List<String[]> buldingbeans2StringArrays(List<BuldingBean> beans);
	
	//�����ַ�������ת��Ϊ����OTSBillEnergyConvert
	public abstract OTSBillEnergyConvert stringArray2OTSBillEnergyConvert(String[] strings);
	//����ַ�������ת��Ϊ���OTSBillEnergyConvert
	public abstract List<OTSBillEnergyConvert> stringArrays2OTSBillEnergyConverts(List<String[]> list);
	
	//�����ַ�������ת��Ϊ����AreaBean
	public abstract AreaBean stringArray2AreaBean(String[] strings);
	//����ַ�������ת��Ϊ����AreaBean
	public abstract List<AreaBean> stringArrays2AreaBeans(List<String[]> list);
	
	//�����õ�����Ϣ�������Ϣ����ѵ�������Ͳ�������
	public abstract List<BuldingBean> generateSamples(List<BuldingBean> buldingBeans, List<AreaBean> areaBeans);
}