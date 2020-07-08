package org.testVagrant.interviewAssignment.regressionTest;


import org.testVagrant.interviewAssignment.pageObjects.HomePage;
import org.testVagrant.interviewAssignment.pageObjects.MasterPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class HomePageTest extends HomePage{

	
	@BeforeMethod
	private void setup() {
		driver = drManager.getDriver();
		driver.get(MasterPage.appURL);
		initializeElements().andSetTimeout();
	}
	
	@AfterMethod
	private void tearDown() {
		drManager.killDriver();
	}

}
