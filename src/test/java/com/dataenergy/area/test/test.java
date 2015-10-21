package com.dataenergy.area.test;
import java.io.IOException;
import java.util.List;

import com.dataenergy.area.test.Transformor;
import com.dataenergy.areapredict.action.SvmTrainPredict;
import com.dataenergy.areapredict.bean.BuldingBean;
import com.dataenergy.areapredict.doAction.CsvReadAction;
import com.dataenergy.areapredict.doAction.SvmTrainPredictAction;
public class test {
	public static void main(String[] args) {
		// show(12);
		String path = "C:\\Users\\dell\\Desktop\\model.data01.csv";
		List<String[]> list = new CsvReadAction().readCsvFile(path, "gbk");
		List<BuldingBean> beanTrain = Transformor.strings2BuildBeans(list.subList(0, 40000));
		List<BuldingBean> beanTest = Transformor.strings2BuildBeans(list.subList(40001, list.size()));

		System.out.println(beanTrain.size());

		SvmTrainPredict svmTrainPredict = new SvmTrainPredictAction();
		String modelPath = "D:/svmparam";
		svmTrainPredict.modelTrain(beanTrain, modelPath);
		
		List<BuldingBean> beansPred = svmTrainPredict.predictAreas(modelPath, beanTest);
		List<String[]> listStrings = Transformor.buildBeans2Strings(beansPred);
		try {
			new CsvReadAction().writeCsvFile(listStrings, "D:/", "result.csv", true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < listStrings.size(); i++) {
			String[] tmpStrings = listStrings.get(i);
			System.out.println(tmpStrings[1]);
		}
		
//		for (int i = 0; i < beanTest.size(); i++) {
//			BuldingBean bean = beanTest.get(i);
//			System.out.println(bean.getArea());
//		}
	}


}