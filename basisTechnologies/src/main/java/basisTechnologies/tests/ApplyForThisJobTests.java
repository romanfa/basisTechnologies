package basisTechnologies.tests;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import basisTechnologies.infrastructure.*;
import basisTechnologies.pages.*;

public class ApplyForThisJobTests {

	private static BasisHomePage basisHomePage;
	private static ViewAllPositionsPage viewAllPositionsPage;
	private static ApplyForThatJobPage applyForThatJobPage;
	private static ApplyPage applyPage;
	private static CareersPage careersPage;
	private static WebDriverConf webDriver;
	private static Utils utils;
	public static final String FOLDERNAME_INSIDE_RESOURCES="resumeFiles";

	@BeforeAll
	public static void setup() {
		basisHomePage = new BasisHomePage();
		viewAllPositionsPage = new ViewAllPositionsPage();
		careersPage = new CareersPage();
		applyPage = new ApplyPage();
		applyForThatJobPage = new ApplyForThatJobPage();
		utils = new Utils();
		webDriver = new WebDriverConf();
		// go to final page before running tests
		webDriver.openPage("https://basis.com");
		basisHomePage.clickOnHeader("Company").clickOnCombo("Careers");
		viewAllPositionsPage.clickOnViewAllPositionsButton();
		utils.changeFocusWindowDriver();
		careersPage.filterBy("Location").clickOnFilterMenu("United States");
		// Check if positions exists
		careersPage.clickOnFirstJobPosition();
		applyForThatJobPage.clickOnApplyForThisJobButton();
	}

	@AfterAll
	public static void end() {
		webDriver.quitPage();
	}

	@Test
	public void attachResumeTest() {
		boolean isSuccess;
		List<String> resumeListFilesFromResources = utils.getFileListFromResourceDirectory("resumeFiles");
		// read all files from directory inside resource folder
		for (String nameOfFile : resumeListFilesFromResources) {
			applyPage.attachResume(utils.getResourceFile(nameOfFile,FOLDERNAME_INSIDE_RESOURCES));
			isSuccess = nameOfFile.contains("Success")?true:false;
			if (isSuccess) {
				assertTrue(applyPage.isUploadSucceded(), "Resume " + nameOfFile + " not uploded as expected");
			} else {
				assertFalse(applyPage.isUploadSucceded(), "Resume " + nameOfFile + " not uploded as expected");
			}
		}
	}

	@Test
	public void pronounsCheckUncheckTest() {
		// Check and Uncheck tests for every fields
		List<String> checkboxValues = applyPage.getCheckBoxPronounsValues();
		for (String pronouns : checkboxValues) {
			assertTrue(applyPage.clickOnCheckboxPronouns(pronouns), "Checkbox not checked after clicking on it for: " + pronouns + "");
			assertFalse(applyPage.clickOnCheckboxPronouns(pronouns), "Checkbox not unchecked after clicking on it for: " + pronouns + "");
		}
	}

	// Click on multiple checkboxes excluding "Use name only" and "Custom" and
	// ensure all checkboxes is checked
	@Test
	public void pronounsMultipleCheckTest() {
		// Ensure before test all checkboxes unchecked
		applyPage.ensureAllCheckBoxesUnchecked();
		List<String> checkboxValues = applyPage.getCheckBoxPronounsValues();
		// click on all checkbox
		for (String pronouns : checkboxValues) {
			if (!pronouns.equals("Use name only") && !pronouns.equals("Custom"))
				applyPage.clickOnCheckboxPronouns(pronouns);
		}
		// ensure they checked
		for (String pronouns : checkboxValues) {
			if (!pronouns.equals("Use name only") && !pronouns.equals("Custom")) {
				assertTrue(applyPage.isPronounsChecked(pronouns), "CheckBox " + pronouns + " not checked");
			}
		}
		applyPage.ensureAllCheckBoxesUnchecked();
	}

	// click on all checkboxes and after that click on UseNameOnly and ensure all check boxes unchecked
	@Test
	public void pronounsUseNameOnlyCheckBoxTest() {
		// Ensure before test all checkboxes unchecked
		applyPage.ensureAllCheckBoxesUnchecked();
		// Check all checkboxes without UseNameOnly and Custom
		List<String> checkboxValues = applyPage.getCheckBoxPronounsValues();
		for (String pronouns : checkboxValues) {
			if (!pronouns.equals("Use name only") && !pronouns.equals("Custom"))
				applyPage.clickOnCheckboxPronouns(pronouns);
		}
		// Click on UseNameOnly
		applyPage.clickOnCheckboxPronouns("Use name only");
		// ensure all checkboxes unchecked:
		for (String pronouns : checkboxValues) {
			if (!pronouns.equals("Use name only") && !pronouns.equals("Custom")) {
				assertFalse(applyPage.isPronounsChecked(pronouns), "CheckBox " + pronouns + " not unchecked");
			}
		}
		applyPage.ensureAllCheckBoxesUnchecked();
	}

	@Test
	public void pronounsCustomCheckBoxTest() {
		// Ensure before test all checkboxes unchecked
		applyPage.ensureAllCheckBoxesUnchecked();
		// Check all checkboxes without UseNameOnly and Custom
		List<String> checkboxValues = applyPage.getCheckBoxPronounsValues();
		for (String pronouns : checkboxValues) {
			if (!pronouns.equals("Use name only") && !pronouns.equals("Custom"))
				applyPage.clickOnCheckboxPronouns(pronouns);
		}
		// Click on UseNameOnly
		applyPage.clickOnCheckboxPronouns("Custom");
		// ensure all checkboxes unchecked:
		for (String pronouns : checkboxValues) {
			if (!pronouns.equals("Use name only") && !pronouns.equals("Custom")) {
				assertFalse(applyPage.isPronounsChecked(pronouns), "Not correct behaviour after click on Custom checkbox");
			}
		}

		assertTrue(applyPage.isTextBoxVisibleAfterClickOnCustom(), "Textbox not appeared after click on Custom checkbox");
		// check if TextBox appears
		applyPage.ensureAllCheckBoxesUnchecked();
	}
}
