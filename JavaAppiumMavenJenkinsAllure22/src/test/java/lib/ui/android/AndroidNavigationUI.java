package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidNavigationUI extends NavigationUI {
    static {
        MY_LIST_LINK = "id:org.wikipedia:id/nav_tab_reading_lists";
        SKIP_LANGUAGE_BTN = "xpath:(//*[contains(@text,'Skip')])";
    }

    public AndroidNavigationUI(RemoteWebDriver driver)
    {
        super(driver);
    }
}
