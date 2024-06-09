package shonautomations.tests;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.github.bonigarcia.wdm.WebDriverManager;
import shonautomations.PageObject.CartPage;
import shonautomations.PageObject.CheckOutPage;
import shonautomations.PageObject.ConfirmPage;
import shonautomations.PageObject.LandingPage;
import shonautomations.PageObject.OrderPage;
import shonautomations.PageObject.ProductCatalog;
import shonautomations.TestComponents.BaseTest;

public class SubmitOrderTest extends BaseTest { 



	
	@Test (dataProvider="GetData",groups={"Purchase"})// getting the GetData dataProvider
	
	public void SubmitOrder(HashMap<String,String> input) throws IOException, InterruptedException
	{
		//the login and submit
		ProductCatalog productcatalog =  landingpage.LoginApplication(input.get("email"),input.get("password")); // i dont need Open productcatalog object cuz i opened it in the LoginApplication Fucntion inside LandingPage Class becuase the login always take me into productcatalog page
		List<WebElement> products = productcatalog.GetProductList();
		productcatalog.AddToCart(input.get("ProductName"));
		CartPage cartpage =	productcatalog.GotToCartPage(); // because i got toCartPage and i make in this class cartpage stuff, so inside GoToCartPage i create cartpage package varaible
		Boolean match =cartpage.VerifyProductDisplay(input.get("ProductName"));
		Assert.assertTrue(match);
		CheckOutPage checkoutpage =cartpage.GoToCheckOut();
		
		Thread.sleep(3000);
		checkoutpage.SelectCountry("india");
		ConfirmPage confirmpage =checkoutpage.SubmitOrder();
		System.out.println(confirmpage.GetConfirmMessage());
		Assert.assertTrue(confirmpage.GetConfirmMessage().equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		}

//	@Test(dependsOnMethods={"SubmitOrder"}) //to verify ZARA COAT 3 is displaying in orders page
//	//dependsonMethod = mean it will excute only after SubmitOrder function will excute (make sure previous test run on beginning
//	public void OrderHistoryTest() throws InterruptedException {
//		ProductCatalog productcatalog =  landingpage.LoginApplication("shon@gmail.com","Shonking123"); // i dont need Open productcatalog object cuz i opened it in the LoginApplication Fucntion inside LandingPage Class becuase the login always take me into productcatalog page
//		OrderPage orderspage = productcatalog.GotToOrderPage();
//	//	Assert.assertTrue(orderspage.VerifyOrderDisplay(ProductName));
//	
//	}
	
	@DataProvider // saying, hi this guy will give data waht needed for all the test in this particilar class
	public Object[][] GetData() throws IOException { //for e	xample we want to run SubmitOrder() function test with 2 different data sets
		
		List<HashMap<String,String>> data = GetJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\shonautomations\\data\\PurchaseOrder.json");

		return new Object[][]  { {data.get(0)},{data.get(1) } };
		
	}
	
	
	
	
}
