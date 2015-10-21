package com.dataenergy.area.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;


public class DADSADAS {
	public static void main(String[] args) {
		double[] a = { 1, 3, 4, 5 };
		DescriptiveStatistics statistics = new DescriptiveStatistics(a);
		// for (int i = 0; i < a.length; i++) {
		// statistics.addValue(a[i]);
		// }
		System.out.println(statistics.getMean());
		System.out.println(statistics.getMax());
		System.out.println(statistics.getMin());
		System.out.println(statistics.getVariance());
		System.out.println(statistics.getStandardDeviation());
		double[][] b = { { 13, 4354, 323 }, { 213, 342, 45 } };
		System.out.println(b[0].length);
		ObjectOutputStream oo;
		try {
			oo = new ObjectOutputStream(new FileOutputStream(new File(
					"E:/Person.txt")));
			oo.writeObject(a);
			System.out.println("Person对象序列化成功！");
			oo.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ObjectInputStream ois = null;
		double[] person = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(
					new File("E:/Person.txt")));
			person = (double[]) ois.readObject();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Person对象反序列化成功！");
		for (int i = 0; i < person.length; i++) {
			System.out.println(person[i]);
		}
		System.out.println("***************************");
		
		ArrayList<String> arr = new ArrayList<String>();
		arr.add("ewq");
		arr.add("ewqdsd");
		arr.add("ewqewq");
		arr.add("ewqewq");
		for (Iterator iterator = arr.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			System.out.println(string);
		}
		System.out.println(arr.contains("ewq"));
		
		ArrayList<Integer> arr1 = new ArrayList<Integer>();
		arr1.add(213);
		arr1.add(221313);
		System.out.println(arr1.contains(212143));
		
		System.out.println("*********************************************");
		ArrayList<String> arrayList = new ArrayList<String>();
		arrayList.add("sda");
		arrayList.add("sdadsa");
		arrayList.add(null);
		arrayList.add("sda");
		for (int i = 0; i < arrayList.size(); i++) {
			System.out.println(arrayList.get(i));
		}
		
	}
}
