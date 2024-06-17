package shonautomations.PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import shonautomations.MultiUse.AbstractComponent;

public class CheckOutPage extends AbstractComponent  {
	
	WebDriver driver;

	public CheckOutPage(WebDriver driver) { // constractor
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);// with this code u go and initialize all the elements and take driver argument and use this driver to intiliaze	
		//we write it in constracotr because we want it the first thing to excute
	}
	
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement country;
	@FindBy(css=".action__submit")
	WebElement submit;
	@FindBy(css=".ta-item:nth-of-type(2)")
	WebElement countrySelector;
	
	
	public void SelectCountry(String countryName) {
		Actions a = new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		countrySelector.click();
		
	}
	
	public ConfirmPage SubmitOrder() {
		submit.click();
		ConfirmPage confirmpage = new ConfirmPage(driver);
		return confirmpage;


	}

}
