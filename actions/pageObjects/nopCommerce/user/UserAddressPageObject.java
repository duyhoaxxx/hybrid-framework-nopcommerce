package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.user.AddressesPageUI;

public class UserAddressPageObject extends BasePage {
	private WebDriver driver;

	public UserAddressPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public boolean isAddressesPageDisplayed() {
		return isElementDisplay(driver, AddressesPageUI.ADDRESSES_HEADER);
	}
}
