package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.user.ChangePasswordPageUI;

public class UserChangePasswordPageObject extends BasePage {
	private WebDriver driver;

	public UserChangePasswordPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public boolean isChangePasswordPageDisplayed() {
		return isElementDisplay(driver, ChangePasswordPageUI.CHANGE_PASSWORD_HEADER);
	}
}
