package com.dataenergy.area.test;

import java.util.List;

import org.junit.Test;

import com.dataenergy.areapredict.action.CsvRead;
import com.dataenergy.areapredict.action.DataTrans;
import com.dataenergy.areapredict.bean.AreaBean;
import com.dataenergy.areapredict.bean.BuldingBean;
import com.dataenergy.areapredict.bean.OTSBillEnergyConvert;
import com.dataenergy.areapredict.doAction.CsvReadAction;
import com.dataenergy.areapredict.doAction.DataTransAction;


public class DataTransActionTest {
	
	public	void readCsvFileTest() {
		CsvRead csvRead = new CsvReadAction();
		List<String[]> list = csvRead.readCsvFile("F:/SVMTrainData02.csv", "gbk");
		
		DataTrans dataTrans = new DataTransAction();
		List<OTSBillEnergyConvert> billEnergyConverts =  dataTrans.stringArrays2OTSBillEnergyConverts(list);
		List<BuldingBean> buldingBeans = dataTrans.generateBuldingBeans(billEnergyConverts);
		for (int i = 0; i < 10; i++) {
			System.out.println(billEnergyConverts.get(i).toString());
		}
		System.out.println("**********************************");
		for (int i = 0; i < 100; i++) {
			System.out.println(buldingBeans.get(i).toString());
		}
	}
	
	public void stringArrays2AreaBeansTest() {
		// TODO Auto-generated method stub
		CsvRead csvRead = new CsvReadAction();
		List<String[]> list = csvRead.readCsvFile("F:/SVMHouseTrainData.csv", "gbk");
		
		DataTrans dataTrans = new DataTransAction();
		List<AreaBean> areaBeans = dataTrans.stringArrays2AreaBeans(list);
		
		for (int i = 0; i < areaBeans.size(); i++) {
			System.out.println(areaBeans.get(i).toString());
		}
	}
	
	@Test
	public void generateSamplesTest() {
		CsvRead csvRead = new CsvReadAction();
		List<String[]> listArea = csvRead.readCsvFile("F:/SVMHouseTrainData.csv", "gbk");
		
		DataTrans dataTrans = new DataTransAction();
		List<AreaBean> areaBeans = dataTrans.stringArrays2AreaBeans(listArea);

		List<String[]> listBulding = csvRead.readCsvFile("F:/SVMTrainData02.csv", "gbk");
		
		List<OTSBillEnergyConvert> billEnergyConverts =  dataTrans.stringArrays2OTSBillEnergyConverts(listBulding);
		List<BuldingBean> buldingBeans = dataTrans.generateBuldingBeans(billEnergyConverts);
		
		
		List<BuldingBean> resBeans = dataTrans.generateSamples(buldingBeans, areaBeans);
		
		for (int i = 0; i < 10; i++) {
			System.out.println("*******************************");
			System.out.println(buldingBeans.get(i).toString());
			System.out.println(resBeans.get(i).toString());
			System.out.println("*******************************");
		}
	}
}
