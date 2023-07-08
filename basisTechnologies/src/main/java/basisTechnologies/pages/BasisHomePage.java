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
	
	public BasisHomePage clickOnHeader(String name) {
		element = WebDriverConf.driver.findElement(By.xpath("//div[@class='oxy-header-container']//div[text()='"+name+"']"));
		//element.click();
		Actions action = new Actions(WebDriverConf.driver);
		action.moveToElement(element).perform();
		return this;
	}
	
	public void clickOnCombo(String name) {
		element = WebDriverConf.driver.findElement(By.xpath("//div[text()='"+name+"']"));
		WebDriverWait wait = new WebDriverWait(WebDriverConf.driver,Duration.ofMillis(5000));
		wait.until(ExpectedConditions.visibilityOf(element));
		element.click();
		
	}
}
