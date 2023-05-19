package config;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class ExcelFileReader extends ConfigFileReader{

    private static final Map<String, Workbook> workbookMap = new HashMap<String, Workbook>();

    public String readDataFromExcel(String sheet, int row, int cell) throws IOException, EncryptedDocumentException, InvalidFormatException 
	{
		//FileInputStream fis= new FileInputStream(ConstantsUtility.ExcelFilePath);
		FileInputStream fis= new FileInputStream("C:\\Users\\archa\\Downloads\\1mgApplication\\1mgApplication\\src\\test\\resources\\Data\\QA\\OrangeHRM.xlsx");
		Workbook wb= WorkbookFactory.create(fis);
		
		org.apache.poi.ss.usermodel.Sheet s = wb.getSheet(sheet);
		Row r= s.getRow(row);
		Cell cel = r.getCell(cell);
		String value = cel.getStringCellValue();
		wb.close();
		return value;
	}
    
    public static String getCellValue(String fileName, String sheetName, int rowNum, String columnName) {
        Workbook workbook = workbookMap.get("fileName");
        try {
            if (workbook == null) {
                workbook = WorkbookFactory.create(ExcelFileReader.class.getResourceAsStream(ConfigFileReader.getDataDir().trim() + fileName));
                workbookMap.put(fileName, workbook);
            }
            Sheet sheet = workbook.getSheet(sheetName);
            Cell cell = sheet.getRow(rowNum - 1).getCell(CellReference.convertColStringToIndex(columnName));
            if(cell != null)
                return readCellByType(cell, cell.getCellTypeEnum());
        } catch (Exception e) {
            return "";
        }
        return "";
    }

    private static String readCellByType(Cell cell, CellType type) {
        String txt = "";
        if (cell != null) {
            switch (type) {
                case NUMERIC:
                    txt = dateOrNumberProcessing(cell);
                    break;
                case STRING:
                    txt = String.valueOf(cell.getRichStringCellValue());
                    break;
                case FORMULA:
                    txt = readCellByType(cell, cell.getCachedFormulaResultTypeEnum());
                    break;
                case BLANK:
                    break;
                default:
                    break;
            }
        }
        return txt;
    }

    private static String dateOrNumberProcessing(Cell cell) {
        String txt;
        if (DateUtil.isCellDateFormatted(cell)) {
            final DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            txt = String.valueOf(formatter.format(cell.getDateCellValue()));
        } else {
            txt = String.valueOf(cell.getNumericCellValue());
        }
        return txt;
    }

    public static void main(String[] args) {
        System.out.println(ExcelFileReader.getCellValue("addpharmacy.xlsx","Sheet1", 1, "A"));
    }
    
    public void writeDataToExcel(String  value, int rowNum) throws IOException 
	{
    	Workbook workbook = new XSSFWorkbook();
		org.apache.poi.ss.usermodel.Sheet sheet = workbook.createSheet("Data");
		
			org.apache.poi.ss.usermodel.Row row = sheet.createRow(rowNum++);
			org.apache.poi.ss.usermodel.Cell cell = row.createCell(0);
			cell.setCellValue(value);
		

		String fileName = "data.xlsx";
		FileOutputStream outputStream = new FileOutputStream(fileName);
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
	}
}
