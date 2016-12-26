package util;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;

public class PageUtil {
	private static WebDriver driver;

	public PageUtil(WebDriver driver) {
		PageUtil.driver = driver;
	}

	public static WebDriver openBrowser() throws InterruptedException, IOException {
		String browser = PropertiesStore.getProperty("browser");
		if (browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", PropertiesStore.getProperty("chrome_path"));
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--no-sandbox");
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			WebDriver driver = new ChromeDriver(capabilities);

			driver.get(PropertiesStore.getProperty("url"));
			driver.manage().window().maximize();
			return driver;
		} else if (browser.equals("iexplorer")) {
			driver = new InternetExplorerDriver();
			return driver;
		} else if (browser.equals("safari")) {
			driver = new SafariDriver();
			return driver;
		} else if (browser.equals("firefox")) {
			driver = new FirefoxDriver();
			return driver;
		} else {
			System.out.println("Cannot open browser!");
			return null;
		}
	}

	public void clickMenu(String menuId) throws InterruptedException {
		WebElement ParentMenu = driver.findElement(By.cssSelector(menuId));
		ParentMenu.click();
	}

	public void clickMenu(String parMenuId, String subMenuId) throws InterruptedException {
		WebElement ParentMenu = driver.findElement(By.cssSelector(parMenuId));

		Actions action = new Actions(driver);
		action.moveToElement(ParentMenu).build().perform();

		WaitFor waitFor = new WaitFor(driver);
		waitFor.presenceOfTheElement(By.cssSelector(subMenuId));

		WebElement SubMenu = driver.findElement(By.cssSelector(subMenuId));
		SubMenu.click();
	}

	public int isClickMenuSuccess(String compareText, String elementId) {

		String elementName = driver.findElement(By.cssSelector(elementId)).getText().trim();

		if (compareText.toLowerCase().equals(elementName.toLowerCase())) {
			System.out.println("MenuClick: Exactly!");
			return 1;
		} else {
			System.out.println("MenuClick: Wrong!");
			return 0;
		}
	}
	
	public int isResultValid(String compareText, String elementId) {
		String elementName = driver.findElement(By.cssSelector(elementId)).getText().trim();

		if (compareText.toLowerCase().equals(elementName.toLowerCase())) {
			System.out.println("Result: Exactly!");
			return 1;
		} else {
			System.out.println("Result: Wrong!");
			return 0;
		}
	}
}
