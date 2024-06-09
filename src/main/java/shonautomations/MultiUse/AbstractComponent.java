	package shonautomations.MultiUse;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import shonautomations.PageObject.CartPage;
import shonautomations.PageObject.OrderPage;

public class AbstractComponent { // putting here Common stuff that we can use
	WebDriver driver;

	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
	}

	
	@FindBy(css="[routerlink*='cart']")
	WebElement cartHeader;
	
	@FindBy(css="[routerlink*='myorders']")	
	WebElement orderHeader;
	
	public void waitForElementToAppear(By FindBy ) { // by make By variable = Make only the by.* stuff without the driver.findelement
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOfElementLocated(FindBy)); // now it generic
	}
	
	
		public void waitForWebElementToAppear(WebElement FindBy ) { // by make By variable = Make only the by.* stuff without the driver.findelement
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(FindBy)); // now it generic
		}
	
	
	public CartPage GotToCartPage() throws InterruptedException {
		cartHeader.click();
		CartPage cartpage = new CartPage(driver);
		return cartpage;

	}
	

	public OrderPage GotToOrderPage() throws InterruptedException {
		orderHeader.click();
		OrderPage orderpage = new OrderPage(driver);
		return orderpage;

	}
	

	public void waitForElementToDisappear(WebElement element ) { 
		//4 Seconds _ Application to handle the load (because we waiting to the InvisibilityOf(element) loading to disappear because there is another loaders happeing in backed
		// if u want to remove it and time is importantm u can remove the wait and Just put Thread.sleep(1000);
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
	wait.until(ExpectedConditions.invisibilityOf(element)); // now it generic
	}
}
