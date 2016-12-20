import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Login {
	public static WebDriver driver;
	
	public static void openBrowser(String appUrl) throws InterruptedException{
		
		System.setProperty("webdriver.chrome.driver", "E:\\chromedriver\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--no-sandbox");
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);

		driver = new ChromeDriver(capabilities);
		
		driver.get(appUrl);
		// maximize the browser window
		driver.manage().window().maximize();
		
		Thread.sleep(1000);
	}
	public int login(String appUrl, String urn, String pass) throws InterruptedException {
		openBrowser(appUrl);
		WebElement username = driver.findElement(By.id("ctl00_PlaceHolderMain_ucLogin_CustomLogin_UserName"));
		username.clear();
		username.sendKeys(urn);

		WebElement password = driver.findElement(By.id("ctl00_PlaceHolderMain_ucLogin_CustomLogin_Password"));
		password.clear();
		password.sendKeys(pass);

		WebElement SignInButton = driver.findElement(By.id("ctl00_ctl27_wPshipList_ctl00_lblRefresh"));
		SignInButton.click();
		
		Thread.sleep(2000);

		String expectedString = "[" + urn + "]";
		WebElement actualElement = driver.findElement(By.id("ctl00_PlaceHolderMain_ucLogin_lblProfileNew"));
		String actualElementName = actualElement.getText().trim();
		
		if (expectedString.toLowerCase().equals(actualElementName.toLowerCase())) {
			System.out.println("Login: Exactly!");
			return 1;
		} else {
			System.out.println("Login: Wrong!");
			return 0;
		}
	}

	public int clickMenu(String parmenu, String submenu, String expectedString, String actualElementCss) throws InterruptedException {

		WebElement ParentMenu = driver.findElement(By.cssSelector(parmenu));
		
		Actions action = new Actions(driver);
		action.moveToElement(ParentMenu).build().perform();
		Thread.sleep(1000);

		WebElement SubMenu = driver.findElement(By.cssSelector(submenu));
		SubMenu.click();
		
		Thread.sleep(2000);
		
		String actualString = driver.findElement(By.cssSelector(actualElementCss)).getText().trim();
		System.out.println("MenuClick: " + expectedString + actualString);
		
		if (expectedString.toLowerCase().equals(actualString.toLowerCase())) {
			System.out.println("MenuClick: Exactly!");
			return 1;
		} else {
			System.out.println("MenuClick: Wrong!");
			return 0;
		}
	}
	
	
	public static void main(String[] args) throws InterruptedException {
		
		String appUrl = "http://hieunt31:8080";
		
		String urn = "0010023456";
		String pass = "123456aA@";
		
		String parmenu = "div#wrapper2 > ul#nav > li:nth-child(13) > a";
		String submenu = "div#wrapper2 > ul#nav > li:nth-child(13) > ul > li:nth-child(1) > a";

		Login login = new Login();
		login.login(appUrl, urn, pass);
		
		Thread.sleep(2000);
		
		String expectedString = "Thông tin tra cứu";
		String actualElementCss = "div#Search > fieldset > legend > span > b";
		
		login.clickMenu(parmenu, submenu, expectedString, actualElementCss);

		Thread.sleep(2000);
		
		FlightSearchForm fltSearch = new FlightSearchForm();
		WebElement FlightDateField;
		WebElement AirlinesCodeField;
		//WebElement AirlinesNameField;
		WebElement FlightNumberField;
		WebElement DepartureCodeField;
		WebElement DepartureNameField;
		WebElement DestinationCodeField;
		WebElement DestinationNameField;
		WebElement GateField;
		WebElement FlightTypeField;
		WebElement searchButton;
		
		FlightDateField = driver.findElement(By.id("ctl00_PlaceHolderMain_ucHCFCImport_dtCREATED_DATE"));
		AirlinesCodeField = driver.findElement(By.id("ctl00_PlaceHolderMain_ucHCFCImport_txtAirlines"));
		//AirlinesNameField = driver.findElement(By.id("ctl00_PlaceHolderMain_ucHCFCImport_ddlAirlines"));
		FlightNumberField = driver.findElement(By.id("ctl00_PlaceHolderMain_ucHCFCImport_txtFlightNumber"));
		DepartureCodeField = driver.findElement(By.id("ctl00_PlaceHolderMain_ucHCFCImport_txtDeparture"));
		DepartureNameField = driver.findElement(By.id("ctl00_PlaceHolderMain_ucHCFCImport_ddlDeparture"));
		DestinationCodeField = driver.findElement(By.id("ctl00_PlaceHolderMain_ucHCFCImport_txtDestination"));
		DestinationNameField = driver.findElement(By.id("ctl00_PlaceHolderMain_ucHCFCImport_ddlDestination"));
		GateField = driver.findElement(By.id("ctl00_PlaceHolderMain_ucHCFCImport_txtGate"));
		FlightTypeField = driver.findElement(By.id("ctl00_PlaceHolderMain_ucHCFCImport_ddlFlightType"));
		searchButton = driver.findElement(By.id("ctl00_PlaceHolderMain_ucHCFCImport_btnSearch"));
		
		fltSearch.enterFlightDate(FlightDateField, "01/12/2016");
		fltSearch.enterAirlinesCode(AirlinesCodeField, "VN");
		//fltSearch.selectValueFromCombobox(AirlinesNameField, "VN");
		fltSearch.enterDepartureCode(DepartureCodeField, "ICN");
		System.out.println("hello!");
		//fltSearch.selectValueFromCombobox(DepartureNameField, "ICN");
		fltSearch.enterDestinationCode(DestinationCodeField, "HAN");
		//FlightSearchForm.DepartureCode = DepartureCodeField.getText();
		//System.out.println("hello!" + FlightSearchForm.DepartureCode);
		//fltSearch.selectValueFromCombobox(DestinationNameField, "HAN");
		fltSearch.enterGate(GateField, "");
		fltSearch.selectValueFromCombobox(FlightTypeField, "1");
		fltSearch.searchFlight(searchButton);
		fltSearch.selectFlightFromDB();
		
		Thread.sleep(5000);
		
	}
}
