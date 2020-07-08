package org.testVagrant.interviewAssignment;


public class Utils {
	public static String browserName;

	public static String getBrowser() {
		String browser = System.getProperty("BROWSER");

		if (browser.equalsIgnoreCase("chrome")) {
			browserName = "CHROME";
			return browserName;
		} else if (browser.equalsIgnoreCase("firefox")) {
			browserName = "FIREFOX";
			return browserName;
		} else if (browser.equalsIgnoreCase("safari")) {
			browserName = "SAFARI";
			return browserName;
		} else if (browser.equalsIgnoreCase("ie")) {
			browserName = "IE";
			return browserName;
		} else {
			browserName = "CHROME";
			return browserName;	
		}
	}

}
