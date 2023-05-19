package pageObjects;

import java.util.List;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import config.ExcelFileReader;
import helper.LoggerHelper;

public class MgApp extends BasePage {
	String baseURL;
	Logger log=LoggerHelper.getLogger(LoggerHelper.class);
	SoftAssert softAssert=new SoftAssert();
	
	
	public MgApp(WebDriver webDriver, String baseURL2) {
		super(webDriver);
		this.baseURL=baseURL;
		PageFactory.initElements(webDriver, this);
		
	}
	
	
	
	@FindBy(how = How.XPATH, using = "//input[@placeholder=\"Enter your city\"]")
	private WebElement verifyhomepage;
	
	@FindBy(how = How.XPATH, using = "//input[@id=\"location-selector\"]")
	private WebElement locationbox;
	
	@FindBy(how = How.XPATH, using = "//*[@id=\"top-div\"]/div[3]/div/a/div")
	private WebElement ordernow;
	
	@FindBy(how = How.XPATH, using = "//div[@class='style__close-icon___3FflV']")
	private WebElement closeAdd;
	
	@FindAll({@FindBy(how = How.XPATH, using = "//div[@class='row styles__bottom-row___1a89P styles__border-one___2wAfi']//ul[1]/li")})
	private List<WebElement> locationlist;
	
	@FindBy(how = How.XPATH, using = "//*[@id=\"srchBarShwInfo\"]")
	private WebElement searchbox;

	@FindBy(how = How.XPATH, using = "//span[text()=\"Quick order\"]")
	private WebElement verifylocationpage;

	
	@FindAll({@FindBy(how = How.XPATH, using = "//ul[@class=\"styles__search-results___3rJOl\"]/li")})
	private List<WebElement> searchlist;
	
	
	@FindBy(how = How.XPATH, using = "//div[@class=\"style__cart-button___3CZnL\"]/div")
	private WebElement addtocartbtn;
	
	@FindBy(how = How.XPATH, using = "//span[text()=\"Uses and benefits\"]")
	private WebElement verifysearchpage;

	@FindBy(how = How.XPATH, using = "//h1[text()=\"Dolo 650 Tablet\"]")
	private WebElement verifydeliverypage;
	
	
	@FindBy(how = How.XPATH, using = "//div[@class=\"CartCounter__cart-img___3xOz1 CartCounter__cart-black___RXZ58\"]")
	private WebElement windowpopup;

	@FindBy(how = How.XPATH, using = "	//div[@class=\"styles__profile-navigation___3SV-l\"]/div[4]")
	private WebElement proceedtocart;
	
	@FindBy(how = How.XPATH, using = "//span[text()=\"Remove\"]")
	private WebElement removetext;
	
	@FindBy(how = How.XPATH, using = "//div[text()=\"strip of 15 tablets\"]")
	private WebElement verifycartpage;
	
	@FindBy(how = How.XPATH, using = "//a[text()=\"ADD MEDICINES\"]")
	private WebElement verifydeletedcartpage;
	
	
	
	@FindBy(how = How.XPATH, using = "//*[@id=\"drug_header\"]/div/div/div[2]/div[1]")
	private WebElement descriptionlist;
	
	//*[@id=\"drug_header\"]/div/div/div[2]/div[1]/div/div
	
	@FindBy(how = How.XPATH, using = "//div[text()=\"Update\"]")
	private WebElement updatecity;
	
	
	public void verifyHomePageIsDisplayed() {
		try {

			log.info("verify home page is displayed succesfully");
			Assert.assertTrue(verifyhomepage.isDisplayed());
			log.info("verified home page is displayed succesfully");
		} catch (Exception e) {

			log.error("not verified home page is displayed succesfully");
			e.printStackTrace();
			throw e;
		}
	}




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
	

	public void clickOnLocationBox() {
		try{
			log.info("click on location box successfully");
			Assert.assertTrue(locationbox.isDisplayed());
			
			doHoverandClick(closeAdd);
			doHoverandClick(updatecity);
			doHoverandClick(closeAdd);
			doHoverandClick(locationbox);
			
			
	String optionToSelect="Bengaluru";
			int count=0;
			
			//writeText( locationbox,ExcelFileReader.getCellValue("orangehrm.xlsx","Sheet1",13,"A"));
			
			
			for(WebElement ele:locationlist) {
				
			String currentOption=ele.getText();
				if(currentOption.contains(optionToSelect))
				{
				ele.click();
				count++;
				break;
			
			}
			}
			if(count!=0) {
				System.out.println(optionToSelect + "has been selected in the dd");
			}
			else {
				System.out.println("option you want to select is not available in dd");
			}
			
			
			log.info("Clicked on  location box  successfully");
			
		} 
		catch  (Exception e) 
		{
		log.error(" location box   is not executed succesfully"); 
		e.printStackTrace(); 
		throw e;
		}
	
}
	public void verifyEnteredLocationPage() {
		try {
			
			
			log.info("verify entered location page successfully");
	
		//Assert.assertTrue(verifylocationpage.isDisplayed());			
		softAssert.assertEquals(verifylocationpage.getText(), "Quick order");
			softAssert.assertAll();
			log.info("verified entered location page successfully");
			
			
		}
		catch(Exception e) {
			log.error("not  verified entered location page successfully"); 
			e.printStackTrace(); 
			throw e;
			
		}
	}

