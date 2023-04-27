package pageObjects;

import org.testng.annotations.Test;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import helper.LoggerHelper;


public class LoginPage extends BasePage{
	
	String baseURL;
	Logger log = LoggerHelper.getLogger(LoggerHelper.class);
	 
	
	public LoginPage(WebDriver webDriver, String baseURL) {
		super(webDriver);
		this.baseURL = baseURL;
		PageFactory.initElements(webDriver, this);
	}

	@FindBy(how = How.NAME, using = "username")
	private WebElement txtBx_Username;
	@FindBy(how = How.NAME, using = "password")
	private WebElement txtBx_Password;
	
	@FindBy(how = How.XPATH, using = "//button[@type='submit']")
	private WebElement login_btn;


	public void goToLoginPage() {
		try {
			log.info("Entered goToLoginPage succesfully");	
			webDriver.get(baseURL);
		    waitFor(2000);
		} catch (Exception e) {
            log.error("Not Executed goToLoginPage");
			e.printStackTrace();
			throw e;
		}
	}

	public void enter_Username() {
		try {
			log.info("Entered enter_Username succesfully");
			String username;
			String userKey;
			String targetEnv;

			FileReader reader = null;
			try {
				reader = new FileReader("src/test/resources/configs/Environment.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			Properties p = new Properties();
			try {
				p.load(reader);
			} catch (IOException e) {
				e.printStackTrace();
			}
			targetEnv = p.getProperty("target_env");
//			      
			userKey = targetEnv + "_AdminUsername";
			username = p.getProperty(userKey);
			txtBx_Username.clear();
			txtBx_Username.sendKeys(username);
			log.info("Executed enter_Username Successfully");

		} catch (Exception e) {
			log.error("Not Executed enter_Username");

			e.printStackTrace();
			throw e;
		}
	}

	public void enter_Password() {

		try {

			log.info("Entered enter_Password succesfully");
			String password;
			String passKey;
			String targetEnv;

			FileReader reader = null;
			try {
				reader = new FileReader("src/test/resources/configs/Environment.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			Properties p = new Properties();
			try {
				p.load(reader);
			} catch (IOException e) {
				e.printStackTrace();
			}
			targetEnv = p.getProperty("target_env");

			passKey = targetEnv + "_AdminPassword";
			password = p.getProperty(passKey);

			System.out.println(p.getProperty("password"));
			txtBx_Password.clear();
			txtBx_Password.sendKeys(password);

			log.info("Executed enter_Password Successfully");

		} catch (Exception e) {

			log.error("Not Executed enter_Password");
			e.printStackTrace();
			throw e;
		}
	}

	public void clickLoginButton() throws Exception {

try{
			log.info("Entered clickSubmitButton succesfully");
			login_btn.click();
		//waitForMessageLonger("Logout");
			//waitForMessageLonger("Logout");
			//waitFor(2000);
			
			log.info("Executed clickSubmitButton Successfully");
		} 
		catch  (Exception e) 
		{
			
			log.error("Not Executed clickSubmitButton"); 
			e.printStackTrace(); 
			throw e;
		}
	}	
	
}
