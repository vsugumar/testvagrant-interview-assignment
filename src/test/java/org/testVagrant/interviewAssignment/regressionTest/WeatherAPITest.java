package org.testVagrant.interviewAssignment.regressionTest;

import java.io.IOException;
import java.util.HashMap;
import org.testVagrant.interviewAssignment.API.HelperMethods;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import io.restassured.path.json.JsonPath;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class WeatherAPITest {
	HelperMethods helper = new HelperMethods();
	
	@BeforeMethod
	private void setUp() {
		helper.setBaseURI();
	}
	
	@Test
	private void validateJSONSchema() throws IOException {
		HashMap<String, String> queryParams = new HashMap<>();
		queryParams.put("q", "chennai");
		queryParams.put("units", "metric");
		
		helper.getResponse("data/2.5/weather", queryParams).then().assertThat().body(matchesJsonSchemaInClasspath("weather-details-schema.json"));
	} 
	
	@Test
	private void validateCity() throws IOException {
		String expectedCityName = "chennai";
		HashMap<String, String> queryParams = new HashMap<>();
		queryParams.put("q", "chennai");
		queryParams.put("units", "metric");
		
		String actualCityName = helper.getResponse("data/2.5/weather", queryParams).jsonPath().getString("name").toLowerCase();
		assertThat(expectedCityName, is(equalTo(actualCityName)));
	} 
	
	public static HashMap<String, String> getWeatherInfoFromAPI(String city) throws IOException {
		HelperMethods helper = new HelperMethods();
		HashMap<String, String> queryParams = new HashMap<>();
		queryParams.put("q", city);
		queryParams.put("units", "metric");

		
		HashMap<String, String> weatherInfo = new HashMap<>();
		JsonPath response = helper.getResponse("data/2.5/weather", queryParams).jsonPath();
		weatherInfo.put("temperature in Celsius", String.valueOf(Math.round(response.getFloat("main.temp"))));
		weatherInfo.put("humidity", String.valueOf(Math.round(response.getFloat("main.humidity"))));
		queryParams.replace("units", "imperial");
		response = helper.getResponse("data/2.5/weather", queryParams).jsonPath(); //For getting temperature in Fahrenheit
		weatherInfo.put("temperature in Fahrenheit", String.valueOf(Math.round(response.getFloat("main.temp"))));
		
		return weatherInfo;

	}
}
