package shonautomations.tests;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;


import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.github.bonigarcia.wdm.WebDriverManager;
import shonautomations.PageObject.CartPage;
import shonautomations.PageObject.CheckOutPage;
import shonautomations.PageObject.ConfirmPage;
import shonautomations.PageObject.LandingPage;
import shonautomations.PageObject.ProductCatalog;
import shonautomations.TestComponents.BaseTest;
import shonautomations.resources.Retry;

public class ErrorValidations extends BaseTest { 

	

	
    @Test(groups = {"ErrorHandling"}, retryAnalyzer=Retry.class)//make the code go into retry class (picked for retry)
	public void LoginErrorValidation() throws IOException, InterruptedException
	{
		//the login and submit
		ProductCatalog productcatalog =  landingpage.LoginApplication("shon@gmail.com","Shonking125332r23"); 
		Assert.assertEquals("Incorrect email or password.",landingpage.GetErrorMsg()); // now we can see that the error is the login
	} 
	
	@Test
	public void ProductErrorValidation()  throws IOException, InterruptedException
	{
		String stuff = "ZARA COAT 3";
		ProductCatalog productcatalog =  landingpage.LoginApplication("test@exmaple.com","Shonking123"); // i dont need Open productcatalog object cuz i opened it in the LoginApplication Fucntion inside LandingPage Class becuase the login always take me into productcatalog page
		List<WebElement> products = productcatalog.GetProductList();
		productcatalog.AddToCart(stuff);
		CartPage cartpage =	productcatalog.GotToCartPage(); // because i got toCartPage and i make in this class cartpage stuff, so inside GoToCartPage i create cartpage package varaible
		Boolean match =cartpage.VerifyProductDisplay(stuff);
		Assert.assertTrue(match); // its = false so its error on product Name
	}
	
}
