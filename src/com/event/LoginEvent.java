package com.event;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.detail.LoginDetail;
import com.page.LoginPage;

public class LoginEvent {
	private static WebDriver driver;
	private LoginPage loginPage;
	private LoginDetail loginDetail;

	public LoginEvent(WebDriver driver, LoginDetail loginDetail) {
		LoginEvent.driver = driver;
		this.loginDetail = loginDetail;
		this.loginPage = new LoginPage(driver);
	}

	public static void openBrowser(String appUrl) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "E:\\chromedriver\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--no-sandbox");
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);

		driver = new ChromeDriver(capabilities);

		driver.get(appUrl);
		driver.manage().window().maximize();

		Thread.sleep(1000);
	}

	public void Login() {
		loginPage.enterUsername(loginDetail.getUsername());
		loginPage.enterPassword(loginDetail.getPassword());

		loginPage.clickLoginButton();
	}

	public int isLoginSuccess(String username) {
		String accountInfor = loginPage.getAccountInformationDetail();

		if (accountInfor.toLowerCase().equals(("[" + username + "]").toLowerCase())) {
			System.out.println("Login: Exactly!");
			return 1;
		} else {
			System.out.println("Login: Wrong!");
			return 0;
		}
	}
}
