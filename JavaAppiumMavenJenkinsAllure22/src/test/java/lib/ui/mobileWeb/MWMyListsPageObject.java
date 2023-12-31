package lib.ui.mobileWeb;

import org.openqa.selenium.remote.RemoteWebDriver;
import lib.ui.MyListsPageObject;

public class MWMyListsPageObject extends MyListsPageObject {
    static {
        SAVED_ARTICLES = "xpath://ul[contains(@class, 'watchlist')]//h3[contains(text(), '{TITLE}')]";
        REMOVE_FROM_SAVED_BTN = "//ul[contains(@class, 'watchlist')]//h3[contains(text(), '{TITLE}')]/../../div[contains(@class, 'watched')]";
    }
    public MWMyListsPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

}
