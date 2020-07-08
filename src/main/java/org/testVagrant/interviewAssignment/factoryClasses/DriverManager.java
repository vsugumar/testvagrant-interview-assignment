package org.testVagrant.interviewAssignment.factoryClasses;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.testVagrant.interviewAssignment.Utils;

public abstract class DriverManager {
	WebDriver driver;
	public abstract void startService();
	public abstract void stopService();
	protected abstract void instantiateDriver();

	public WebDriver getDriver() {
		instantiateDriver();
		return driver;
	}

	public void killDriver() {
		driver.quit();
		driver = null;
	}

	protected String getWebDriverPath() {
		String os = System.getProperty("os.name");
		String basePath = "src"+File.separator+"web-drivers"+File.separator+"linux-drivers"+File.separator;
		if(os.equalsIgnoreCase("linux")) {
			if(Utils.browserName.equals("CHROME")) {
				return basePath+File.separator+"chromedriver";
			} else if(Utils.browserName.equals("FIREFOX")) {
				return basePath+File.separator+"geckodriver";
			}
		}
		return null;
	}
}
