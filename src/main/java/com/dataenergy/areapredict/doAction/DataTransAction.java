package com.dataenergy.areapredict.doAction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import libsvm.svm_node;

import com.dataenergy.areapredict.action.DataTrans;
import com.dataenergy.areapredict.bean.AreaBean;
import com.dataenergy.areapredict.bean.BuldingBean;
import com.dataenergy.areapredict.bean.OTSBillEnergyConvert;
import com.dataenergy.areapredict.doAction.SvmModelAction.SvmException;

public class DataTransAction implements DataTrans {

	public svm_node[] buildingBean2SvmNode(BuldingBean buldingBean) {
		// TODO Auto-generated method stub
		svm_node p0 = new svm_node();
		p0.index = 0;
		p0.value = buldingBean.getBaseElectric();
		
		svm_node p1 = new svm_node();
		p1.index = 1;
		p1.value = buldingBean.getAirElectric();
		
		svm_node[] p = {p0, p1};
		return p;
	}

	public svm_node[][] buildingBeans2SvmNodes(List<BuldingBean> buldingBeans) {
		// TODO Auto-generated method stub
		svm_node[][] p = new svm_node[buldingBeans.size()][];
		for (int i = 0; i < buldingBeans.size(); i++) {
			BuldingBean bean = buldingBeans.get(i);
			p[i] = this.buildingBean2SvmNode(bean);
		}
		return p;
	}

	public double getBuldingArea(BuldingBean buldingBean) {
		// TODO Auto-generated method stub
		return buldingBean.getArea();
	}

	public double[] getBuldingsArea(List<BuldingBean> buldingBeans) {
		// TODO Auto-generated method stub
		double[] p = new double[buldingBeans.size()];
		for (int i = 0; i < buldingBeans.size(); i++) {
			p[i] = this.getBuldingArea(buldingBeans.get(i));
		}
		return p;
	}

	public svm_node[] scaleSvmNode(svm_node[] node, double[] meanValues,
			double[] sdValues) {
		// TODO Auto-generated method stub
		int nAttr = node.length;
		for (int i = 0; i < nAttr; i++) {
			node[i].value = (node[i].value - meanValues[i]) / sdValues[i];
		}
		return node;
	}

	public svm_node[][] scaleSvmNode(svm_node[][] node, double[] meanValues,
			double[] sdValues) {
		// TODO Auto-generated method stub
		int nSample = node.length;
		svm_node[][] resNodes = new svm_node[nSample][];
		for (int i = 0; i < nSample; i++) {
			resNodes[i] = scaleSvmNode(node[i], meanValues, sdValues);
		}
		return resNodes;
	}

	public Vector<double[]> getMeanSd(svm_node[][] node) {
		// TODO Auto-generated method stub
		DescriptiveStatistics statistics = new DescriptiveStatistics();
		int nAttr = node[0].length;
		int nSample = node.length;
		double[] meanValues = new double[nAttr];
		double[] sdValues = new double[nAttr];
		
		for (int i = 0; i < nAttr; i++) {
			statistics.clear();
			for (int j = 0; j < nSample; j++) {
				statistics.addValue(node[j][i].value);
			}
			//获取中值及标准差
			meanValues[i] = statistics.getMean();
			sdValues[i] = statistics.getStandardDeviation();
		}
		
		Vector<double[]> meanSd = new Vector<double[]>();
		meanSd.add(meanValues);
		meanSd.add(sdValues);	
		return meanSd;
	}
	public BuldingBean generateBuldingBean(
			List<OTSBillEnergyConvert> otsBillEnergyConverts) {
		// TODO Auto-generated method stub
		BuldingBean bean = null;
		
		bean = OTSBillEnergyConvert2BuldingBean(otsBillEnergyConverts);
		return bean;
	}
	public List<BuldingBean> generateBuldingBeans(
			List<OTSBillEnergyConvert> otsBillEnergyConverts) {
		// TODO Auto-generated method stub
		//存放转化后的BuldingBean
		List<BuldingBean> beans = new ArrayList<BuldingBean>();
		
		Map<String, List<OTSBillEnergyConvert>> map = this.OTSBillEnergyConvertSplit(otsBillEnergyConverts);
		Set<String> keys = map.keySet();
		
		for (String key : keys) {	
			beans.add(OTSBillEnergyConvert2BuldingBean(map.get(key)));
		}
		return beans;
	}
	
