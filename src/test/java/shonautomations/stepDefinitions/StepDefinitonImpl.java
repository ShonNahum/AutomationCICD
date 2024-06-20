package shonautomations.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import shonautomations.PageObject.CartPage;
import shonautomations.PageObject.CheckOutPage;
import shonautomations.PageObject.ConfirmPage;
import shonautomations.PageObject.LandingPage;
import shonautomations.PageObject.ProductCatalog;
import shonautomations.TestComponents.BaseTest;

public class StepDefinitonImpl extends BaseTest{
	
	public LandingPage landingPage;
	public ProductCatalog productcatalog;
	public CheckOutPage checkoutpage;
	public CartPage cartpage;
	public ConfirmPage confirmpage;

	@Given("i landed on Ecommerce Page")
	public void i_landed_on_Ecommerce_page() throws IOException
	{
		landingPage = launchApp();
	}
	
	@Given("^logged in with username (.+) and password (.+)$") // (.+) respesent any value/character (its generic) its regular expression so we need put ^ in start and $ in end  only work in dynamic data
	public void logged_in_username_and_password(String username, String Password) {
		 productcatalog =  landingpage.LoginApplication(username,Password);
	}
	@When("^i add the product (.+) from Cart$")
	public void i_add_product_to_cart(String ProductName) {
		List<WebElement> products = productcatalog.GetProductList();
		productcatalog.AddToCart(ProductName);
		
	}
	@And("^Checkout (.+) and submit the order$")
	public void checkout_submit_order(String ProductName) throws InterruptedException
	{
		 cartpage =	productcatalog.GotToCartPage(); 
		Boolean match =cartpage.VerifyProductDisplay(ProductName);
		Assert.assertTrue(match);
		 checkoutpage =cartpage.GoToCheckOut();
		
		Thread.sleep(3000);
		checkoutpage.SelectCountry("india");
		 confirmpage =checkoutpage.SubmitOrder();
	}
	@Then("{string} message is displayed on ConfirmationPage") // its how to get string static data
	public void message_displayed_confirmationPage(String string) {
		System.out.println(confirmpage.GetConfirmMessage());
		Assert.assertTrue(confirmpage.GetConfirmMessage().equalsIgnoreCase(string));
		driver.close();
	}
	
	@Then ("^\"([^\"]*)\" message is displayed$")
	public void something_message_is_displayed(String strArg1) throws Throwable {
		Assert.assertEquals(strArg1,landingpage.GetErrorMsg()); 
		driver.close();
 
	}


}
