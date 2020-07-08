package org.testVagrant.interviewAssignment.regressionTest;

import java.io.IOException;
import java.util.HashMap;
import org.testVagrant.interviewAssignment.Utils;
import org.testVagrant.interviewAssignment.API.HelperMethods;
import org.testVagrant.interviewAssignment.pageObjects.PageHandler;
import org.testVagrant.interviewAssignment.pageObjects.WeatherPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testVagrant.interviewAssignment.regressionTest.WeatherAPITest.*;

public class UIAPIComparator extends WeatherPage {
	HelperMethods helper = new HelperMethods();

	@BeforeMethod
	private void setup() {
		driver = drManager.getDriver();
		driver.get(PageHandler.appURL);
		initializeElements().andSetTimeout();
		maximize();
		goToWeatherPage();
		helper.setBaseURI();

	}
	
	@DataProvider(name="Cities")
	public Object[] cityNamesProvider() {
		return new Object[] {"Allahabad", "Ahmedabad", "Bhilwara"};
	}

	@Test(dataProvider = "Cities")
	private void compareAPIandUI(String testData) throws IOException {
		unselectAllCities(getSelectedCities());
		HashMap<String, String> weatherInfoFromUI = getWeatherInfo(testData);
		HashMap<String, String> weatherInfoFromAPI = getWeatherInfoFromAPI(testData);
		HashMap<String, String> comparatorProperties = Utils.getComparatorProperties();
	
		int tempFromUICelsius = Integer.parseInt(weatherInfoFromUI.get("temperature in Celsius"));
		int tempFromAPICelsius = Integer.parseInt(weatherInfoFromAPI.get("temperature in Celsius"));
		int temperatureVariance = Integer.parseInt(comparatorProperties.get("temperatureVarianceForCelsius"));
		
		if(Math.abs((tempFromUICelsius-tempFromAPICelsius))>temperatureVariance) {
			Assert.fail("Temperature difference in Celsius between UI and API is greater than variance "+temperatureVariance);
		}
		
		int tempFromUIFahrenheit = Integer.parseInt(weatherInfoFromUI.get("temperature in Fahrenheit"));
		int tempFromAPIFahrenheit = Integer.parseInt(weatherInfoFromAPI.get("temperature in Fahrenheit"));
		int temperatureVarianceForFahrenheit = Integer.parseInt(comparatorProperties.get("temperatureVarianceForFahrenheit"));
		
		if(Math.abs((tempFromUIFahrenheit-tempFromAPIFahrenheit))>temperatureVarianceForFahrenheit) {
			Assert.fail("Temperature difference in Fahrenheit between UI and API is greater than variance "+temperatureVarianceForFahrenheit);
		}
		
		int humidityFromUI = Integer.parseInt(weatherInfoFromUI.get("humidity"));
		int humidityFromAPI = Integer.parseInt(weatherInfoFromAPI.get("humidity"));
		int humidityVariance = Integer.parseInt(comparatorProperties.get("humidityVariance"));
		
		if(Math.abs((humidityFromUI-humidityFromAPI))>humidityVariance) {
			Assert.fail("Humidity difference between UI and API is greater than variance "+humidityVariance);
		}

	}
	
}
