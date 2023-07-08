package basisTechnologies.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import basisTechnologies.infrastructure.WebDriverConf;

public class ViewAllPositionsPage {

	WebElement element;

	public void clickOnViewAllPositionsButton() {
			element = WebDriverConf.driver.findElement(By.xpath("//a[text()='View All Positions']"));
			element.click();
	}

}
