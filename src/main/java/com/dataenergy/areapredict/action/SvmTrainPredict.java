package com.dataenergy.areapredict.action;

import java.util.List;

import libsvm.svm_model;

import com.dataenergy.areapredict.bean.BuldingBean;

public interface SvmTrainPredict {
	//训练模型并保存
	public abstract  svm_model modelTrain(List<BuldingBean> buldingBeans, String modelDir);
	//加载已有模型并预测单个面积
	public abstract  BuldingBean predictArea(String modelDir, BuldingBean bean);
	//加载已有模型并预测多个面积
	public abstract  List<BuldingBean> predictAreas(String modelDir, List<BuldingBean> buldingBeans);
	
	//上传用电量文件(.csv，含有5列)和面积文件(.csv，含有2列)，训练 并保存到目录modelDir模型
	public abstract  svm_model modelTrain(String elecFilePath, String areaFilePath 
			, String modelDir, String encoding);
	//上传用电量文件(.csv，含有5列)，加载模型后预测面积
	public abstract  List<BuldingBean> predictAreas(String modelDir, 
			String elecFilePath, String encoding);
}
