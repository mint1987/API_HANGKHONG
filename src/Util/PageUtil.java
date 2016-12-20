package Util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


public class PageUtil {
	private static WebDriver driver;

	public PageUtil(WebDriver driver) {
		PageUtil.driver = driver;
	}

	public void clickMenu(String menuId) throws InterruptedException {
		WebElement ParentMenu = driver.findElement(By.cssSelector(menuId));
		ParentMenu.click();
		Thread.sleep(2000);
	}

	public void clickMenu(String parMenuId, String subMenuId) throws InterruptedException {
		WebElement ParentMenu = driver.findElement(By.cssSelector(parMenuId));

		Actions action = new Actions(driver);
		action.moveToElement(ParentMenu).build().perform();
		Thread.sleep(1000);

		WebElement SubMenu = driver.findElement(By.cssSelector(subMenuId));
		SubMenu.click();

		Thread.sleep(2000);
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
}
