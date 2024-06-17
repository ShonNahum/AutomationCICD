package shonautomations.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import shonautomations.MultiUse.AbstractComponent;
public class LandingPage extends AbstractComponent {
	
	WebDriver driver;

	public  LandingPage(WebDriver driver) { // constractor
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);// with this code u go and initialize all the elements and take driver argument and use this driver to intiliaze	
		//we write it in constracotr because we want it the first thing to excute
	}
	
	
	@FindBy(id="userEmail") // its remove the findelement ( it reconize to use driver. because the InitElement constractor
	WebElement userEmail; // The findElemenet put it into this varaible
	//this 2 lines above we removed WebElement userEmail = driver.findElement(By.xpath("//input[@id='userEmail']"));
	
	@FindBy(id="userPassword") 
	WebElement userPassword; 
	
	@FindBy(id="login") 
	WebElement submitLogin;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMsg;

	
	public String GetErrorMsg() {
		waitForWebElementToAppear(errorMsg);
		return errorMsg.getText();
	}
	public ProductCatalog LoginApplication(String email,String password) {
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		submitLogin.click();
		ProductCatalog productcatalog = new ProductCatalog(driver);
		return productcatalog;


	}
	public void GoTo() {
		driver.get("https://rahulshettyacademy.com/client");

	}

	
}
