package shonautomations.tests;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.github.bonigarcia.wdm.WebDriverManager;
import shonautomations.PageObject.LandingPage;

public class StandAlone { // this is END TO END TEST

	public static void main(String[] args) throws InterruptedException {
		String stuff = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();// took from The webdrivermanager dependency , now chomredriver automatic downloaded to the system  base on my chrome brower version
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));// Global level of wait (to every driver Element) 10seconds.
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		
		LandingPage landingpage = new LandingPage(driver);

		driver.findElement(By.xpath("//input[@id='userEmail']")).sendKeys("shon@gmail.com");
		driver.findElement(By.xpath("//input[@id='userPassword']")).sendKeys("Shonking123");
		driver.findElement(By.xpath("//input[@id='login']")).click();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3"))); // wait untill it will appear because the product has to load
		List <WebElement> products =driver.findElements(By.cssSelector(".mb-3"));
		
		WebElement prod =products.stream().filter(product -> 
		product.findElement(By.cssSelector("b")).getText().equals(stuff)).findFirst().orElse(null);
		//findfirst() no matter how much result u get, give the first one that equals to ZARA COAT 3, and give it the the prod variable,or return nothing (null)
		//prod is store the product with the text is having is ZARA COAT 3
		// all the stream should be same (all xpah or all cssSelector)
		prod.findElement(By.xpath("//button[.=' Add To Cart']")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='toast-container']"))); // all the code wait untill this element will pop untill the code dont go forward (untill 5sec)
		//its insted of using Thread.sleep (we need to always never use it)		
		
		Thread.sleep(5000); // USED IT once only because the loading thing i cant find it in the game
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(stuff));
	//by writing anyMatch , boolean return true only if all the elemenets is positive with this filter
		Assert.assertTrue(match);
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")), "India").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		String confirmMsg = driver.findElement(By.cssSelector(".hero-primary")).getText();
		confirmMsg.equalsIgnoreCase(confirmMsg);
		Assert.assertTrue(confirmMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		System.out.println(confirmMsg);

		driver.close();
		
	}
}
