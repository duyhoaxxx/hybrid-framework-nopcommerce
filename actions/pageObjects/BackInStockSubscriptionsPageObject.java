package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.BackInStockSubscriptionsPageUI;

public class BackInStockSubscriptionsPageObject extends BasePage {
	private WebDriver driver;

	public BackInStockSubscriptionsPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public boolean isBackInStockSubscriptionsPageDisplayed() {
		return isElementDisplay(driver, BackInStockSubscriptionsPageUI.BACK_IN_STOCK_SUBSCRIPTIONS_HEADER);
	}
}
