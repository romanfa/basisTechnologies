package basisTechnologies.pages;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import basisTechnologies.infrastructure.WebDriverConf;

public class ApplyPage {

	WebElement element;
	WebDriverWait wait;

	By attachButton= By.xpath("//input[@id='resume-upload-input']");
	By sucessElement= By.xpath("//div[@class='application-field']//span[@class='resume-upload-success' and @style='display: inline;']");
	By allCheckBoxesInsidePronounce= By.xpath("//ul[@id='candidatePronounsCheckboxes']//input[@type='checkbox']");
	By textBoxPronounsCommon= By.xpath("//ul[@id='candidatePronounsCheckboxes']//input[@type='checkbox']");
	String checkboxPronouns= "//input[@type='checkbox' and @value='%s']";
	By textOnAttachButton= By.xpath("//li[@class='application-question resume']//span[@class='default-label']");
	
	public boolean checkIfButtonEnabled() {
		element = WebDriverConf.driver.findElement(attachButton);
		return element.isEnabled();
	} 
	
	public boolean compareTextOnButton(String expectedText) {
		element = WebDriverConf.driver.findElement(textOnAttachButton);
		return  expectedText.equals(element.getText());
	} 
	
	public void attachResume(File fileName) {
		element = WebDriverConf.driver.findElement(attachButton);
		element.sendKeys(fileName.getPath());
	}

	public boolean isUploadSucceded() {
		try {
			wait = new WebDriverWait(WebDriverConf.driver, Duration.ofMillis(12000));
			wait.until(ExpectedConditions.presenceOfElementLocated(sucessElement));
			// Element Present in DOM
			return true;
		} catch (TimeoutException e) {
			// Element not Present in DOM
			return false;
		}
	}

	public boolean clickOnCheckboxPronouns(String pronouns) {
		element = WebDriverConf.driver.findElement(By.xpath(String.format(checkboxPronouns,pronouns)));
		element.click();
		return element.isSelected();
	}

	public boolean isPronounsChecked(String pronouns) {
		element = WebDriverConf.driver.findElement(By.xpath(String.format(checkboxPronouns,pronouns)));
		return element.isSelected();
	}
	
	public List<String> getCheckBoxPronounsValues() {

		List<WebElement> checkboxes = WebDriverConf.driver.findElements(allCheckBoxesInsidePronounce);
		List<String> checkboxValues = new ArrayList<>();
		String tempValue;
		for (WebElement checkbox : checkboxes) {
			tempValue = checkbox.getAttribute("value");
			if (!tempValue.isBlank()) checkboxValues.add(tempValue);
		}
		return checkboxValues;
	}

	public void ensureAllCheckBoxesUnchecked() {
		List<WebElement> checkboxes = WebDriverConf.driver.findElements(allCheckBoxesInsidePronounce);
		for (WebElement checkbox : checkboxes) {
			if (checkbox.isSelected())
				checkbox.click();
		}
	}
	
	public boolean isTextBoxVisibleAfterClickOnCustom() {
        // Assuming that the input field appears when the checkbox is clicked
        WebElement textField = WebDriverConf.driver.findElement(textBoxPronounsCommon);
        String style = textField.getAttribute("style");

        if (!style.contains("display: none;")) {
            return true;
        } else {
        	return false;
        }
	}
}
