package pageObjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import helper.LoggerHelper;
import config.ExcelFileReader;
import config.ExcelFileUtility;
public class OrangeHRMPage extends BasePage
{
	public ExcelFileReader eutil;
	
	String baseURL;
	Logger log=LoggerHelper.getLogger(LoggerHelper.class);
	SoftAssert softAssert=new SoftAssert();
	public String name;
	public String pswd;
	
	public OrangeHRMPage(WebDriver webDriver, String baseURL2) 
	{
		super(webDriver);
		this.baseURL=baseURL;
		PageFactory.initElements(webDriver, this);		
	}
		 
		@FindBy(how = How.NAME, using ="username")
		private WebElement userNameEdt;
		
		@FindBy(how = How.NAME, using="password")
		private WebElement userPasswordEdt;
		
		@FindBy(how = How.XPATH, using="//button[@type='submit']")
		private WebElement loginBtn;
		
		@FindBy(how = How.XPATH, using="//button[@type='submit']")
		private WebElement adminBtn;
		
		@FindBy(how = How.XPATH, using="//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[1]/div/div[2]/input")
		private WebElement userNameTxt;
		
		@FindBy(how=How.XPATH, using = "//button[text()=' Reset ']")
		private WebElement restBtn;
		
		/** Orange HRM Login Page	 
		 * @throws Exception **/
		public void getUserNameEdt(String username) throws Exception {
			try {
				
				 name = eutil.readDataFromExcel("Sheet1", 1, 1);
			  getUserNameEdt(name);			
				userNameEdt.sendKeys(username);
				
				} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		}

		public void getUserPasswordEdt(String password) {	
			try {
				 pswd = eutil.readDataFromExcel("Sheet1", 1, 2);

				userPasswordEdt.sendKeys(pswd);
			} catch (Exception e) {		
				e.printStackTrace();
			}
		}

		public void getLoginBtn() {
		
			try {
				loginBtn.click();
				BasePage.closeWebBrowser();
			} catch (Exception e) 
			{
				e.printStackTrace();
				throw e;
			}
		}
		
		/** Orange HRM Admin Page	 **/
		
		public void clickOnAdminBtn() 
	    {
		  try {
			adminBtn.click();
		} catch (Exception e) 
		  {
			e.printStackTrace();
			throw e;
		}     
	    }

	   public void usernameTextField(String username) 
	    {
		   try {
			userNameTxt.sendKeys(username);
		} catch (Exception e) {
			e.printStackTrace(); 
			throw e;
		}
	    }

	   public void resetbtn() 
	    {
		   try {
			restBtn.click();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	    }
}
		
		