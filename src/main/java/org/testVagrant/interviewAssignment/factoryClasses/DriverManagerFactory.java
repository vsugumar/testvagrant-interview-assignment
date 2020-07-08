package org.testVagrant.interviewAssignment.factoryClasses;

import org.testVagrant.interviewAssignment.DriverType;

public class DriverManagerFactory {
	
	public static DriverManager driverManager;
	
	public static DriverManager getDriver(DriverType driverType) {
		
		switch(driverType) {
		case CHROME:
			driverManager = new ChromeDriverManager();
		case FIREFOX:
			driverManager = new FirefoxDriverManager();
		default:
			break;
		}
		return driverManager;
	}
}