	public  List<BuldingBean> stringArrays2BuildBeans(List<String[]> list) {
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

	public  BuldingBean stringArray2BuildBean(String[] strings) {
		BuldingBean tmpBean = new BuldingBean();
		tmpBean.setId(strings[0]);
		tmpBean.setArea(Double.parseDouble(strings[1]));
		tmpBean.setBaseElectric(Double.parseDouble(strings[2]));
		tmpBean.setAirElectric(Double.parseDouble(strings[3]));
		return tmpBean;
	}
	public  String[] buldingbean2StringArray(BuldingBean bean) {
		String[] strings = new String[4];
		strings[0] = bean.getId();
		strings[1] = Double.toString(bean.getArea());
		strings[2] = Double.toString(bean.getBaseElectric());
		strings[3] = Double.toString(bean.getAirElectric());
		return strings;
	}
	
	public  List<String[]> buldingbeans2StringArrays(List<BuldingBean> beans) {
		List<String[]> list =new ArrayList<String[]>();
		
		for (int i = 0; i < beans.size(); i++) {
			list.add(buldingbean2StringArray(beans.get(i)));
		}
		return list;
	}

	
	
	//将传输进来的OTSBillEnergyConvert按合同编号进行划分
	private Map<String, List<OTSBillEnergyConvert>> OTSBillEnergyConvertSplit(List<OTSBillEnergyConvert> otsBillEnergyConverts) {
		//获取合同账号
		Set<String> objectIdSet = new HashSet<String>();
		OTSBillEnergyConvert tmp = null;
		for (int i = 0; i < otsBillEnergyConverts.size(); i++) {
			tmp = otsBillEnergyConverts.get(i);
			if (tmp != null) {
				objectIdSet.add(tmp.getObjectId());
			}
		}
		
		//构建合同编号和用电信息对照表
		Map<String, List<OTSBillEnergyConvert>> map = 
				new HashMap<String, List<OTSBillEnergyConvert>>();
		for (Iterator<String> iterator = objectIdSet.iterator(); iterator.hasNext();) {
			map.put(iterator.next(), new ArrayList<OTSBillEnergyConvert>());
		}
		
		for (Iterator<OTSBillEnergyConvert> iterator = otsBillEnergyConverts.iterator(); iterator.hasNext();) {
			OTSBillEnergyConvert e = iterator.next();
			if (e != null) {
				map.get(e.getObjectId()).add(e);
			}
		}
		return map;
	}
	
	//将一个合同账号的12和与用电量转化为基本用电量和空调用电量
	@SuppressWarnings("finally")
	private BuldingBean OTSBillEnergyConvert2BuldingBean(List<OTSBillEnergyConvert> billEnergyConverts) {
		BuldingBean bean = new BuldingBean();
		try {
			//检查合同账号是否包含完整的12个月数据
			if (billEnergyConverts.size() < 12) {
				bean = null;
				throw new SvmModelAction().new SvmException("合同账号： " + billEnergyConverts.get(0).getObjectId()
					+ "含有数据少于12个月份！！");
			}
			double baseElec = 0;
			double airElec =0;
			OTSBillEnergyConvert tmp = null;
			
			//计算基本用电量的月份索引
			ArrayList<Integer> baseMonth = new ArrayList<Integer>();
			baseMonth.add(3);baseMonth.add(4);baseMonth.add(11);baseMonth.add(12);
			//计算空调用电量的月份索引
			ArrayList<Integer> airMonth = new ArrayList<Integer>();
			airMonth.add(6);airMonth.add(7);airMonth.add(8);airMonth.add(9);
			
			
			//计算基本用电量和空调用电两，空调用电量为减去基本用电量
			for (int i = 0; i < billEnergyConverts.size(); i++) {
				tmp = billEnergyConverts.get(i);
				
				//判断一合同账号12个月份中的数据是全部否完整
				if (tmp.getIsComplete() == 1) {
					bean = null;
					throw new SvmModelAction().new SvmException("合同账号： " + tmp.getObjectId()
							+ " " + tmp.getYear() + '年' + tmp.getMonth() + "月数据不完整" );
				}
				
//				System.out.println(tmp.getMonth());
				if (baseMonth.contains(tmp.getMonth().intValue())) {
					baseElec = baseElec + tmp.getMeasure();
				}
				if (airMonth.contains(tmp.getMonth().intValue())) {
					airElec = airElec + tmp.getMeasure();
				}
			}
			
			//将数值注入到目标BuldingBean中
			bean.setId(billEnergyConverts.get(0).getObjectId());
			bean.setBaseElectric(baseElec);
			bean.setAirElectric(airElec - baseElec);
			bean.setArea(0);
		} catch (SvmException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			return bean;	
		}
	}

	@SuppressWarnings("finally")
	public OTSBillEnergyConvert stringArray2OTSBillEnergyConvert(String[] strings) {
		// TODO Auto-generated method stub
		OTSBillEnergyConvert billEnergyConvert = new OTSBillEnergyConvert();
		try {
			if (strings.length != 5) {
				billEnergyConvert = null;
				throw new SvmModelAction().new SvmException("合同账号（用电量）： "+strings[0]+" 的属性值不完整！！！");
			}
			billEnergyConvert.setObjectId(strings[0]);
			billEnergyConvert.setHouseId(null);
			billEnergyConvert.setObjectType(null);
			billEnergyConvert.setYear(Long.parseLong(strings[1]));
			billEnergyConvert.setMonth(Long.parseLong(strings[2]));
			billEnergyConvert.setIsComplete(Long.parseLong(strings[3]));
			billEnergyConvert.setEndDate(null);
			billEnergyConvert.setCreateTime(null);
			billEnergyConvert.setMeasure(Double.parseDouble(strings[4]));
			billEnergyConvert.setCost(null);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			return billEnergyConvert;
		}
	}

	public List<OTSBillEnergyConvert> stringArrays2OTSBillEnergyConverts(
			List<String[]> list) {
		// TODO Auto-generated method stub
		List<OTSBillEnergyConvert> billEnergyConverts = new ArrayList<OTSBillEnergyConvert>();
		
		OTSBillEnergyConvert tmp = null;
		for (int i = 0; i < list.size(); i++) {
			//排除值为空的OTSBillEnergyConvert
			tmp = stringArray2OTSBillEnergyConvert(list.get(i));
			if (tmp != null) {
				billEnergyConverts.add(tmp);
			}
		}
		return billEnergyConverts;
	}

	@SuppressWarnings("finally")
	public AreaBean stringArray2AreaBean(String[] strings) {
		// TODO Auto-generated method stub
		AreaBean areaBean = new AreaBean();
		
		try {
			if (strings.length != 2) {
				areaBean = null;
				throw new SvmModelAction().new SvmException("合同账号(面积)： "+strings[0]+" 的属性值不完整！！！");
			}
			areaBean.setObjectId(strings[0]);
			areaBean.setArea(Double.parseDouble(strings[1]));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			return areaBean;
		}
	}

	public List<AreaBean> stringArrays2AreaBeans(List<String[]> list) {
		// TODO Auto-generated method stub
		List<AreaBean> areaBeans = new ArrayList<AreaBean>();
		AreaBean tmp = null;
		for (int i = 0; i < list.size(); i++) {
			tmp = stringArray2AreaBean(list.get(i));
			if (tmp != null) {
				areaBeans.add(tmp);
			}	
		}
		return areaBeans;
	}

	public List<BuldingBean> generateSamples(List<BuldingBean> buldingBeans,
			List<AreaBean> areaBeans) {
		// TODO Auto-generated method stub
		List<BuldingBean> samples = new ArrayList<BuldingBean>();
		
		Map<String, Double> map = new HashMap<String, Double>();
		for (int i = 0; i < areaBeans.size(); i++) {
			map.put(areaBeans.get(i).getObjectId(), areaBeans.get(i).getArea());
		}
		Set<String> keySet = map.keySet();
		
		BuldingBean tmp = null;
		String tmpKey = null;
		for (int i = 0; i < buldingBeans.size(); i++) {
			tmp = buldingBeans.get(i);
			tmpKey = tmp.getId();
			if (!keySet.contains(tmpKey)) {
				continue;
			}
			tmp.setArea(map.get(tmpKey));
			samples.add(tmp);
		}
		return samples;
	}
}