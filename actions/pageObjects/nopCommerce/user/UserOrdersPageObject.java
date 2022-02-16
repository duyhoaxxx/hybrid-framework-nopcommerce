package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.user.OrdersPageUI;

public class UserOrdersPageObject extends BasePage {
	private WebDriver driver;

	public UserOrdersPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public boolean isOrdersPageDisplayed() {
		return isElementDisplay(driver, OrdersPageUI.ORDERS_HEADER);
	}
}
