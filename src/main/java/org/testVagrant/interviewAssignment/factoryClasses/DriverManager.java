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
		String os = System.getProperty("os.name").toLowerCase();
		String basePath = "src"+File.separator+"web-drivers"+File.separator;
		if(os.equalsIgnoreCase("linux")) {
			if(Utils.browserName.equals("CHROME")) {
				return basePath+"linux-drivers"+File.separator+"chromedriver";
			} else if(Utils.browserName.equals("FIREFOX")) {
				return basePath+"linux-drivers"+File.separator+"geckodriver";
			}
		} else if(os.equalsIgnoreCase("windows")) {
			if(Utils.browserName.equals("CHROME")) {
				return basePath+"windows-drivers"+File.separator+"chromedriver.exe";
			} else if(Utils.browserName.equals("FIREFOX")) {
				return basePath+"windows-drivers"+File.separator+"geckodriver.exe";
			}
		} else if(os.contains("mac")) {
			if(Utils.browserName.equals("CHROME")) {
				return basePath+"mac-drivers"+File.separator+"chromedriver";
			} else if(Utils.browserName.equals("FIREFOX")) {
				return basePath+"mac-drivers"+File.separator+"geckodriver";
			}
		}
		return null;
	}
}
