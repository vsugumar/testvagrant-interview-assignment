package org.testVagrant.interviewAssignment.pageObjects;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testVagrant.interviewAssignment.DriverType;
import org.testVagrant.interviewAssignment.Utils;
import org.testVagrant.interviewAssignment.factoryClasses.DriverManager;
import org.testVagrant.interviewAssignment.factoryClasses.DriverManagerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;


public class PageHandler {
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

	protected void waitTillChildElementsToBeVisible(long timeOutInSeconds, String parentElementXpath, String childElementXpath) {
		setExplicitWaitTimeOutAndWait(timeOutInSeconds).until(visibilityOfNestedElementsLocatedBy(By.xpath(parentElementXpath), 
				By.xpath(childElementXpath)));
	}

	protected void waitTillVisibilityOfElement(long timeOutInSeconds, WebElement elementToBeVisible) {
		setExplicitWaitTimeOutAndWait(timeOutInSeconds).until(visibilityOf(elementToBeVisible));
	}

	protected void waitTillVisibilityOfAllElements(long timeOutInSeconds, List<WebElement> elementsToBeVisible) {
		setExplicitWaitTimeOutAndWait(timeOutInSeconds).until(visibilityOfAllElements(elementsToBeVisible));
	}

	private WebDriverWait setExplicitWaitTimeOutAndWait(long timeOutInSeconds) {
		return new WebDriverWait(driver, timeOutInSeconds);
	}

	protected boolean elementExists(String xpath) {
		try {
			driver.findElement(By.xpath(xpath));
		} catch (NoSuchElementException e) {
			return false;
		}
		return true;
	}

	protected WebElement getElementById(String id) {
		return driver.findElement(By.id(id));
	}

	protected WebElement getElementByXpath(String xpath) {
		return driver.findElement(By.xpath(xpath));
	}

	@AfterSuite
	private void stopDriverService() {
		drManager.stopService();
	}
}
