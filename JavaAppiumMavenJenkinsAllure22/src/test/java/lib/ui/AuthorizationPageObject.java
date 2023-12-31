package lib.ui;

import org.openqa.selenium.remote.RemoteWebDriver;

public class AuthorizationPageObject extends MainPageObject{
    private static final String
    KEBAB_MENU = "css:input[id='main-menu-input']",
    LOGIN_BTN = "css:menu.login",
    LOGIN_INPUT = "css:input[name='wpName']",
    PASSWORD_INPUT = "css:input[name='wpPassword']",
    SUBMIT_BTN = "css:button#wpLoginAttempt";

    public AuthorizationPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public void clickAuthBtn() {
        this.waitForElementPresent(KEBAB_MENU, "Cannot find auth btn", 5);
        this.waitForElementAndClick(KEBAB_MENU, "Cannot find and click auth btn", 5);
        this.waitForElementPresent(LOGIN_BTN, "Cannot find auth btn", 5);
        this.waitForElementAndClick(LOGIN_BTN, "Cannot find and click auth btn", 5);
    }

    public void enterLoginData(String login, String password) {
        this.waitForElementAndSendKeys(LOGIN_INPUT, login, "cannot find and enter login to the login input", 5);
        this.waitForElementAndSendKeys(PASSWORD_INPUT, password, "cannot find and enter password to the password input", 5);

    }

    public void setSubmitForm() {
        this.waitForElementAndClick(SUBMIT_BTN, "cannot find and click submit auth btn", 5);
    }
}
