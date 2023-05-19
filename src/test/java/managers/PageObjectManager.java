package managers;
import org.openqa.selenium.WebDriver;

import pageObjects.BseIndiaPage;
import pageObjects.LoginPage;
import pageObjects.MgApp;
import pageObjects.OrangeHRMPage;
import pageObjects.ProductPage;


//import pageObjects.Practice;
public class PageObjectManager {

	private WebDriver webDriver;
	private String baseURL;
	private LoginPage loginPage;
	private MgApp mgapp;
	private ProductPage prdtpg;
	private OrangeHRMPage ohrm;
	private BseIndiaPage bseIndia;

		
	public PageObjectManager(WebDriver webDriver, String baseURL) {
		this.webDriver = webDriver;
		this.baseURL = baseURL;
	
	}

	public LoginPage getLoginPage() 
	{
		return (this.loginPage == null) ? new LoginPage(webDriver, this.baseURL) : this.loginPage;	   
	}

	
	public MgApp getMgApp() 
	{
		return(this.mgapp==null)? new MgApp(webDriver,this.baseURL): this.mgapp;
	}

	public ProductPage getProductPage() 
	{
		
		return(this.prdtpg==null)? new ProductPage(webDriver,this.baseURL): this.prdtpg;
	}

	public OrangeHRMPage getOrangeHRMPage() 
	{
		return(this.ohrm==null)? new OrangeHRMPage(webDriver,this.baseURL): this.ohrm;	
	}

	public BseIndiaPage getBseIndiaPage() 
	{
		return (this.bseIndia== null)? new BseIndiaPage(webDriver, this.baseURL): this.bseIndia ;
	}
	
}	
	
	



	
	

	


	
	


	
	



