package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSMyListsPageObject extends MyListsPageObject {
    static {
        SAVED_ARTICLES = "xpath://XCUIElementTypeLink[contains(@text, '{TITLE}']";
    }
    public iOSMyListsPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}
