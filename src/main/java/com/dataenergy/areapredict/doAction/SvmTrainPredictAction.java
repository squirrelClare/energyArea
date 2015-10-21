package com.dataenergy.areapredict.doAction;

import java.io.IOException;
import java.util.List;

import libsvm.svm_model;

import com.dataenergy.areapredict.action.CsvRead;
import com.dataenergy.areapredict.action.DataTrans;
import com.dataenergy.areapredict.action.SvmModel;
import com.dataenergy.areapredict.action.SvmTrainPredict;
import com.dataenergy.areapredict.bean.AreaBean;
import com.dataenergy.areapredict.bean.BuldingBean;
import com.dataenergy.areapredict.bean.OTSBillEnergyConvert;
import com.dataenergy.areapredict.doAction.SvmModelAction.SvmException;

public class SvmTrainPredictAction implements SvmTrainPredict{

	public svm_model modelTrain(List<BuldingBean> buldingBeans,
			String modelDir) {
		// TODO Auto-generated method stub
		SvmModel svmModel = new SvmModelAction();
		
		svm_model model = null;
		//ѵ��ģ�Ͳ�����ģ��
		try {
			model = svmModel.modelTrain(buldingBeans, modelDir);
		} catch (SvmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return model;
	}

	public BuldingBean predictArea(String modelDir, BuldingBean bean) {
		// TODO Auto-generated method stub
		SvmModel svmModel = new SvmModelAction();
		//����Ԥ��ģ��
		svm_model model = null;
		try {
			model = svmModel.modelLoad(modelDir);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return svmModel.predict(model, bean, modelDir);
	}

	public List<BuldingBean> predictAreas(String modelDir,
			List<BuldingBean> buldingBeans) {
		// TODO Auto-generated method stub
		SvmModel svmModel = new SvmModelAction();
		
		//����Ԥ��ģ��
		svm_model model = null;
		try {
			model = svmModel.modelLoad(modelDir);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		List<BuldingBean> beans = svmModel.predict(model, buldingBeans, modelDir);
		return beans;
	}

	public svm_model modelTrain(String elecFilePath, String areaFilePath,
			String modelDir, String encoding) {
		// TODO Auto-generated method stub
		CsvRead csvRead = new CsvReadAction();
		DataTrans dataTrans = new DataTransAction();
		
		//�����õ����ļ�
		List<String[]> listElec = csvRead.readCsvFile(elecFilePath, encoding);
		//�����õ����ļ�
		List<OTSBillEnergyConvert> billEnergyConverts =  
				dataTrans.stringArrays2OTSBillEnergyConverts(listElec);
		List<BuldingBean> buldingBeans = dataTrans.generateBuldingBeans(billEnergyConverts);
		
		//��������ļ�
		List<String[]> listArea = csvRead.readCsvFile(areaFilePath, encoding);
		//��������ļ�
		List<AreaBean> areaBeans = dataTrans.stringArrays2AreaBeans(listArea);
		
		
		//�����õ��������������������	
		List<BuldingBean> samplesBuldingBeans = dataTrans.generateSamples(buldingBeans, areaBeans);
		
		//ѵ��ģ��
		svm_model model = this.modelTrain(samplesBuldingBeans, modelDir);
		
		return model;
	}

	public List<BuldingBean> predictAreas(String modelDir, 
			String elecFilePath, String encoding) {
		// TODO Auto-generated method stub
		CsvRead csvRead = new CsvReadAction();
		DataTrans dataTrans = new DataTransAction();
		//�����õ����ļ�
		List<String[]> listElec = csvRead.readCsvFile(elecFilePath, encoding);
		//�����õ����ļ�
		List<OTSBillEnergyConvert> billEnergyConverts =  
				dataTrans.stringArrays2OTSBillEnergyConverts(listElec);
		List<BuldingBean> buldingBeans = dataTrans.generateBuldingBeans(billEnergyConverts);
		
		return this.predictAreas(modelDir, buldingBeans);
	}

}
