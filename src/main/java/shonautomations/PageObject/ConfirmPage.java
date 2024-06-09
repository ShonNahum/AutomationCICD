package shonautomations.PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import shonautomations.MultiUse.AbstractComponent;

public class ConfirmPage extends AbstractComponent  {
	
	WebDriver driver;

	public ConfirmPage(WebDriver driver) { // constractor
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);// with this code u go and initialize all the elements and take driver argument and use this driver to intiliaze	
		//we write it in constracotr because we want it the first thing to excute
	}
	
	@FindBy(css=".hero-primary")
	WebElement confirmMessage;

	public String GetConfirmMessage() {
		return confirmMessage.getText();
	}
	
	
	
}
	