package lib.ui.mobileWeb;

import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWNavigationUI extends NavigationUI {
        static {
        MY_LIST_LINK = "id:a[data-event-name='menu.watchlist']";
        OPEN_NAVIGATION = "css:input[id='main-menu-input']";
    }
    public MWNavigationUI(RemoteWebDriver driver) {
        super(driver);
    }
}
