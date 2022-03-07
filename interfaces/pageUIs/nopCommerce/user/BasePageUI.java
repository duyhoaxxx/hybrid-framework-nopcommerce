package pageUIs.nopCommerce.user;

public class BasePageUI {
	public static final String CUSTOMER_INFO_LINK = "//div[@class='block block-account-navigation']//a[text()='Customer info']";
	public static final String ADDRESS_LINK = "//div[@class='block block-account-navigation']//a[text()='Addresses']";
	public static final String ORDERS_LINK = "//div[@class='block block-account-navigation']//a[text()='Orders']";
	public static final String DOWNLOADABLE_PRODUCTS_LINK = "//div[@class='block block-account-navigation']//a[text()='Downloadable products']";
	public static final String BACK_IN_STOCK_SUBCRIPTIONS_LINK = "//div[@class='block block-account-navigation']//a[text()='Back in stock subscriptions']";
	public static final String REWARD_POINTS_LINK = "//div[@class='block block-account-navigation']//a[text()='Reward points']";
	public static final String CHANGE_PASSWORD_LINK = "//div[@class='block block-account-navigation']//a[text()='Change password']";
	public static final String MY_PRODUCT_REVIEWS_LINK = "//div[@class='block block-account-navigation']//a[text()='My product reviews']";
	public static final String LOGOUT_LINK_AT_USER = "//a[@class='ico-logout']";
	public static final String LOGOUT_LINK_AT_ADMIN = "//a[text()='Logout']";

	public static final String DYNAMIC_PAGE_HEADER = "//div[@class='footer']//a[text()='%s']";
	public static final String DYNAMIC_PAGE_FOOTER = "//div[@class='header']//a[text()='%s']";
	public static final String DYNAMIC_TEXTBOX_BY_ID = "//input[@id='%s']";
	public static final String DYNAMIC_RADIO_BUTTON_BY_ID = "//input[@id='%s']";
	public static final String DYNAMIC_DROPDOWN_BY_NAME = "//select[@name='%s']";
	public static final String DYNAMIC_BUTTON_BY_TEXT = "//button[text()='%s']";
}
