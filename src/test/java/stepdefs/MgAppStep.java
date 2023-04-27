package stepdefs;

import cucumber.api.java.en.Then;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pageObjects.BasePage;
import pageObjects.MgApp;
import util.TestContext;

public class MgAppStep  {
	
	
	TestContext testContext;
	private MgApp mgapp;
	
	
	public MgAppStep(TestContext testContext) {
		this.testContext=testContext;
		this.mgapp=testContext.getPageObjectManager().getMgApp();
	}
		

	@Given("^User opens the 1mg application$")
    public void user_opens_the_1mg_application() throws Throwable 
	{
         mgapp.goToHomePage();
    }

    @When("^close the Ads Set the location as Bengaluru$")
    public void  close_the_ads_Set_the_location_as_Bengaluru() throws Throwable 
    {
    	mgapp.clickOnLocationBox();
    }
   

    @And("Search for a tablet")
    public void search_for_a_tablet() throws Throwable 
    {
    	mgapp.clickOnSearchbox();
    }
    
    @When ("^If the delivery date is today add the tablet to cart otherwise no$")
    public void if_the_delivery_date_is_today_add_the_tablet_to_cart_otherwise_no() throws Exception
    {
    		mgapp.checkDeliveryPage(); 	
    		mgapp.clickOnAddToCartBtn();
    		//Smgapp.verifyProductDetailsWithExcelSheet();
    }

 /*   @Then("^check the car Quantity Amount and description match$")
    public void check_the_car_quantity_amount_and_description_match() throws Exception 
    {
    	mgapp.verifyProductDetailsWithExcelSheet();
        throw new io.cucumber.java.PendingException();
    }
*/
 
    @And("^remove the tablet from cart$")
    public void remove_the_tablet_from_cart() throws Throwable
    {
    
    	mgapp.deleteItemFromCart();
    	
    }

     
}