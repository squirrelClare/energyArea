package com.dataenergy.area.test;

import java.util.ArrayList;
import java.util.List;

import com.dataenergy.areapredict.bean.BuldingBean;

public class Transformor {
	public static List<BuldingBean> strings2BuildBeans(List<String[]> list) {
		List<BuldingBean> beans = new ArrayList<BuldingBean>();

		for (int i = 0; i < list.size(); i++) {
			BuldingBean tmpBean = new BuldingBean();
			String[] tmpValues = list.get(i);
			tmpBean.setId(tmpValues[0]);
			tmpBean.setArea(Double.parseDouble(tmpValues[1]));
			tmpBean.setBaseElectric(Double.parseDouble(tmpValues[2]));
			tmpBean.setAirElectric(Double.parseDouble(tmpValues[3]));
			beans.add(tmpBean);
		}

		return beans;
	}

	public static BuldingBean string2BuldingBean(String[] strings) {
		BuldingBean tmpBean = new BuldingBean();
		tmpBean.setId(strings[0]);
		tmpBean.setArea(Double.parseDouble(strings[1]));
		tmpBean.setBaseElectric(Double.parseDouble(strings[2]));
		tmpBean.setAirElectric(Double.parseDouble(strings[3]));
		return tmpBean;
	}
	public static String[] bean2Strings(BuldingBean bean) {
		String[] strings = new String[4];
		strings[0] = bean.getId();
		strings[1] = Double.toString(bean.getBaseElectric());
		strings[2] = Double.toString(bean.getAirElectric());
		strings[3] = Double.toString(bean.getArea());
		return strings;
	}
	
	public static List<String[]> buildBeans2Strings(List<BuldingBean> beans) {
		List<String[]> list =new ArrayList<String[]>();
				
		for (int i = 0; i < beans.size(); i++) {
			list.add(bean2Strings(beans.get(i)));
		}
		return list;
	}
}
