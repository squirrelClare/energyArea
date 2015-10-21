package com.dataenergy.area.test;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.dataenergy.areapredict.action.SvmTrainPredict;
import com.dataenergy.areapredict.bean.BuldingBean;
import com.dataenergy.areapredict.doAction.CsvReadAction;
import com.dataenergy.areapredict.doAction.SvmTrainPredictAction;


public class SvmTrainPredictActionTest {
	
	public void modelTrainTest() {
		SvmTrainPredict svmTrainPredict = new SvmTrainPredictAction();
		svmTrainPredict.modelTrain("F:/SVMTrainData02.csv", "F:/SVMHouseTrainData.csv", 
				"F:/modelData", "gbk");
	}
	@Test
	public void predictAreasTest() {
		SvmTrainPredict svmTrainPredict = new SvmTrainPredictAction();
		//�����ͬ�˺����õ�������
		List<BuldingBean> buldingBeans = svmTrainPredict.predictAreas
				("F:/modelData", "F:/modelData/testData.csv", "gbk");
		
		//Ԥ����ת��Ϊ�ַ���
		List<String[]> listStrings = Transformor.buildBeans2Strings(buldingBeans);
		
		//д��csv�ļ�
		try {
			new CsvReadAction().writeCsvFile(listStrings, "F:/modelData", "result.csv", true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
