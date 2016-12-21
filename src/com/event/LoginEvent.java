package com.event;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.detail.LoginDetail;
import com.page.LoginPage;

import util.WaitFor;

public class LoginEvent {
	private static WebDriver driver;
	private LoginPage loginPage;
	private LoginDetail loginDetail;
	private WaitFor waitFor;

	public LoginEvent(WebDriver driver, LoginDetail loginDetail) {
		LoginEvent.driver = driver;
		this.loginDetail = loginDetail;
		this.loginPage = new LoginPage(driver);
		this.waitFor = new WaitFor(driver);
	}

	public void waitForUsernameTextboxAppear() {
		waitFor.presenceOfTheElement(By.id(loginPage.getusernameElementId()));
	}

	public void login() throws InterruptedException, IOException {
		loginPage.enterUsername(loginDetail.getUserName());
		loginPage.enterPassword(loginDetail.getPassWord());
		loginPage.clickLoginButton();
	}

	public void waitForAccountNameAppear() {
		waitFor.presenceOfTheElement(By.id(loginPage.getAccountInforElementId()));
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
