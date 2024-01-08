package lib.ui;
import io.qameta.allure.Step;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class SearchPageObject extends MainPageObject {
    protected static String
    SEARCH_INIT_ELEMENT,
    SEARCH_INPUT,
    SEARCH_RESULT_BY_SUBSTRING_TPL,
    SEARCH_CANCEL_BTN,
    SEARCH_RESULT_ELEMENT,
    SEARCH_EMPTY_RESULT_ELEMENT,
    SEARCH_EMPTY_RESULT_LABEL,
    SEARCH_RESULT_LIST,
    SEARCH_INPUT_PLACEHOLDER;

    public SearchPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
    /*TPL (TEMPLATES) method */
    private static String  getResultSearchElement(String substring)
    {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    @Step("Initializing the search field")
    public void initSearchInput()
    {
        this.waitForElementPresent(SEARCH_INIT_ELEMENT, "Cannot find search input after clicking search init element", 5);
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find and click search init element", 5);

    }

    @Step("Typing '{search_line}' into the search field")
    public void typeSearchLine(String search_line)
    {
        this.waitForElementAndSendKeys(SEARCH_INPUT, search_line, "Cannot find and type into search input", 5);
    }

    @Step("Waiting for search result to appear")
    public void waitForSearchResult(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(search_result_xpath, "Cannot find search result with substring " +  substring, 5);
    }

    @Step("Waiting for cancel search result btn to appear")
    public void waitForCancelBtnToAppear()
    {
        this.waitForElementPresent(SEARCH_CANCEL_BTN, "Cannot find 'X' cancel btn", 5);
    }

    @Step("Waiting for cancel search result btn to disappear")
    public void waitForCancelBtnToDisappear()
    {
        this.waitForElementNotPresent(SEARCH_CANCEL_BTN, "'X' cancel btn is still present", 5);
    }

    @Step("Clicking to cancel search btn")
    public void clickCancelSearch()
    {
        this.waitForElementAndClick(SEARCH_CANCEL_BTN, "Cannot find an click 'X' cancel btn", 5);
    }

    @Step("Waiting search result and select an article by substring in article title")
    public void clickByArticleWithSubstring(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(search_result_xpath, "Cannot find and click search result with substring " +  substring, 10);
    }

    @Step("Getting amount of found articles")
    public int getAmountOfFoundArticles()
    {
        this.waitForElementPresent(
                SEARCH_RESULT_ELEMENT,
                "Cannot find anything by request",
                15
        );
        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);
    }

    @Step("Waiting for empty result label")
    public void waitForEmptyResultsLabel()
    {
        this.waitForElementPresent(
                SEARCH_EMPTY_RESULT_ELEMENT,
                "Cannot find empty result element",
                15
        );
        this.waitForElementPresent(
                SEARCH_EMPTY_RESULT_LABEL,
                "Cannot find empty result label",
                15
        );
    }

    @Step("Verifying there are no results for the search")
    public void assertThereIsNoResultOfSearch()
    {
        this.assertElementNotPresent(
                SEARCH_RESULT_LIST,
                "We found some results"
        );
    }

    @Step("Verifying more than 1 result in search list")
    public void verifySearchResultsIsGreaterThanOne()
    {
        this.assertElementLengthIsGreaterThan1(
                SEARCH_RESULT_ELEMENT,
                "cannot find any article",
                10
        );
    }
    @Step("Waiting for search result to disappear")
    public void waitForSearchResultToDisappear()
    {
        this.waitForElementNotPresent(
                SEARCH_RESULT_ELEMENT,
                "Search result is still present!",
                10
        );
    }

    @Step("Verifying element has text")
    public void assertElementHasText()
    {
        this.assertElementHasText(SEARCH_INPUT_PLACEHOLDER,
                "Couldn't find an element",
                10
        );
    }
}
