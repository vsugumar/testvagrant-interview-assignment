package org.testVagrant.interviewAssignment.regressionTest;


import org.testVagrant.interviewAssignment.pageObjects.WeatherPage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import org.testVagrant.interviewAssignment.pageObjects.PageHandler;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WeatherPageTest extends WeatherPage{

	
	@BeforeMethod
	private void setup() {
		driver = drManager.getDriver();
		driver.get(PageHandler.appURL);
		initializeElements().andSetTimeout();
		goToWeatherPage();
	}
	
	@Test (enabled = false)
	private void validateDefaultCitySelection() {
		ArrayList<String> citiesSelectedByDefault = getSelectedCities();
		ArrayList<String> citiesAppearingInMap = getCitiesAppearingInMap();
		assertThat(citiesSelectedByDefault, containsInAnyOrder(citiesAppearingInMap.toArray()));
	}
	
	@Test (enabled = false)
	private void validateCitySelection() {
		String[] citiesToBeSelected = {"Allahabad", "Ahmedabad", "Bhilwara", "Chandigarh"};
		selectCities(citiesToBeSelected);
		ArrayList<String> selectedCities = getSelectedCities();
		ArrayList<String> citiesAppearingInMap = getCitiesAppearingInMap();
		assertThat(selectedCities, containsInAnyOrder(citiesAppearingInMap.toArray()));
	}
	
	@Test (enabled = false)
	private void validateIfTempIsDisplayedForSelectedCities() {
		String[] citiesToBeSelected = {"Allahabad", "Ahmedabad", "Bhilwara", "Chandigarh"};
		selectCities(citiesToBeSelected);
		isTempDisplayedForAllSelectedCities(getSelectedCities());
	}
	
	@Test
	private void validateWeatherDetails() {
		String[] citiesToBeSelected = {"Ahmedabad", "Bhilwara", "Chandigarh"};
		String[] expectedTemperatureDetails = {"Condition", "Wind", "Humidity", "Temp in Degrees", "Temp in Fahrenheit"};
		unselectAllCities(getSelectedCities());
		selectCities(citiesToBeSelected);	
		HashMap<String, String> cityTempDetails = getTemperatureDetailsForSelectedCities(getSelectedCities());
		Iterator<Map.Entry<String, String>> iterator = cityTempDetails.entrySet().iterator();
		
		while(iterator.hasNext()) {
			Map.Entry<String, String> entryObj = iterator.next();
			String city = entryObj.getKey();
			String actualTempDetails =  entryObj.getValue();
			assertThat(actualTempDetails, containsString(city));
			Arrays.asList(expectedTemperatureDetails).forEach(expectedTempDetail -> assertThat(actualTempDetails, containsString(expectedTempDetail)));

		}
		
	}
	
	
	@AfterMethod
	private void tearDown() {
		drManager.killDriver();
	}

}
