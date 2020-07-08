 package org.testVagrant.interviewAssignment.pageObjects;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.testVagrant.interviewAssignment.DriverType;
import org.testVagrant.interviewAssignment.Utils;
import org.testVagrant.interviewAssignment.factoryClasses.DriverManager;
import org.testVagrant.interviewAssignment.factoryClasses.DriverManagerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;


public class MasterPage {
	public static final String appURL = "https://www.ndtv.com/";
	public static WebDriver driver;
	public static DriverManager drManager;
	private int elementTimeOut = 10;
	
	@BeforeSuite
	private void startWebdriver() {
		drManager = DriverManagerFactory.getDriver(DriverType.valueOf(Utils.getBrowser()));
		drManager.startService();
	}
	
	public void andSetTimeout() {
		driver.manage().timeouts().implicitlyWait(elementTimeOut, TimeUnit.SECONDS);
	}
	
	@AfterSuite
	private void stopDriverService() {
		drManager.stopService();
	}
}
