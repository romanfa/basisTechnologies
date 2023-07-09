package basisTechnologies.pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import basisTechnologies.infrastructure.WebDriverConf;

public class BasisHomePage {
	WebElement element;
	Actions action;
	WebDriverWait wait;
	String headerNames = "//div[@class='oxy-header-container']//div[text()='%s']";
	String subMenu = "//div[text()='%s']";

	public BasisHomePage clickOnHeader(String name) {
		element = WebDriverConf.driver.findElement(By.xpath(String.format(headerNames, name)));
		action = new Actions(WebDriverConf.driver);
		action.moveToElement(element).perform();
		return this;
	}

	public void clickOnCombo(String name) {
		element = WebDriverConf.driver.findElement(By.xpath(String.format(subMenu, name)));
		wait = new WebDriverWait(WebDriverConf.driver, Duration.ofMillis(5000));
		wait.until(ExpectedConditions.visibilityOf(element));
		element.click();
	}
}
