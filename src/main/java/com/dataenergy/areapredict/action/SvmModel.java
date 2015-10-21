package com.dataenergy.areapredict.action;

import java.io.IOException;
import java.util.List;

import com.dataenergy.areapredict.bean.BuldingBean;
import com.dataenergy.areapredict.doAction.SvmModelAction.SvmException;

import libsvm.svm_model;

public interface SvmModel {
	//训练支持向量机模型
	public abstract  svm_model modelTrain(List<BuldingBean> buldingBeans, String modelDir) throws SvmException, IOException;
	//保存支持向量机模型
	public abstract  void modelSave(String modelDir, svm_model model) throws IOException;
	//加载支持向量机模型
	public abstract  svm_model modelLoad(String modelDir) throws IOException;
	//预测单个建筑物用电面积
	public abstract  BuldingBean predict(svm_model model, BuldingBean bean, String modelDir);
	//预测多个建筑物用电面积
	public abstract  List<BuldingBean> predict(svm_model model, List<BuldingBean> buldingBeans, String modelDir);
}