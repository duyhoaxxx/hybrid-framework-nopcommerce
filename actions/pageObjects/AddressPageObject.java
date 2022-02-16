package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.AddressesPageUI;

public class AddressPageObject extends BasePage {
	private WebDriver driver;

	public AddressPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public boolean isAddressesPageDisplayed() {
		return isElementDisplay(driver, AddressesPageUI.ADDRESSES_HEADER);
	}
}
