package lib.ui.mobileWeb;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWSearchPageObject extends SearchPageObject {
    static {
        SEARCH_INPUT="css:[id='searchform'] input[name='search']";
        SEARCH_RESULT_BY_SUBSTRING_TPL="xpath://div[contains(@class, 'wikidata-description')][contains(text(),'{SUBSTRING}')]";
        SEARCH_RESULT_ELEMENT="css:ul.page-list>li.page-summary";
        SEARCH_EMPTY_RESULT_ELEMENT="css:p.without-results";
        SEARCH_CANCEL_BTN = "//div[@class='header-action']";
    }
    public MWSearchPageObject (RemoteWebDriver driver)
    {
        super(driver);
    }
}
