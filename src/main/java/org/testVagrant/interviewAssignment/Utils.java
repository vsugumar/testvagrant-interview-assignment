package org.testVagrant.interviewAssignment;

import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public class Utils {
	public static String browserName;

	public static String getBrowser() {
		String browser = System.getProperty("BROWSER");

		
		if (browser == null) {
			browserName = "CHROME";
			return browserName;
		}else if (browser.equalsIgnoreCase("chrome")) {
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
		}
		
		return browser; 
	}
	
	public static HashMap<String, String> getComparatorProperties() throws IOException {
		Properties properties = new Properties();
		properties.load(java.lang.Object.class.getResourceAsStream("/comparator.properties"));
		HashMap<String, String> comparatorProperties = new HashMap<>();
		comparatorProperties.put("temperatureVarianceForCelsius", properties.getProperty("temperatureVarianceForCelsius"));
		comparatorProperties.put("temperatureVarianceForFahrenheit", properties.getProperty("temperatureVarianceForFahrenheit"));
		comparatorProperties.put("humidityVariance", properties.getProperty("humidityVariance"));
		
		return comparatorProperties;
	}

}
