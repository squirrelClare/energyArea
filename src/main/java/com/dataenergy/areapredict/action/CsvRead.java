package com.dataenergy.areapredict.action;

import java.io.IOException;
import java.util.List;

public interface CsvRead {
	public abstract List<String[]> readCsvFile(String argPath, String encoding);
    public abstract void writeCsvFile(List<String[]> argList, String argPath, String argFileName, boolean isNewFile) throws IOException, Exception;  
}