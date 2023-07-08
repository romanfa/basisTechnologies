package basisTechnologies.infrastructure;

import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverConf {

	public static  WebDriver driver;

	public  WebDriverConf() {
		
		WebDriverManager.chromedriver().setup();


		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("test-type");
		chromeOptions.addArguments("--disable-extensions");
		chromeOptions.addArguments("disable-infobars");
		chromeOptions.addArguments("--disable-notifications");

		chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
		chromeOptions.addArguments("--start-maximized");

		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);
		chromeOptions.setExperimentalOption("prefs", prefs);

		driver= new ChromeDriver(chromeOptions);
		
	}


	public void openPage(String url) {
		driver.get(url);
	}

	public void closePage() {
		driver.close();
	}
	public void quitPage() {
		driver.quit();
	}

}