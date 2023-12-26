package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidMyListsPageObject extends MyListsPageObject {
        static {
            FOLDER_BY_NAME_TPL = "xpath://*[@text='{FOLDER_NAME}']";
            SAVED_ARTICLES = "id:org.wikipedia:id/item_title_container";
        }

        public AndroidMyListsPageObject(RemoteWebDriver driver)
        {
            super(driver);
        }
    }