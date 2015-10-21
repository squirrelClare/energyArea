package com.dataenergy.areapredict.doAction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import libsvm.svm;
import libsvm.svm_model;
import libsvm.svm_node;
import libsvm.svm_parameter;
import libsvm.svm_problem;

import com.dataenergy.areapredict.action.DataTrans;
import com.dataenergy.areapredict.action.SvmModel;
import com.dataenergy.areapredict.bean.BuldingBean;

public class SvmModelAction implements SvmModel {

	public class SvmException extends Exception {
		private static final long serialVersionUID = 1L;
		
		public SvmException(String msg) {
			// TODO Auto-generated constructor stub
			super(msg);
		}
	}
	
	
	public svm_model modelTrain(List<BuldingBean> buldingBeans, String modelDir) throws SvmException, IOException {
		// TODO Auto-generated method stub
		DataTrans trans = new DataTransAction();
		svm_problem problem = new svm_problem();
		
		svm_node[][] nodes = trans.buildingBeans2SvmNodes(buldingBeans);//训练样本节点
		Vector<double[]> meanSd = trans.getMeanSd(nodes);	//获取各属性均值和方差
		nodes = trans.scaleSvmNode(nodes, meanSd.get(0), meanSd.get(1));	//归一化节点个属性值
		
		//训练样本点的相关参数
		double[] areas = trans.getBuldingsArea(buldingBeans);
		double[] meanSdOfArea = new double[2];
		meanSdOfArea[0] = this.getMeanValue(areas);
		meanSdOfArea[1] = this.getSdValue(areas);
		
		problem.l = buldingBeans.size();
		problem.x = nodes;
		problem.y = this.scaleValue(areas);
		
        //定义svm_parameter对象
        svm_parameter param = new svm_parameter();
        param.svm_type = svm_parameter.EPSILON_SVR;
        param.kernel_type = svm_parameter.RBF;
        param.gamma = 0.5;
        param.cache_size = 1000;
        param.eps = 0.1;
        param.C = 1;

//        svm.svm_cross_validation(problem, param, 4, trans.getBuldingsArea(buldingBeans));
        
    
        if (svm.svm_check_parameter(problem, param) != null) {
			throw new SvmException("支持向量机模型训练参数异常！！！！！！！");
		}
        
        ObjectSaveLoadAction saveLoadAction = new ObjectSaveLoadAction();
        //序列化存储样本中自变量的均值和标准差
        saveLoadAction.meanSdValueSave(meanSd, modelDir+"/meaSd");
        //序列化存储样本中因变量（面积）的均值和标准差
        saveLoadAction.meanSdValueOfAreaSave(meanSdOfArea, modelDir+"/meanSdOfArea");
        
        //训练模型
        svm_model model = svm.svm_train(problem, param);
        //序列化村存储模型
        modelSave(modelDir+"/svmModel", model);
		return model;
	}


	public void modelSave(String modelDir, svm_model model) throws IOException {
		// TODO Auto-generated method stub
		svm.svm_save_model(modelDir, model);
	}


	public svm_model modelLoad(String modelDir) throws IOException {
		// TODO Auto-generated method stub
		svm_model model = svm.svm_load_model(modelDir+"/svmModel");
		return model;
	}

	public BuldingBean predict(svm_model model, BuldingBean bean, String modelDir) {
		// TODO Auto-generated method stub
		
		DataTrans trans = new DataTransAction();
		svm_node[] p = trans.buildingBean2SvmNode(bean);
		
		ObjectSaveLoadAction saveLoadAction = new ObjectSaveLoadAction();
		//反序列化存储样本中自变量的均值和标准差
		Vector<double[]> meanSd = saveLoadAction.meanSdValueSaveLoad(modelDir+"/meaSd");
		
		//反序列化存储建筑物面积的均值和标准差
		double[] meanSdOfArea  = saveLoadAction.meanSdValueOfAreaLoad(modelDir+"/meanSdOfArea");
		//归一化自变量
		p = trans.scaleSvmNode(p, meanSd.get(0), meanSd.get(1));
		double areaPredictScale = svm.svm_predict(model, p);
		double area = areaPredictScale*meanSdOfArea[1]+meanSdOfArea[0];
		bean.setArea(area);
		return bean;
	}


	public List<BuldingBean> predict(svm_model model,
			List<BuldingBean> buldingBeans, String modelDir) {
		// TODO Auto-generated method stub
		ArrayList<BuldingBean> beans = new ArrayList<BuldingBean>();
		for (int i = 0; i < buldingBeans.size(); i++) {
			beans.add(predict(model, buldingBeans.get(i), modelDir));
		}
		
		return beans;
	}
	
	//归一化
	private double[] scaleValue(double[] value) {
		double meanValue = this.getMeanValue(value);
		double sdValue = this.getSdValue(value);
		for (int i = 0; i < value.length; i++) {
			value[i] = (value[i] - meanValue) / sdValue;
		}
		return value;
	}
	//获取均值
	private double getMeanValue(double[] value) {
		DescriptiveStatistics statistics = new DescriptiveStatistics(value);
		return statistics.getMean();
	}
	//获取标准差
	private double getSdValue(double[] value) {
		DescriptiveStatistics statistics = new DescriptiveStatistics(value);
		return statistics.getStandardDeviation();
	}
} 