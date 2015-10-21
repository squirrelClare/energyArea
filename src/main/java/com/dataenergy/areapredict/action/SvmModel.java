package com.dataenergy.areapredict.action;

import java.io.IOException;
import java.util.List;

import com.dataenergy.areapredict.bean.BuldingBean;
import com.dataenergy.areapredict.doAction.SvmModelAction.SvmException;

import libsvm.svm_model;

public interface SvmModel {
	//ѵ��֧��������ģ��
	public abstract  svm_model modelTrain(List<BuldingBean> buldingBeans, String modelDir) throws SvmException, IOException;
	//����֧��������ģ��
	public abstract  void modelSave(String modelDir, svm_model model) throws IOException;
	//����֧��������ģ��
	public abstract  svm_model modelLoad(String modelDir) throws IOException;
	//Ԥ�ⵥ���������õ����
	public abstract  BuldingBean predict(svm_model model, BuldingBean bean, String modelDir);
	//Ԥ�����������õ����
	public abstract  List<BuldingBean> predict(svm_model model, List<BuldingBean> buldingBeans, String modelDir);
}