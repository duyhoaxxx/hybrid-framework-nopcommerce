package commons;

import java.io.File;

public class GlobalConstants {
	public static final String PORTAL_PAGE_URL = "https://demo.nopcommerce.com/";
	public static final String ADMIN_PAGE_URL = "https://admin-demo.nopcommerce.com/";
	public static final String OS_NAME = System.getProperty("os.name");
	public static final String PROJECT_PATH = System.getProperty("user.dir");
	public static final String UPLOAD_FILE_FOLDER = PROJECT_PATH + File.separator + "uploadFiles";
	public static final String DOWNLOAD_FILE_FOLDER = PROJECT_PATH + File.separator + "downloadFiles";
	public static final String BROWSER_LOG_FOLDER = PROJECT_PATH + File.separator + "browserLogs";
}
