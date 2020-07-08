package org.testVagrant.interviewAssignment.factoryClasses;

import java.io.File;
import java.io.IOException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.GeckoDriverService;

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
		driver = new FirefoxDriver(firefoxService);
	}
	
}
