package shonautomations.TestComponents;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterMethod;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import shonautomations.PageObject.LandingPage;

public abstract class BaseTest {
	public WebDriver driver; // making driver variable public so all the functions will use it, because in the end of the functions we want all use driver.manage().window().maximize();
	public LandingPage landingpage;

	public WebDriver initializeDriver() throws IOException {
		
		//properties class that read the properties and decide in runtime which browers the framework has to excute test cases
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\shonautomations\\resources\\GlobalData.properties"); // (System.getProperty("user.dir") will get automatic the project path dynamicly
		prop.load(fis); // we need send the file as input stream, to make it input stream we do this ^
	
		String browserName = System.getProperty("browser") != null ? System.getProperty("browser") :prop.getProperty("browser"); // Java ternay operator = provide condition if its true second argument will active if it false thied argument will active
		// = prop.getProperty("browser"); // now we get the thing we write in browser= in GlobalData.properties file
		
		if(browserName.contains("chrome")) {
			ChromeOptions optionC = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			
			
			driver = new ChromeDriver();

	
		}
		else if (browserName.equalsIgnoreCase("FireFox"))
		{
			//FireFox
		}
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
	    driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}
	
	public List<HashMap<String,String>> GetJsonDataToMap(String filePath) throws IOException{
		//Read Json to String
		
		String jsonContent=	FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
		
		//String to Hashmap JackSon datbind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>() {});
		return data;
		// this data is list with 2 argument with hashmaps	like {map,map}
	}
	
	
	
	
	
	@BeforeMethod (alwaysRun = true)
	public LandingPage launchApp() throws IOException {
		 driver = initializeDriver();
		landingpage = new LandingPage(driver);
		landingpage.GoTo();
		return landingpage;
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}
	
}