	public void clickOnSearchbox() {
		try{
			log.info("click on search box successfully");
			//waitUntilElementVisible(searchbox);
			Assert.assertTrue(searchbox.isDisplayed());
			//doHoverandClick(updatecity);
			//doHoverandClick(ordernow);
			//doHoverandClick(searchbox);
			
			String optionToSelect="Dolo 650 Tablet";
			int count=0;
	//	waitUntilElementToBeClickable(locationbox);
			writeText( searchbox,ExcelFileReader.getCellValue("orangehrm.xlsx","Sheet1",15,"A"));
			
			
			for(WebElement ele:searchlist) {
				
			
				String curentOption=ele.getText();
				if(curentOption.contains(optionToSelect))
				{
					ele.click();
				count++;
				break;
			
			}
			}
			
			
			log.info("Clicked on  search box  successfully");
			
		} 
		catch  (Exception e) 
		{
		log.error(" search box   is not executed succesfully"); 
		e.printStackTrace(); 
		throw e;
		}
	
}
	public void verifySearchedPage() {
		try{
			log.info("verify serached page successfully");
			waitUntilElementVisible(verifysearchpage);
			softAssert.assertEquals(verifysearchpage.getText(),"Uses and benefits");
			softAssert.assertAll();
			
			log.info("verified serached page successfully");
			
		} 
		catch  (Exception e) 
		{
		log.error(" not verified searched page succesfully"); 
		e.printStackTrace(); 
		throw e;
		}
	
}
	
	public void clickOnAddToCartBtn() {
		try{
			log.info("click on cart btn successfully");
		
		//	waitUntilElementVisible(addtocartbtn);
			doHoverandClick(addtocartbtn);
			doHoverandClick(proceedtocart);
			
	//doHoverandClick(windowpopup);
			    
			    
			log.info("clicked on cart btn successfully");
			    
		
		}
		catch  (Exception e) 
		{
		log.error(" not clicked on cart btn successfully"); 
		e.printStackTrace(); 
		throw e;
		}
	
}
	public void checkDeliveryPage() {
		try {
			
			
			log.info("check delivery page successfully");
			//waitUntilElementVisible(verifydeliverypage);
		Assert.assertTrue(verifydeliverypage.isDisplayed());		
		
			log.info("checked delivery page successfully");
			
		}
		catch(Exception e) {
			log.error("not checked delivery page successfully"); 
			e.printStackTrace(); 
			throw e;
			
		}
	}
	public void deleteItemFromCart() {
		try{
			log.info("click on proceed to cart btn successfully");
		
			
			
			
			doHoverandClick(removetext);
			closeWebBrowser();    
			    
			log.info("clicked on proceed to  cart btn successfully");
			    
		
		}
		catch  (Exception e) 
		{
		log.error(" not clicked on proceed to  cart btn successfully"); 
		e.printStackTrace(); 
		throw e;
		}
	
}
	public void verifyCartPage() {
		try{
			log.info("verify cart page successfully");
			waitUntilElementVisible(verifycartpage);
			softAssert.assertEquals(verifycartpage.getText(),"strip of 15 tablets");
			softAssert.assertAll();
			log.info("verified cart page successfully");
			
		} 
		catch  (Exception e) 
		{
		log.error(" not verified cart page succesfully"); 
		e.printStackTrace(); 
		throw e;
		}
	
}
	public void verifyDeletedCartPage() {
		try{
			log.info("verify deleted cart page successfully");
			//waitUntilElementVisible(verifydeletedcartpage);
			softAssert.assertEquals(verifydeletedcartpage.getText(),"ADD MEDICINES");
			softAssert.assertAll();
			log.info("verified deleted cart page successfully");
			
		} 
		catch  (Exception e) 
		{
		log.error(" not verified deleted cart page succesfully"); 
		e.printStackTrace(); 
		throw e;
		}
	
}
	public void verifyProductDetailsWithExcelSheet() throws Exception {
	try{
		log.info("verify Product Details With Excel Sheet successfully");
		
	XSSFWorkbook book = new XSSFWorkbook("C:\\Users\\archa\\Downloads\\1mgApplication\\1mgApplication\\src\\test\\resources\\Data\\QA\\OrangeHRM.xlsx");
			XSSFSheet sheet=book.getSheetAt(0);
			Row row=sheet.getRow(0);
			Cell cell=row.getCell(3);
			String ExpectedTitle=cell.getStringCellValue();
			System.out.println("expected title is:" +ExpectedTitle);
			
				String text=descriptionlist.getText();
				System.out.println("actual text is:" +text);

				if(ExpectedTitle.equals(text)) {
			    	System.out.println("test pawss");
							
				}
				else {
					
				        System.out.println("test fail");
					}
		
		log.info("verified Product Details With Excel Sheet successfully");
	}	
	 
	catch  (Exception e) 
	{
	log.error(" not verified Product Details With Excel Sheet successfully"); 
	e.printStackTrace(); 
	throw e;
	}

}
}

		
		    
			
			
			
			
	