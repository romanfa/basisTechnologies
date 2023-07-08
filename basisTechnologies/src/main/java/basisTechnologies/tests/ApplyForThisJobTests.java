package basisTechnologies.tests;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import basisTechnologies.pages.*;
import basisTechnologies.infrastructure.*;

//@RunWith(Parameterized.class)
public class ApplyForThisJobTests {

	private static BasisHomePage basisHomePage;
	private static ViewAllPositionsPage viewAllPositionsPage;
	private static ApplyForThatJobPage applyForThatJobPage;
	private static ApplyPage applyPage;
	private static CareersPage careersPage;
	private static WebDriverConf webDriver;
	private static Utils utils;
	private static final Logger logger = LogManager.getLogger(CareersPage.class);

	private String noJobsAppearsForSelectedFilterMessage="No jobs found on selected filter";
	
	@BeforeClass
	public static void setup() {
		basisHomePage = new BasisHomePage();
		viewAllPositionsPage = new ViewAllPositionsPage();
		careersPage = new CareersPage();
		applyPage = new ApplyPage();
		applyForThatJobPage = new ApplyForThatJobPage();
		utils = new Utils();
		webDriver = new WebDriverConf();
	}

	@Before
	public void before() {
		webDriver.openPage("https://basis.com");
	}

	@AfterClass
	public static void end() {
		webDriver.quitPage();
	}

	// Attach
	@Test
	public void attachResume() {
	// Inside Home page click on Company ComboBox and on Careers
		basisHomePage.clickOnHeader("Company").clickOnCombo("Careers");
		viewAllPositionsPage.clickOnViewAllPositionsButton();
		utils.changeFocusWindowDriver();
		careersPage.filterBy("Location").clickOnFilterMenu("United States");
		// Check if positions exists
		if (careersPage.clickOnFirstJobPosition()) {

			applyForThatJobPage.clickOnApplyForThisJobButton();
			
			applyPage.attachResume(utils.getResourceFile("resumeWithName.txt") );
		} else {
			logger.info(noJobsAppearsForSelectedFilterMessage);
		}
	}
}
