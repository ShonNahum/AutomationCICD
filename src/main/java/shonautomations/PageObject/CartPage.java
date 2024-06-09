package shonautomations.PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import shonautomations.MultiUse.AbstractComponent;

public class CartPage extends AbstractComponent  {
	
	WebDriver driver;

	public CartPage(WebDriver driver) { // constractor
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);// with this code u go and initialize all the elements and take driver argument and use this driver to intiliaze	
		//we write it in constracotr because we want it the first thing to excute
	}
	
	
	@FindBy(xpath="//div/ul/li/button[@type='button']")
	WebElement CheckOutButton;
	@FindBy(css=".cartSection h3")
	List<WebElement> ProductsTitles;
	
	
	public Boolean VerifyProductDisplay(String productName) {
		Boolean match = ProductsTitles.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckOutPage GoToCheckOut() throws InterruptedException {
		Thread.sleep(2000);
		CheckOutButton.click();
		CheckOutPage checkoutpage = new CheckOutPage(driver);
		
		return checkoutpage;
		
	}
	
}
