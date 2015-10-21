package com.dataenergy.areapredict.action;

import java.util.List;

import libsvm.svm_model;

import com.dataenergy.areapredict.bean.BuldingBean;

public interface SvmTrainPredict {
	//ѵ��ģ�Ͳ�����
	public abstract  svm_model modelTrain(List<BuldingBean> buldingBeans, String modelDir);
	//��������ģ�Ͳ�Ԥ�ⵥ�����
	public abstract  BuldingBean predictArea(String modelDir, BuldingBean bean);
	//��������ģ�Ͳ�Ԥ�������
	public abstract  List<BuldingBean> predictAreas(String modelDir, List<BuldingBean> buldingBeans);
	
	//�ϴ��õ����ļ�(.csv������5��)������ļ�(.csv������2��)��ѵ�� �����浽Ŀ¼modelDirģ��
	public abstract  svm_model modelTrain(String elecFilePath, String areaFilePath 
			, String modelDir, String encoding);
	//�ϴ��õ����ļ�(.csv������5��)������ģ�ͺ�Ԥ�����
	public abstract  List<BuldingBean> predictAreas(String modelDir, 
			String elecFilePath, String encoding);
}
