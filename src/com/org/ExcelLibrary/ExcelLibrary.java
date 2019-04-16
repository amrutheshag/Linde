package com.org.ExcelLibrary;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class ExcelLibrary
{
	
	public String readDataFromExcel(String sheetName, int rowNum, int cellNum)
	{
		String retVal=null;
			 try {
				 FileInputStream fis = new FileInputStream("./Data//data.xlsx");
				 XSSFWorkbook workbook=new XSSFWorkbook(fis);
				 XSSFSheet sheet=workbook.getSheet(sheetName);
				 XSSFRow row=sheet.getRow(rowNum);
				 XSSFCell cell = row.getCell(cellNum);
				 retVal=cell.getStringCellValue();
				System.out.println(retVal);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
		
		return retVal;
	}

	public void writeToExcel(String sheetName, int rowNum, 
			int cellNum, String data){
	
			try {
				 FileInputStream fis = new FileInputStream("./Data//data.xlsx");
				 XSSFWorkbook workbook=new XSSFWorkbook(fis);
				 XSSFSheet sheet=workbook.getSheet(sheetName);
				 XSSFRow row=sheet.getRow(rowNum);
				 XSSFCell cell = row.createCell(cellNum);
				cell.setCellValue(data);
				
				FileOutputStream fos = new FileOutputStream("./Data//data.xlsx");
				workbook.write(fos);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
	}
	public String[][]  getExcelData(String excelLocation,String sheetName)
	{
		String dataSets[][]=null;
			try {
				FileInputStream fis = new FileInputStream(new File(excelLocation));
				XSSFWorkbook workbook=new XSSFWorkbook(fis);
				XSSFSheet sheet = workbook.getSheet(sheetName);
				int totalRow=sheet.getLastRowNum()+1;
				int totalColumn=sheet.getRow(0).getLastCellNum();
				
				dataSets=new String[totalRow-1][totalColumn];
				Iterator<Row> rowIterator = sheet.iterator();
				int i=0;
				int t=0;
				while(rowIterator.hasNext())
				{
					Row row=rowIterator.next();
					if(i++!=0)
					{
						int k=t;
						t++;
						
						Iterator<Cell> cellIterator = row.cellIterator();
						int j=0;
						while(cellIterator.hasNext())
						{
							Cell cell=cellIterator.next();
							switch(cell.getCellType())
							{
							case Cell.CELL_TYPE_NUMERIC:
								dataSets[k][j++]=cell.getStringCellValue();
								System.out.println(cell.getNumericCellValue());
								break;
								
							case Cell.CELL_TYPE_STRING:
								dataSets[k][j++]=cell.getStringCellValue();
								System.out.println(cell.getStringCellValue());
								break;
								
							case Cell.CELL_TYPE_BOOLEAN:
								dataSets[k][j++]=cell.getStringCellValue();
								System.out.println(cell.getNumericCellValue());
								break;
								
							
							}
						}
						System.out.println("");
					}
			
				}
				fis.close();
				return dataSets;
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
			return null;
			
	}
}
