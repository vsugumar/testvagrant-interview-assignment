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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class WeatherPageTest extends WeatherPage{

	
	@BeforeMethod
	private void setup() {
		driver = drManager.getDriver();
		driver.get(PageHandler.appURL);
		initializeElements().andSetTimeout();
		goToWeatherPage();
	}
	
	@DataProvider(name="Cities")
	public Object[] cityNamesProvider() {
		return new Object[] {"Allahabad", "Ahmedabad", "Bhilwara"};
	}
	
	@Test 
	private void validateDefaultCitySelection() {
		ArrayList<String> citiesSelectedByDefault = getSelectedCities();
		ArrayList<String> citiesAppearingInMap = getCitiesAppearingInMap();
		assertThat(citiesSelectedByDefault, containsInAnyOrder(citiesAppearingInMap.toArray()));
	}
	
	@Test(dataProvider = "Cities")
	private void validateCitySelection(String city) {
		selectCity(city);
		ArrayList<String> selectedCities = getSelectedCities();
		ArrayList<String> citiesAppearingInMap = getCitiesAppearingInMap();
		assertThat(selectedCities, containsInAnyOrder(citiesAppearingInMap.toArray()));
	}
	
	@Test(dataProvider = "Cities")
	private void validateIfTempIsDisplayedForSelectedCities(String city) {
		selectCity(city);
		isTempDisplayedForAllSelectedCities(getSelectedCities());
	}
	
	@Test(dataProvider = "Cities")
	public void validateWeatherDetails(String city) {
		String[] expectedTemperatureDetails = {"Condition", "Wind", "Humidity", "Temp in Degrees", "Temp in Fahrenheit"};
		unselectAllCities(getSelectedCities());
		selectCity(city);	
		HashMap<String, String> cityTempDetails = getTemperatureDetailsForSelectedCities(getSelectedCities());
		Iterator<Map.Entry<String, String>> iterator = cityTempDetails.entrySet().iterator();
		
		while(iterator.hasNext()) {
			Map.Entry<String, String> entryObj = iterator.next();
			String City = entryObj.getKey();
			String actualTempDetails =  entryObj.getValue();
			assertThat(actualTempDetails, containsString(City));
			Arrays.asList(expectedTemperatureDetails).forEach(expectedTempDetail -> assertThat(actualTempDetails, containsString(expectedTempDetail)));
		}	
	}
	
	
	@AfterMethod
	private void tearDown() {
		drManager.killDriver();
	}

}
