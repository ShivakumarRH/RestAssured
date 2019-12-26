package payeezy;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class GetExcelData {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		FileInputStream fis=new FileInputStream("E:\\Work\\TestData\\Data.xlsx");
		
		XSSFWorkbook workbook=new XSSFWorkbook(fis); 
		
		XSSFSheet sheet=workbook.getSheet("Sheet1");
		
		int rowcount=sheet.getLastRowNum();
		
		int lastcol=sheet.getRow(0).getLastCellNum();
		
			
		System.out.println(rowcount);
		System.out.println(lastcol);
	}

}
