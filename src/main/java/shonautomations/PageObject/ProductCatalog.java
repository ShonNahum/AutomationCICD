package shonautomations.PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import shonautomations.MultiUse.AbstractComponent;

public class ProductCatalog extends AbstractComponent  {
	
	WebDriver driver;

	public ProductCatalog(WebDriver driver) { // constractor
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);// with this code u go and initialize all the elements and take driver argument and use this driver to intiliaze	
		//we write it in constracotr because we want it the first thing to excute
	}
	
	@FindBy(css=".ng-animating")
	WebElement Spinner;
	@FindBy(css=".mb-3") // its remove the findelement ( it reconize to use driver. because the InitElement constractor
	List <WebElement> products;  // (by saying list it make it FindElements
	By productsBy = By.cssSelector(".mb-3");
	By AddToCart = By.xpath("//button[.=' Add To Cart']");
	By toastMsg = By.xpath("//div[@id='toast-container']");
	public List <WebElement> GetProductList() {
		waitForElementToAppear(productsBy);	
		return products;
	}
	 public WebElement GetProductByName(String productName) {
		 WebElement prod = GetProductList().stream().filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		 return prod;
	 }
	
	 public void AddToCart(String ProductName) {
		 WebElement prod = GetProductByName(ProductName);
		 prod.findElement(AddToCart).click();
		 waitForElementToAppear(toastMsg);
		 waitForElementToDisappear(Spinner);
	 }
}
