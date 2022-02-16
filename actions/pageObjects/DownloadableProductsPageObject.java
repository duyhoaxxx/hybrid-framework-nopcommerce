package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.DownloadableProductsPageUI;

public class DownloadableProductsPageObject extends BasePage {
	private WebDriver driver;

	public DownloadableProductsPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public boolean isDownloadableProductsPageDisplayed() {
		return isElementDisplay(driver, DownloadableProductsPageUI.DOWNLOADABLE_PRODUCTS_HEADER);
	}
}
