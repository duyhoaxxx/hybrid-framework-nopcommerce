package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.MyProductReviewsPageUI;

public class MyProductReviewsPageObject extends BasePage {
	private WebDriver driver;

	public MyProductReviewsPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public boolean isMyProductReviewsPageDisplayed() {
		return isElementDisplay(driver, MyProductReviewsPageUI.MY_PRODUCT_REVIEWS_HEADER);
	}
}
