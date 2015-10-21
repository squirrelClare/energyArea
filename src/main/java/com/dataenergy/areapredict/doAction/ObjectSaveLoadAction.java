package com.dataenergy.areapredict.doAction;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

import com.dataenergy.areapredict.action.ObjectSaveLoad;

public class ObjectSaveLoadAction implements ObjectSaveLoad {

	public void meanSdValueSave(Vector<double[]> meansd, String filePath) {
		// TODO Auto-generated method stub
		ObjectOutputStream oo;
		try {
			oo = new ObjectOutputStream(new FileOutputStream(new File(filePath)));
			oo.writeObject(meansd);
			oo.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "resource", "unchecked" })
	public Vector<double[]> meanSdValueSaveLoad(String filePath) {
		// TODO Auto-generated method stub
		ObjectInputStream ois = null;
		Vector<double[]> meansd = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(
					new File(filePath)));
			meansd = (Vector<double[]>) ois.readObject();
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
		return meansd;
	}

	public void meanSdValueOfAreaSave(double[] meansd, String filePath) {
		// TODO Auto-generated method stub
		ObjectOutputStream oo;
		try {
			oo = new ObjectOutputStream(new FileOutputStream(new File(filePath)));
			oo.writeObject(meansd);
			oo.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("resource")
	public double[] meanSdValueOfAreaLoad(String filePath) {
		// TODO Auto-generated method stub
		ObjectInputStream ois = null;
		double[] meansd = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(
					new File(filePath)));
			meansd = (double[]) ois.readObject();
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
		return meansd;
	}

}
