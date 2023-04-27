package stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import util.TestContext;

	public class ProductVerify
	{

		TestContext testContext;
		private pageObjects.ProductPage prdtpg;
		
		
		public ProductVerify(TestContext testContext) {
			this.testContext=testContext;
			this.prdtpg=testContext.getPageObjectManager().getProductPage();
		}
			
		
		@Given("^User Search for a item and Check that the product is displayed")
	public void User_Search_for_a_item_and_Check_that_the_product_is_displayed() throws Throwable 
	{
	    prdtpg.closePopUp();
	    prdtpg.searchBoxFld();
	    prdtpg.searchIcon();
		
	}

//	@When("^Check the product details match the expected values fetched from excel sheet$")
//	public void Check_the_product_details_match_the_expected_values_fetched_from_excel_sheet() throws Throwable
//	{	
////		prdtpg.prdtInfo();
//		
//		prdtpg.prdtpriceInfo();
//	}

	@Then("^Add the item to the cart$")
	public void Add_the_item_to_the_cart() throws Throwable 
	{
		prdtpg.addToCartButn();
		prdtpg.clickOnCart();
	}

	@Then("^Delete the item from the cart and verify that item is deleted$")
	public void Delete_the_item_from_the_cart_and_verify_that_item_is_deleted()
	{
		//	prdtpg.removeButn();
//			prdtpg.clickRemoveBtn();
		
			prdtpg.verifyRemoved();
	
		//System.out.println("verify Removed");
	}	
}
