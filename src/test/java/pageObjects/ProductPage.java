package pageObjects;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import helper.LoggerHelper;

public class ProductPage extends BasePage
{
	String baseURL;
	Logger log=LoggerHelper.getLogger(LoggerHelper.class);
    SoftAssert softAssert=new SoftAssert();
    
	public ProductPage(WebDriver webDriver, String baseURL2) {
		super(webDriver);
		this.baseURL=baseURL;
		PageFactory.initElements(webDriver, this);
		
	}
	 
	@FindBy(how = How.XPATH, using = "//button[@class=\"_2KpZ6l _2doB4z\"]")
	private WebElement closebtn;
	
	@FindBy(name="q")
	private WebElement searchBox;
	
	@FindBy (how = How.XPATH, using = "//button[@type=\"submit\"]")
	private WebElement searchicon;
	
	@FindBy(xpath = "(//div[@class=\"_4rR01T\"])[1]")
	private WebElement item;
	
	@FindBy(how = How.XPATH, using = "//button[@class='_2KpZ6l _2U9uOA _3v1-ww']")
	private WebElement addToCartBtn;
	
	@FindBy(how = How.XPATH, using = "//span[text()='Cart']")
	private WebElement cart;
	
	@FindBy(how = How.XPATH, using = "//div[@class=\"_3YT0U7 _10vWcL td-FUv WDiNrH\"]/div[2]")
	private WebElement removeBtn;
	
	@FindBy(how = How.XPATH, using = "//div[text()='Missing Cart items?']")
	private WebElement missingCart;
	
	@FindBy(how = How.XPATH, using = "(//div[@class=\"_4rR01T\"])[1]")
	private WebElement prdtInfo;
	
	@FindBy(how = How.XPATH, using = "//div[@class='_30jeq3 _16Jk6d']")
	private WebElement prdtpriceInfo;
	
	@FindBy(how = How.XPATH, using = "//div[text()='Remove']")
	private WebElement removebtn;
	
	
	//Rule 4: provide getters to access the elements

	public void closePopUp() 
	{
		try {
			log.info("Close the Pop up");

			closebtn.click();
			log.info("Pop up closed successfully");

		} catch (Exception e) {
			log.error("Pop up not closed");
			e.printStackTrace();
			throw e;
		}
	}
	public void searchBoxFld()
	{
		try {
			log.info("Search the product");

			searchBox.sendKeys("dell laptop i5 12th generation");
			log.info("Searched the product sucessfully");

		} catch (Exception e) {
			log.error("Product failed to Search");
			e.printStackTrace();
			throw e;
		}
	}
	
	public void searchIcon() 
	{
		try {
			log.info("Click on search icon");
			searchicon.click();
			log.info("Clicked on search icon sucessfully");

		} catch (Exception e) {
			log.error("Not clicked on search icon");
			e.printStackTrace();
			throw e;
		}
	}
	public void clickOnItem() 
	{
		try {
			
			log.info("Click on searched item");
			item.click();
			log.info("Clicked on searched item succesfully");

		} catch (Exception e) 
		{
			log.error("Not clicked on searched item");
			e.printStackTrace();
			throw e;
		}
	}

	public void addToCartButn()
	{	
		try {
			log.info("Add Product to cart");
			clickOnItem();	
			Set<String> windowIds = webDriver.getWindowHandles();
			Iterator<String> iterate = windowIds.iterator();
			String parentId = iterate.next();
			String childId= iterate.next();
			webDriver.switchTo().window(childId);
			addToCartBtn.click();
			log.info("Product added to the cart sucessfully");
			//ereport.test.log(LogStatus.PASS,"Added item to the cart");
		} catch (Exception e)
		{
			log.error("Product not added to the cart");
			e.printStackTrace();
			throw e;
		}
	}
	public void clickOnCart() 
	{
		try {
			log.info("Click on the cart");
			cart.click();
			log.info("Clicked on the cart sucessfully");
		} catch (Exception e) 
		{
			log.error("Not clicked on the cart");
			e.printStackTrace();
			throw e;
		}
	}
	public void removeButn()
	{	
		try {
			log.info("Remove item from cart");
			removeBtn.click();
			log.info("Item removed from the cart successfully");
		} catch (Exception e) {
			log.error("Item not able to remove from the cart");
			e.printStackTrace();
			throw e;
		}
	}
	
	
	 public void verifyRemoved() 
	 {
		try {
			log.info("Assert missing cart item");
			boolean isDisplayed = missingCart.isDisplayed();
			Assert.assertTrue(isDisplayed, "Missing item not displayed");
			log.info("Asserted missing cart item");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Assert failed for missing cart item");
			throw e;
		}		
	}
	 
	 public void prdtInfo() throws EncryptedDocumentException, InvalidFormatException, IOException 
	 {
			try {
				log.info("User check the product information from the Excel");
				prdtInfo.getText();
				System.out.println(prdtInfo);
				//String product = eutil.readDataFromExcel("Sheet1", 1, 0);

				XSSFWorkbook book = new XSSFWorkbook("C:\\Users\\archa\\Downloads\\1mgApplication\\1mgApplication\\src\\test\\resources\\Data\\QA\\FlipkartProduct.xlsx");
				XSSFSheet sheet=book.getSheet("Sheet1");
				Row row=sheet.getRow(1);
				Cell cell=row.getCell(0);
				
				String product = cell.getStringCellValue();
//			Assert.assertEquals(product,prdtInfo);
				clickOnItem();
					
				Set<String> windowIds = webDriver.getWindowHandles();
				Iterator<String> iterate = windowIds.iterator();
				String parentId = iterate.next();
				String childId= iterate.next();
				webDriver.switchTo().window(childId);
				log.info("User checked the product information from the Excel successfully");
			} catch (IOException e) 
			{
				e.printStackTrace();
				log.info("Failed to check the product information from the Excel");
				throw e;

			}

		}
	 
	 public void prdtpriceInfo() throws EncryptedDocumentException, InvalidFormatException, IOException 
	 {
		 	try {
				log.info("User check the product price from the Excel");
				prdtpriceInfo.getText();

				//String price = eutil.readDataFromExcel("Sheet1", 1, 1);
				
				XSSFWorkbook book = new XSSFWorkbook("C:\\Users\\archa\\Downloads\\1mgApplication\\1mgApplication\\src\\test\\resources\\Data\\QA\\FlipkartProduct.xlsx");
				XSSFSheet sheet=book.getSheet("Sheet1");
				Row row=sheet.getRow(1);
				Cell cell=row.getCell(1);
				String price = cell.getStringCellValue();

				Assert.assertEquals(price,prdtpriceInfo);
				log.info("User checked the product price from the Excel successfully");

			} catch (IOException e) 
		 	{
				e.printStackTrace();
				log.error("Assertion failed  for verifying product information from the Excel");
				throw e;
			}
	}
	 
	 public void clickRemoveBtn() 
	 {
	
		 try {
			log.info("Click on remove button");
			removebtn.click();
			log.info("Successfully Clicked on remove product button from cart");
		} catch (Exception e) 
		 {
			e.printStackTrace();
			log.error("Not clicked on remove product button from cart");
			throw e;
		}
	}

}
