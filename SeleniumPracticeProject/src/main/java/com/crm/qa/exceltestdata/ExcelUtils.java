package com.crm.qa.exceltestdata;

import java.io.File;
import java.io.FileInputStream;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	
	
	//*********Variables*********
	
	 private static Logger Log = Logger.getLogger(ExcelUtils.class.getName());
	 
	 private static FileInputStream excelFile;
	 private static XSSFSheet ExcelWSheet;
	 private static XSSFWorkbook ExcelWBook;
	 private static XSSFCell Cell;
	 
	 
	 
	//*********Methods*********
	
	/**
	 * This method is to set the File path and to open the Excel file, Pass Excel Path and Sheetname as Arguments to this method
	 * @param fileName TODO
	 * @param sheetName TODO
	 */
	
	public static void setExcelFile(String fileName, String sheetName)
	{
		try {
			
			
			excelFile = new FileInputStream(new File(System.getProperty("user.dir") + "\\src\\main\\java\\com\\crm\\qa\\testdata\\"+fileName));
			ExcelWBook = new XSSFWorkbook(excelFile);
			ExcelWSheet = ExcelWBook.getSheet(sheetName);
			
		} catch (Exception e) {
		
			Log.error("----------Exception Occured while Setting Path and Sheet for reading Excel file---------", e);
		}
		
	}
	
	/**
	 * This method will return the column number based on Column Name Specified.
	 * @param colName TODO
	 * @return TODO
	 */
	
	
	public static int getColNumber(String colName)
	{
		
		
		try {
			
			int columnNumber = -1;
			
			int noOfColumns = ExcelWSheet.getRow(0).getLastCellNum();

			String[] Headers = new String[noOfColumns];

			for (int cnt=0;cnt<noOfColumns;cnt++)
			{
				Headers[cnt] = ExcelWSheet.getRow(0).getCell(cnt).getStringCellValue();
			}
			
			for (int colNo=0;colNo<noOfColumns;colNo++)
			{
				if(Headers[colNo].equals(colName))
				{
					columnNumber = colNo;
				}
			}
			
			return columnNumber;
			
		} catch (Exception e) {
			
			Log.error("----------Exception Occured while getting column number for reading Excel file---------", e);
			return -1;
		}
		
		
			
	}
	
	/**
	 * This method is to read the test data from the Excel cell, in this we are passing parameters as FileName,SheetName ,Row num and Col name
	 * @param fileName TODO
	 * @param sheetName TODO
	 * @param rowNo TODO
	 * @param colName TODO
	 * @return TODO
	 */
	
	
	public static String getCellData(String fileName, String sheetName, int rowNo, String colName)
	{
		
		try {
			
			setExcelFile(fileName, sheetName);
			int colNo = getColNumber(colName);
			Cell = ExcelWSheet.getRow(rowNo).getCell(colNo);
			String CellData = Cell.getStringCellValue();
			return CellData;
			
		} catch (Exception e) {
			
			Log.error("----------Exception Occured while reading data from Excel file---------", e);
			return "Excetion";
			
		}
	}

}
