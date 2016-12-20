package com.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class FlightSearchPage {
	WebDriver driver;

	private WebElement dtpFlightDate;
	private WebElement txtAirlinesCode;
	private WebElement cbxAirlinesName;
	private WebElement txtFlightNumber;
	private WebElement txtDepartureCode;
	private WebElement cbxDepartureName;
	private WebElement txtDestinationCode;
	private WebElement cbxDestinationName;
	private WebElement txtGate;
	private WebElement cbxFlightType;
	private WebElement btnFlightSearch;

	private String flightDateId = "ctl00_PlaceHolderMain_ucHCFCImport_dtCREATED_DATE";
	private String airlineCodeId = "ctl00_PlaceHolderMain_ucHCFCImport_txtAirlines";
	private String airlineNameId = "ctl00_PlaceHolderMain_ucHCFCImport_ddlAirlines";
	private String flightNoId = "ctl00_PlaceHolderMain_ucHCFCImport_txtFlightNumber";
	private String departureCodeId = "ctl00_PlaceHolderMain_ucHCFCImport_txtDeparture";
	private String departureNameId = "ctl00_PlaceHolderMain_ucHCFCImport_ddlDeparture";
	private String destinationCodeId = "ctl00_PlaceHolderMain_ucHCFCImport_txtDestination";
	private String destinationNameId = "ctl00_PlaceHolderMain_ucHCFCImport_ddlDestination";
	private String gateId = "ctl00_PlaceHolderMain_ucHCFCImport_txtGate";
	private String flightTypeId = "ctl00_PlaceHolderMain_ucHCFCImport_ddlFlightType";
	private String searchButtonId = "ctl00_PlaceHolderMain_ucHCFCImport_btnSearch";

	public FlightSearchPage(WebDriver driver) {
		this.driver = driver;
	}

	public void enterFlightDate(String flightDate) {
		dtpFlightDate = driver.findElement(By.id(flightDateId));
		dtpFlightDate.clear();
		dtpFlightDate.sendKeys(flightDate);
		dtpFlightDate.sendKeys(Keys.TAB);
	}

	public void enterAirlinesCode(String airlineCode) {
		txtAirlinesCode = driver.findElement(By.id(airlineCodeId));
		txtAirlinesCode.clear();
		txtAirlinesCode.sendKeys(airlineCode);
		txtAirlinesCode.sendKeys(Keys.TAB);
	}

	public void selectAirlinesName(String airlinesValue) throws InterruptedException {
		cbxAirlinesName = driver.findElement(By.id(airlineNameId));
		Select selectByValue = new Select(cbxAirlinesName);
		selectByValue.selectByValue(airlinesValue);
		cbxAirlinesName.sendKeys(Keys.TAB);
		Thread.sleep(2000);
	}

	public void enterFlightNumber(String flightNumber) {
		txtFlightNumber = driver.findElement(By.id(flightNoId));
		txtFlightNumber.clear();
		txtFlightNumber.sendKeys(flightNumber);
	}

	public void enterDepartureCode(String departureCode) {
		txtDepartureCode = driver.findElement(By.id(departureCodeId));
		txtDepartureCode.clear();
		txtDepartureCode.sendKeys(departureCode);
		txtDepartureCode.sendKeys(Keys.TAB);
	}

	public void selectDepartureName(String departureValue) throws InterruptedException {
		cbxDepartureName = driver.findElement(By.id(departureNameId));
		Select selectByValue = new Select(cbxDepartureName);
		selectByValue.selectByValue(departureValue);
		cbxDepartureName.sendKeys(Keys.TAB);
		Thread.sleep(2000);
	}

	public void enterDestinationCode(String destinationCode) {
		txtDestinationCode = driver.findElement(By.id(destinationCodeId));
		txtDestinationCode.clear();
		txtDestinationCode.sendKeys(destinationCode);
		txtDestinationCode.sendKeys(Keys.TAB);
	}

	public void selectDestinationName(String destinationValue) throws InterruptedException {
		cbxDestinationName = driver.findElement(By.id(destinationNameId));
		Select selectByValue = new Select(cbxDestinationName);
		selectByValue.selectByValue(destinationValue);
		cbxDestinationName.sendKeys(Keys.TAB);
		Thread.sleep(2000);
	}

	public void enterGate(String gate) {
		txtGate = driver.findElement(By.id(gateId));
		txtGate.clear();
		txtGate.sendKeys(gate);
	}

	public void selectFlightType(String flightTypeValue) throws InterruptedException {
		cbxFlightType = driver.findElement(By.id(flightTypeId));
		Select selectByValue = new Select(cbxFlightType);
		selectByValue.selectByValue(flightTypeValue);
		cbxFlightType.sendKeys(Keys.TAB);
		Thread.sleep(2000);
	}

	public void clickSearchButton() {
		btnFlightSearch = driver.findElement(By.id(searchButtonId));
		btnFlightSearch.click();
	}
}
