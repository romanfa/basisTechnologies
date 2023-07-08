package basisTechnologies.pages;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import basisTechnologies.infrastructure.WebDriverConf;

public class ApplyPage {

	WebElement element;

	public void attachResume(File fileName) {
		// element =
		// WebDriverConf.driver.findElement(By.xpath("//div[@class='filter-bar']//div[@class='filter-button'
		// and text()='Location']"));
		element = WebDriverConf.driver.findElement(By.xpath("//input[@id='resume-upload-input']"));
		element.sendKeys(fileName.getPath());
	}

}
