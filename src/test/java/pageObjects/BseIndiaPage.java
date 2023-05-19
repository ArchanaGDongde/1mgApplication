package pageObjects;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import config.ExcelFileReader;
import helper.LoggerHelper;

public class BseIndiaPage extends BasePage
{
	String baseURL;
	Logger log=LoggerHelper.getLogger(LoggerHelper.class);
	SoftAssert softAssert=new SoftAssert();
	BasePage base;
	ExcelFileReader excel;
	
	List<String> data;
	
	public BseIndiaPage(WebDriver webDriver, String baseURL2) 
	{
		super(webDriver);
		this.baseURL= baseURL;
		this.webDriver=webDriver;
		PageFactory.initElements(webDriver, this);
	}
	
	
	@FindBy(how=How.XPATH, using = "//ul[@class='nav nav-tabs responsive-tabs']")
	private WebElement gainersIndex;
	
	@FindBy(how = How.ID, using ="gainer")
	private WebElement gainersTab;
	
	@FindBy(how= How.XPATH, using="//a[@aria-label='More information on Gainers']")
	private WebElement gainersArrow;
	
	@FindBy(how=How.XPATH, using = "//tr[@class='ng-scope']")
	private List<WebElement>  allrows;
	
	@FindBy(how=How.XPATH,using = "//a[text()='Next »']")
	private WebElement nextButton;
	
	public void goToHomePage() {
		try {
			log.info("Entered goToHomePage succesfully");	
			//webDriver.get(baseURL);
		
		   
		} catch (Exception e) {
            log.error("Not Executed goToHomePage successfully");
			e.printStackTrace();
			throw e;
		}
	}
	
	public void clickOnGainersTab() 
	{
		try 
		{
			log.info("Click on Gainers Tab");
			BasePage.ScrollUpToElement(gainersIndex);
			//BasePage.waitUntilElementToBeClickable(gainersTab); 
			BasePage.waitUntilElementToBeClickableScrolling(gainersTab);
			gainersTab.click();
		} catch (Exception e) {
			log.error("Clicked on Gainers Tab");
			e.printStackTrace();
			throw e;
		}
	}
	
	public void clickOnGainersArrow() 
	{
		try 
		{
			log.info("Click on Gainers Tab");
			gainersArrow.click();
		} catch (Exception e) {
			log.error("Clicked on Gainers Tab");
			e.printStackTrace();
			throw e;
		}
	}

	public void rowCountInTable() throws InterruptedException 
	{
	//	List<WebElement> allrows= driver.findElements(By.xpath("//tr[@class='ng-scope']"));
		int si=allrows.size();
		System.out.println("Number of rows:"+si);
		
		for(int i=0; i<si; i++) {
			WebElement option=allrows.get(i);
			String te=option.getText();
			System.out.println(te);		
		}
		
		data = new ArrayList<String>();
		for (int i = 4; i < allrows.size(); i += 5) {
			WebElement row = allrows.get(i);
			List<WebElement> cells = row.findElements(By.tagName("td"));
			String value = cells.get(0).getText(); 
			data.add(value);
	}
}
	
	
	public void writeDataToExcel() throws IOException 
	{
		Workbook workbook = new XSSFWorkbook();
		org.apache.poi.ss.usermodel.Sheet sheet = workbook.createSheet("Data");

		int rowNum = 0;
		for (String value : data) {
			org.apache.poi.ss.usermodel.Row row = sheet.createRow(rowNum++);
			org.apache.poi.ss.usermodel.Cell cell = row.createCell(0);
			cell.setCellValue(value);
		}

		String fileName = "data.xlsx";
		FileOutputStream outputStream = new FileOutputStream(fileName);
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
	}
	

public void writeDataToExcel2() throws IOException 
{
	Workbook workbook = new XSSFWorkbook();
	org.apache.poi.ss.usermodel.Sheet sheet = workbook.createSheet("Data");

	int rowNum = 0;
	for (String value : data) {
		org.apache.poi.ss.usermodel.Row row = sheet.createRow(rowNum++);
		org.apache.poi.ss.usermodel.Cell cell = row.createCell(0);
		cell.setCellValue(value);
	}

	String fileName = "data2.xlsx";
	FileOutputStream outputStream = new FileOutputStream(fileName);
	workbook.write(outputStream);
	workbook.close();
	outputStream.close();
}

public void rowCount() throws InterruptedException 
{
//	List<WebElement> allrows= driver.findElements(By.xpath("//tr[@class='ng-scope']"));
	int si=allrows.size();
	System.out.println("Number of rows:"+si);
	
	for(int i=0; i<si; i++) {
		WebElement option=allrows.get(i);
		String te=option.getText();
		System.out.println(te);		
	}
	
	data = new ArrayList<String>();
	for (int i = 4; i < allrows.size(); i += 5) {
		WebElement row = allrows.get(i);
		List<WebElement> cells = row.findElements(By.tagName("td"));
		String value = cells.get(0).getText(); 
		data.add(value);
}

}
}
