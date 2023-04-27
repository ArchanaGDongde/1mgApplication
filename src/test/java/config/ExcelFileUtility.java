package config;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtility
{
	// Read the data from Excel file
	public String readDataFromExcel(String sheet, int row, int cell) throws IOException, EncryptedDocumentException, InvalidFormatException 
	{
		//FileInputStream fis= new FileInputStream(ConstantsUtility.ExcelFilePath);
		FileInputStream fis= new FileInputStream("C:\\Users\\archa\\Downloads\\1mgApplication\\1mgApplication\\src\\test\\resources\\Data\\QA\\FlipkartProduct.xlsx");
		Workbook wb= WorkbookFactory.create(fis);
		
		org.apache.poi.ss.usermodel.Sheet s = wb.getSheet(sheet);
		Row r= s.getRow(row);
		Cell cel = r.getCell(cell);
		String value = cel.getStringCellValue();
		wb.close();
		return value;
	}
}
/*
FileInputStream inputstream= new FileInputStream(excelFilePath);

XSSFWorkbook workbook= new XSSFWorkbook(inputstream);

XSSFSheet sheet=workbook.getSheet(sheetname);

Row r=sheet.getRow(rowNum);

Cell c=r.getCell(cellNum);

retVal= c.getStringCellValue();

return retVal;


*/