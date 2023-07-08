package basisTechnologies.tests;

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
	private static ApplyForThatJobPage applyForThatJobPage;
	private static ApplyPage applyPage;
	private static CareersPage careersPage;
	private static ViewAllPositionsPage viewAllPositionsPage;
	private static WebDriverConf webDriver;
	
	
	@BeforeClass
	public static void setup() {
		basisHomePage = new BasisHomePage();
		applyForThatJobPage = new ApplyForThatJobPage();
		applyPage = new ApplyPage();
		careersPage = new CareersPage();
		viewAllPositionsPage = new ViewAllPositionsPage();
		webDriver = new WebDriverConf();
	}

	@Before
	public void before() {
		webDriver.openPage("https://basis.com");
	}
	
	@AfterClass
	public static void end() {
		webDriver.closePage();
	}
	
	//Attach 
	@Test
	public void attachResume() {
		
		basisHomePage.clickOnHeader("Company").clickOnCombo("Careers");
		
		
		
	}
}
