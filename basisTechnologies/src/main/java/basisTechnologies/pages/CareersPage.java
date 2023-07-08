package basisTechnologies.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import basisTechnologies.infrastructure.WebDriverConf;

public class CareersPage {

	WebElement element;
	WebDriverWait wait;

	public CareersPage filterBy(String filterName) {

		// element =
		// WebDriverConf.driver.findElement(By.xpath("//div[@class='filter-bar']//div[@class='filter-button'
		// and text()='Location']"));
		element = WebDriverConf.driver
				.findElement(By.xpath("//div[normalize-space()='" + filterName + "']//*[name()='svg']"));
		element.click();

		return this;
	}

	public CareersPage clickOnFilterMenu(String filterNameSub) {
		element = WebDriverConf.driver.findElement(By.xpath("//a[text()='" + filterNameSub + "']"));
		wait = new WebDriverWait(WebDriverConf.driver, Duration.ofMillis(5000));
		wait.until(ExpectedConditions.visibilityOf(element));
		element.click();
		return this;
	}

	public boolean checkIfPositionsListExists() {

		List<WebElement> elements = WebDriverConf.driver
				.findElements(By.xpath("//div[@class='posting-apply']//a[text()='Apply']"));
		return !elements.isEmpty();

	}

	public boolean clickOnFirstJobPosition() {

		List<WebElement> elements = WebDriverConf.driver
				.findElements(By.xpath("//div[@class='posting-apply']//a[text()='Apply']"));
		if (!elements.isEmpty()) {

			elements.get(0).click();
			return true;
		} else {

			return false;
		}

	}
}
