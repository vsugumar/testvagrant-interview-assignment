package org.testVagrant.interviewAssignment.factoryClasses;

import java.io.File;
import java.io.IOException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeDriverManager extends DriverManager {
	private ChromeDriverService chromeDriverService;

	@Override
	public void startService() {
		if(chromeDriverService==null) {
			try {
				chromeDriverService = new ChromeDriverService.Builder().usingDriverExecutable(new File(getWebDriverPath()))
						.usingAnyFreePort().build();
				chromeDriverService.start();
			} catch (IOException e) {
				e.printStackTrace();
			}		
		}
	}

	@Override
	public void stopService() {
		chromeDriverService.stop();
	}

	@Override
	public void instantiateDriver() {
		if(driver==null) {
			driver = new ChromeDriver(chromeDriverService, getChromeOptions());
		}
	}

	private ChromeOptions getChromeOptions() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("disable-infobars");
		options.addArguments("start-maximized");
		return options;
	}

}
