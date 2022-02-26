package pageUIs.jQuery;

public class HomePageUI {
	public static final String PAGINATION_PAGE_BY_NUMBER = "//li[@class='qgrd-pagination-page']//a[text()='%s']";
	public static final String PAGINATION_PAGE_ACTIVED_BY_NUMBER = "//li[@class='qgrd-pagination-page']//a[@class='qgrd-pagination-page-link active' and text()='%s']";
	public static final String HEADER_TEXTBOX_BY_LABEL = "//div[text()='%s']/parent::div/following-sibling::input";
	public static final String TOTAL_PAGINATION = "//ul[@class='qgrd-pagination-ul']//li";
	public static final String ALL_ROW_EACH_PAGE = "//tbody//tr";
	public static final String ALL_ROW_COUNTRY_EACH_PAGE = "//tbody//tr//td[@data-key='country']";

	public static final String COLUMN_INDEX_BY_NAME = "//tr//td[text()='%s']/preceding-sibling::td";
	public static final String TEXTBOX_BY_COLUMN_INDEX_AND_ROW_INDEX = "//tbody//tr[%s]//td[%s]//input";
	public static final String DROPDOWN_BY_COLUMN_INDEX_AND_ROW_INDEX = "//tbody//tr[%s]//td[%s]//select";
	public static final String CHECKBOX_BY_COLUMN_INDEX_AND_ROW_INDEX = "//tbody//tr[%s]//td[%s]//input[@type='checkbox']";
	public static final String LOAD_BUTTON = "//button[@id='btnLoad']";
	public static final String ICON_NAME_BY_ROW_NUMBER = "//tbody//tr[%s]//button[@title='%s']";
	public static final String FILE_NAME_UPLOAD = "//p[@class='name' and text()='%s']";
	
	
}
