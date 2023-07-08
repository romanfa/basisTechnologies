package basisTechnologies.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import basisTechnologies.infrastructure.WebDriverConf;

public class ApplyForThatJobPage {
	WebElement element;

	public void clickOnApplyForThisJobButton() {


		element = WebDriverConf.driver
				.findElement(By.xpath("//a[contains(text(),'Apply for this job')]"));
		element.click();
	}
}
