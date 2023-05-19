package stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.BasePage;
import pageObjects.BseIndiaPage;
import pageObjects.MgApp;
import util.TestContext;

public class BseIndiaSteps  
{
	TestContext testContext;
	private BseIndiaPage bseIndia;
	BasePage bp;
	
	public BseIndiaSteps(TestContext testContext) {
		this.testContext=testContext;
		this.bseIndia=testContext.getPageObjectManager().getBseIndiaPage();
	}
	
	@Given("^User is on bseindia homepage$")
	public void user_is_on_bseindia_homepage() throws Throwable 
	{
		bseIndia.goToHomePage();
	}

	@When("^User clicks on Gainers$")
	public void user_clicks_on_Gainers() throws Throwable 
	{
		bseIndia.clickOnGainersTab();
	}

	@And("^Expand by clicking on arrow button$")
	public void expand_by_clicking_on_arrow_button() throws Throwable 
	{
		bseIndia.clickOnGainersArrow();
	}

	@Then("^Fetch the total number of rows displayed in all pages$")
	public void fetch_the_total_number_of_rows_displayed_in_all_pages() throws Throwable
	{
		bseIndia.rowCountInTable();
	}

	@And("^Store the values of every fifth in to Excel$")
	public void store_the_values_of_every_fifth_in_to_Excel() throws Throwable 
	{
		bseIndia.writeDataToExcel();
		System.out.println("executed step file 1");
		
//		bp.closeWebBrowser();

	}

}
