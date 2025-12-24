package com.comcast.crm.gneric.FileUtility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class XL_Utility {

	String path = "./testdata/genericUtility.xlsx";
	
	public String getDataFromExcel(String sheetName, int rowNum, int cellNum) throws Exception, IOException {
		FileInputStream fis = new FileInputStream(new File(path));
		
		Workbook workbook = WorkbookFactory.create(fis);
		String data  = workbook.getSheet(sheetName).getRow(rowNum).getCell(cellNum).toString();
		
		return data;
	}
	
	public int getRowCount(String sheetName) throws Exception, IOException {
		FileInputStream fis = new FileInputStream(new File(path));
		
		Workbook workbook = WorkbookFactory.create(fis);
		
		return workbook.getSheet(sheetName).getPhysicalNumberOfRows();
	}
	
	public void setDataIntoCell(String sheetName, int rowNum, int cellNum, String data) throws Exception {
		FileInputStream fis = new FileInputStream(new File(path));
		
		Workbook workbook = WorkbookFactory.create(fis);
		
		workbook.getSheet(sheetName).getRow(rowNum).createCell(cellNum).setCellValue(data);
		
		FileOutputStream fio = new FileOutputStream(new File(path));
		workbook.write(fio);
		workbook.close();
	}
}
