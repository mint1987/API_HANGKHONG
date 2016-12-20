package com.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
	WebDriver driver;

	private WebElement txtUsername;
	private WebElement txtPassword;
	private WebElement btnLogin;
	private WebElement titAccountInformation;

	private String usernameId = "ctl00_PlaceHolderMain_ucLogin_CustomLogin_UserName";
	private String passwordId = "ctl00_PlaceHolderMain_ucLogin_CustomLogin_Password";
	private String loginButtonId = "ctl00_ctl27_wPshipList_ctl00_lblRefresh";
	private String accountInformationId = "ctl00_PlaceHolderMain_ucLogin_lblProfileNew";

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	public void enterUsername(String username) {
		txtUsername = driver.findElement(By.id(usernameId));
		txtUsername.clear();
		txtUsername.sendKeys(username);
	}

	public void enterPassword(String password) {
		txtPassword = driver.findElement(By.id(passwordId));
		txtPassword.clear();
		txtPassword.sendKeys(password);
	}

	public void clickLoginButton() {
		btnLogin = driver.findElement(By.id(loginButtonId));
		btnLogin.click();
	}

	public String getAccountInformationDetail() {
		titAccountInformation = driver.findElement(By.id(accountInformationId));
		String accountInformationDetail = titAccountInformation.getText().trim();
		return accountInformationDetail;
	}
}
