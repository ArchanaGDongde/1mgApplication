package stepdefs;

import java.io.IOException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.LoginPage;
import pageObjects.OrangeHRMPage;
import util.TestContext;

public class OrangeHRMLogingStepTest 
{
	TestContext testContext;
	private LoginPage loginPage;
	private OrangeHRMPage ohrm;
		public OrangeHRMLogingStepTest(TestContext testContext) throws IOException
		{
			this.testContext = testContext;
			this.loginPage= testContext.getPageObjectManager().getLoginPage();
			this.ohrm= testContext.getPageObjectManager().getOrangeHRMPage();
		}


	@Given("^user is on login page$")
public void user_is_on_login_page() throws Throwable 
{
		loginPage.goToLoginPage();
}

@When("^user enters username and password$")
public void user_enters_username_and_password() throws Throwable
{	
	loginPage.enter_Username();
	loginPage.enter_Password();

}

@Then("^clicks on login button$")
public void clicks_on_login_button() throws Throwable 
{
	ohrm.getLoginBtn();
}

}