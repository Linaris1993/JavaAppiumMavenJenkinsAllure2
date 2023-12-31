package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class NavigationUI extends MainPageObject {

    protected static String
            MY_LIST_LINK,
            SKIP_LANGUAGE_BTN,
            OPEN_NAVIGATION;


    public NavigationUI(RemoteWebDriver driver)
    {
        super(driver);
    }

    public void clickMyLists()
    {
            if (Platform.getInstance().isMW()) {
                this.tryClickElementWithFewAttempts(
                        MY_LIST_LINK,
                        "Cannot find navigation button to My list",
                        5
                );
            } else {
                this.waitForElementAndClick(
                        MY_LIST_LINK,
                        "Cannot find navigation button to My list",
                        5
                );
            }
    }

    public void skipLanguage()
    {
     this.skipLanguageFunction(
             SKIP_LANGUAGE_BTN,
             "Cannot find 'Skip' btn",
             10
     );
    }

    public void setOpenNavigation()
    {
        if (Platform.getInstance().isMW()) {
            this.waitForElementAndClick(OPEN_NAVIGATION,
                    "Cannot find and click open navigation button",
                    5);

        } else {
            System.out.println("Method openNavigation() do nothing for platform " +  Platform.getInstance().getPlatformVar());
        }
    }
}
