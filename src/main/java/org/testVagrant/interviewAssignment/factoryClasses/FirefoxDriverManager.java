package org.testVagrant.interviewAssignment.factoryClasses;

import java.io.File;
import java.io.IOException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

public class FirefoxDriverManager extends DriverManager {
	private GeckoDriverService firefoxService;

	@Override
	public void startService() {
		if(firefoxService == null) {
			try {
			firefoxService = new GeckoDriverService.Builder().usingDriverExecutable(new File(getWebDriverPath()))
					.usingAnyFreePort().build();
				firefoxService.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void stopService() {
		firefoxService.stop();
	}

	@Override
	protected void instantiateDriver() {
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("dom.webnotifications.enabled", false);
		profile.setPreference("dom.push.enabled", false);
		DesiredCapabilities dc = DesiredCapabilities.firefox();
		dc.setCapability(FirefoxDriver.PROFILE, profile);
		FirefoxOptions options = new FirefoxOptions();
		options.merge(dc);
	
		driver = new FirefoxDriver(firefoxService, options);
		
	}
	
}
