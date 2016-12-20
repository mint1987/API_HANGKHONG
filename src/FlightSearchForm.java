import java.awt.FlowLayout;
import java.sql.Date;
import java.sql.SQLException;

import org.openqa.selenium.support.ui.Select;

import Util.OracleJDBC;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class FlightSearchForm {
	
	WebDriver driver;
	public static String FlightDate;
	public static String AirlinesCode;
	public static String FlightNumber;
	public static String DepartureCode;
	public static String DestinationCode;
	public static String Gate;
	public static String FlightType;
	public static Select selectByValue;


	private WebElement AirlinesCodeField;
	private WebElement FlightDateField;
	private WebElement AirlinesNameField;	
	private WebElement FlightNumberField;
	private WebElement DepartureCodeField;
	private WebElement DepartureNameField;
	private WebElement DestinationCodeField;
	private WebElement DestinationNameField;
	private WebElement GateField;
	private WebElement FlightTypeField;
	
	public void enterFlightDate (WebElement FlightDateField, String FlightDate){
		FlightDateField.clear();
		FlightDateField.sendKeys(FlightDate);
		FlightDateField.sendKeys(Keys.TAB);
		FlightSearchForm.FlightDate = FlightDate;
		System.out.println("FlightDate: " + FlightSearchForm.FlightDate);
	}
	
	public void enterAirlinesCode(WebElement AirlinesCodeField, String AirlinesCode){
		AirlinesCodeField.clear();
		AirlinesCodeField.sendKeys(AirlinesCode);
		AirlinesCodeField.sendKeys(Keys.TAB);
		FlightSearchForm.AirlinesCode = AirlinesCode;
	}
	
	public void selectValueFromCombobox(WebElement elementField, String valueName) throws InterruptedException{
		Select selectByValue = new Select (elementField);
		selectByValue.selectByValue(valueName);
		FlightSearchForm.selectByValue = selectByValue;
		elementField.sendKeys(Keys.TAB);
		Thread.sleep(2000);
	}
	
	public void enterFlightNumber(WebElement FlightNumberField, String FlightNumber){
		FlightNumberField.clear();
		FlightNumberField.sendKeys(FlightNumber);
		FlightSearchForm.FlightNumber = FlightNumber;
	}
	
	public void enterDepartureCode(WebElement DepartureCodeField, String DepartureCode){
		DepartureCodeField.clear();
		DepartureCodeField.sendKeys(DepartureCode);
		DepartureCodeField.sendKeys(Keys.TAB);
		FlightSearchForm.DepartureCode = DepartureCode;
	}
	
	public void enterDestinationCode(WebElement DestinationCodeField, String DestinationCode){
		DestinationCodeField.clear();
		DestinationCodeField.sendKeys(DestinationCode);
		DestinationCodeField.sendKeys(Keys.TAB);
		FlightSearchForm.DestinationCode = DestinationCode;
	}
	
	public void enterGate(WebElement GateField, String Gate){
		GateField.clear();
		GateField.sendKeys(Gate);
		FlightSearchForm.Gate = Gate;
	}
	
	public void searchFlight(WebElement searchButton){
		searchButton.click();
	}
	
	public void selectFlightFromDB(){
		OracleJDBC connectDB = new OracleJDBC();

		try {

			connectDB.selectRecordsFromDbFlightTable(FlightDate, AirlinesCode, FlightNumber, DepartureCode, DestinationCode, Gate, FlightType);

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		}
		
	}
}
