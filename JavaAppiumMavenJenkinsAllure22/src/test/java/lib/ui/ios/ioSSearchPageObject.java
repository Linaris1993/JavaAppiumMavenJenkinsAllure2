package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class ioSSearchPageObject extends SearchPageObject
{
    static {
        SEARCH_INIT_ELEMENT="xpath://XCUIElementTypeSearchField[@name='Search Wikipedia'])";
        SEARCH_INPUT="xpath://XCUIElementTypeSearchField[@value='Search Wikipedia'])";
        SEARCH_RESULT_BY_SUBSTRING_TPL="xpath:XCUIElementTypeLink[contains(@name,'{SUBSTRING}')]";
        SEARCH_CANCEL_BTN="id:Close";
        SEARCH_RESULT_ELEMENT="id://XCUIElementTypeLink";
        SEARCH_EMPTY_RESULT_ELEMENT="xpath:XCUIElementTypeStaticText[@name='No results found']";
    }
    public ioSSearchPageObject (AppiumDriver driver)
    {
        super(driver);
    }
}
