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
	By applyButtons=By.xpath("//div[@class='posting-apply']//a[text()='Apply']");
	String filterHeadTitles="//div[normalize-space()='%s']//*[name()='svg']";
	String filterMenu="//a[text()='%s']";
	
	public CareersPage filterBy(String filterName) {
		element = WebDriverConf.driver.findElement(By.xpath(String.format(filterHeadTitles, filterName)));
		element.click();
		return this;
	}

	public CareersPage clickOnFilterMenu(String filterNameSub) {
		element = WebDriverConf.driver.findElement(By.xpath(String.format(filterMenu, filterNameSub)));
		wait = new WebDriverWait(WebDriverConf.driver, Duration.ofMillis(5000));
		wait.until(ExpectedConditions.visibilityOf(element));
		element.click();
		return this;
	}

	public boolean checkIfPositionsListExists() {
		List<WebElement> elements = WebDriverConf.driver.findElements(applyButtons);
		return !elements.isEmpty();
	}

	public boolean clickOnFirstJobPosition() {

		List<WebElement> elements = WebDriverConf.driver.findElements(applyButtons);
		if (!elements.isEmpty()) {
			elements.get(0).click();
			return true;
		} else {
			return false;
		}
	}
}
