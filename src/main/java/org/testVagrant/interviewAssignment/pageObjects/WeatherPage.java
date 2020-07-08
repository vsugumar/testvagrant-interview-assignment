package org.testVagrant.interviewAssignment.pageObjects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import static java.util.stream.Collectors.*;
import static org.testng.Assert.assertTrue;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;


public class WeatherPage extends PageHandler {

	@FindBy(id = "h_sub_menu")
	WebElement subMenu;

	@FindBy(linkText = "WEATHER")
	WebElement weatherMenu;

	@FindBy(className = "cityText")
	List<WebElement> citiesAppearingInMap;

	@FindBy(className = "comment_cont")
	WebElement cityListPane;

	@FindBy(xpath = "//*[@type='checkbox']")
	List<WebElement> checkBoxes;

	@FindBy(className = "leaflet-popup-content-wrapper")
	WebElement temperatureDetailsPopOver;

	@FindBy(xpath = "//*[contains(@class, 'leaflet-marker-icon')]")
	List<WebElement> mapElements;

	@FindBy(xpath = "//*[contains(@href, 'close')]")
	WebElement popOverClose;
	
	@FindBy(xpath = "//b[contains(text(), 'Humidity')]")
	WebElement humidity;
	
	@FindBy(xpath = "//*[text()='No Thanks']")
	WebElement noThanksLink;

	private final String xpathOfParentMap = "//*[contains(@class, 'leaflet-marker-pane')]";
	private final String xpathOfMapElements = "//*[contains(@class, 'leaflet-marker-icon')]";
	private final String xpathOfTemperatureElementCelsius = "//*[@title='cityToAppear']//*[@class='tempRedText']";
	private final String xpathOfTemperatureElementFahrenheit = "//*[@title='cityToAppear']//*[@class='tempWhiteText']";

	protected WeatherPage initializeElements() {
		PageFactory.initElements(PageHandler.driver, this);
		return this;
	}

	protected void goToWeatherPage() {
		waitTillElementToBeClickable(2, subMenu);
		try {
			subMenu.click();
			weatherMenu.click();
		} catch (ElementClickInterceptedException e) {
			noThanksLink.click();
			waitTillElementToBeClickable(2, subMenu);
			subMenu.click();
			weatherMenu.click();
		}	
	}

	protected ArrayList<String> getCitiesAppearingInMap() {
		waitTillChildElementsToBeVisible(3, xpathOfParentMap, xpathOfMapElements);
		return citiesAppearingInMap.stream().map(WebElement::getText).
				collect(toCollection(ArrayList<String>::new));
	}

	protected void selectCity(String city) {
		waitTillVisibilityOfElement(2, cityListPane);
		if(elementExists(xpathOfMapElements)) { 
			waitTillVisibilityOfAllElements(3, mapElements);
		}
		getElementById(city).click();
	}

	protected ArrayList<String> getSelectedCities() {
		waitTillVisibilityOfElement(3, cityListPane);	
		return checkBoxes.stream().filter(WebElement::isSelected).map(selectedCity -> selectedCity.
				findElement(By.xpath("..")).getText().trim()).collect(toCollection(ArrayList<String>::new));

	}

	protected void isTempDisplayedForAllSelectedCities(ArrayList<String> selectedCities) {		
		selectedCities.forEach(city -> {
			String tempInCelcius = "//*[@title='"+city+"']//div[@class='temperatureContainer']//span[@class='tempRedText']";
			String tempInFarenheit = "//*[@title='"+city+"']//div[@class='temperatureContainer']//span[@class='tempWhiteText']";
			boolean isTempInCelciusDispayed = false;
			boolean tempInFarenheitDispayed = false;
			try {
				isTempInCelciusDispayed = getElementByXpath(tempInCelcius).isDisplayed();
				tempInFarenheitDispayed = getElementByXpath(tempInFarenheit).isDisplayed();
			} catch (NoSuchElementException e) {
				Assert.fail("Temperature is not displayed for the city "+city);
			}
			assertTrue(isTempInCelciusDispayed, "Temperature in Celcius is not displayed for the city "+city);
			assertTrue(tempInFarenheitDispayed, "Temperature in Farenheit is not displayed for the city "+city);
		});

	}

	protected HashMap<String, String> getTemperatureDetailsForSelectedCities(ArrayList<String> selectedCities) {
		HashMap<String, String> cityTempDetails = new HashMap<>();
		selectedCities.forEach(city -> {
			String xpath = xpathOfTemperatureElementCelsius.replace("cityToAppear", city);
			getElementByXpath(xpath).click(); //Clicking on temperature to get the weather info
			waitTillVisibilityOfElement(3, temperatureDetailsPopOver);
			String tempDetails = temperatureDetailsPopOver.getText();
			cityTempDetails.put(city, tempDetails);
			popOverClose.click();
		});
		return cityTempDetails;
	}

	protected void unselectAllCities(ArrayList<String> selectedCities) {
		selectedCities.forEach(city -> {
			WebElement cityInMap = getElementByXpath("//*[@class='cityText' and text()='"+city+"']");
			getElementById(city).click();
			waitTillElementToBeInvisible(2, cityInMap);
		});	
	}
	
	protected HashMap<String, String> getWeatherInfo(String city) {
		selectCity(city);
		String temperatureInCelsius = getElementByXpath(xpathOfTemperatureElementCelsius.replace("cityToAppear", city))
				.getText().replaceAll("\\D", "");
		String temperatureInFahrenheit = getElementByXpath(xpathOfTemperatureElementFahrenheit.replace("cityToAppear", city))
				.getText().replaceAll("\\D", "");
		getElementByXpath(xpathOfTemperatureElementCelsius.replace("cityToAppear", city)).click();
		waitTillVisibilityOfElement(3, temperatureDetailsPopOver);
		String humidityDetails = humidity.getText().replaceAll("\\D", "");
		popOverClose.click();
		
		HashMap<String, String> weatherInfo = new HashMap<>();
		weatherInfo.put("temperature in Celsius", temperatureInCelsius);
		weatherInfo.put("temperature in Fahrenheit", temperatureInFahrenheit);
		weatherInfo.put("humidity", humidityDetails);
		
		return weatherInfo;
	}

}
