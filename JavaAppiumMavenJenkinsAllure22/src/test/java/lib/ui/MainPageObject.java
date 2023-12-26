package lib.ui;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.apache.tools.ant.taskdefs.optional.javah.JavahAdapter;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import java.time.Duration;
import lib.CoreTestCase;
import lib.Platform;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class MainPageObject {
    protected RemoteWebDriver driver;

    public MainPageObject(RemoteWebDriver driver) {
        this.driver = driver;
    }


    public WebElement waitForElementPresent(String locator, String error_message, long timeoutInSeconds) {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    public WebElement assertElementLengthIsGreaterThan1(String locator, String error_message, long timeoutInSeconds) {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(by, 1));
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    public WebElement waitForElementAndClick(String locator, String error_message, long timeoutInSeconds) {
        By by = this.getLocatorByString(locator);
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    public WebElement skipLanguageFunction(String locator, String error_message, long timeoutInSeconds) {
        By by = this.getLocatorByString(locator);
        WebElement skipLanguage = waitForElementPresent(locator, error_message, timeoutInSeconds);
        skipLanguage.click();
        return skipLanguage;
    }

    public WebElement waitForElementAndSendKeys(String locator, String value, String error_message, long timeoutInSeconds) {
        By by = this.getLocatorByString(locator);
        WebElement inputField = waitForElementPresent(locator, error_message, timeoutInSeconds);
        inputField.sendKeys(value);
        return inputField;
    }

    public boolean waitForElementNotPresent(String locator, String error_message, long timeoutInSeconds) {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    public WebElement waitForElementAndClear(String locator, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    public void swipeUp() {
        if (driver instanceof AppiumDriver) {
            TouchAction action = new TouchAction((AppiumDriver) driver);
            Dimension size = driver.manage().window().getSize();
            int center_x = size.width / 2;
            int start_y = (int) (size.height * 0.70);
            int end_y = (int) (size.height * 0.30);

            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
            Sequence swipe = new Sequence(finger, 1);
            swipe.addAction(finger.createPointerMove(Duration.ofSeconds(0),
                    PointerInput.Origin.viewport(), center_x, (int) start_y));
            swipe.addAction(finger.createPointerDown(0));
            swipe.addAction(finger.createPointerMove(Duration.ofMillis(700),
                    PointerInput.Origin.viewport(), center_x, (int) end_y));
            swipe.addAction(finger.createPointerUp(0));
            driver.perform(Arrays.asList(swipe));
        } else {
            System.out.println("Method swipeUp() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }

    }
//    public void verticalSwipeToBottom(){
//        Dimension size = driver.manage().window().getSize();
//        int startY = (int) (size.height * 0.70);
//        int endY = (int) (size.height * 0.30);
//        int centerX = size.width / 2;
//
//        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH,"finger");
//        Sequence swipe = new Sequence(finger,1);
//
//        //Двигаем палец на начальную позицию
//        swipe.addAction(finger.createPointerMove(Duration.ofSeconds(0),
//                PointerInput.Origin.viewport(),centerX,(int)startY));
//        //Палец прикасается к экрану
//        swipe.addAction(finger.createPointerDown(0));
//
//        //Палец двигается к конечной точке
//        swipe.addAction(finger.createPointerMove(Duration.ofMillis(700),
//        PointerInput.Origin.viewport(),centerX,(int)endY));
//
//        //Убираем палец с экрана
//        swipe.addAction(finger.createPointerUp(0));
//
//        //Выполняем действия
//        driver.perform(Arrays.asList(swipe));
//    }


    public void swipeUpQuick() {
        swipeUp();
    }

    public void scrollWebPageUp() {
        if (Platform.getInstance().isMW()) {
            JavascriptExecutor JSExecutor = (JavascriptExecutor) driver;
            JSExecutor.executeScript("window.scrollBy(0, 250)");
        } else {
            System.out.println("Method scrollWebPageUp() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    public void scrollWebPageTillElementNotVisible(String locator, String error_message, int max_swipes) {
        int already_swiped = 0;

        WebElement element = this.waitForElementPresent(locator, error_message, 10);
        while (!this.isElementLocatedOnTheScreen(locator)) {
            scrollWebPageUp();
            ++already_swiped;
            if (already_swiped > max_swipes) {
                Assert.assertTrue(error_message, element.isDisplayed());
            }
        }

    }
    public void swipeUpToFindElement(String locator, String error_message, int max_swipes) {
        By by = this.getLocatorByString(locator);
//        driver.findElements(by);
//        driver.findElements(by).size(); //will find amount of elements found in findElements();
        int already_swiped = 0;
        while (driver.findElements(by).size() == 0) {
            if (already_swiped > max_swipes) {
                waitForElementPresent(locator, "Cannot find element by swiping up. \n" + error_message, 0);
                return;
            }
            swipeUpQuick();
            ++already_swiped;
        }
    }

    public void swipeUpTillElementAppear(String locator, String error_message, int max_swipes) {
        int already_swiped = 0;
        while (!this.isElementLocatedOnTheScreen(locator)) {
            if (already_swiped > max_swipes) {
                Assert.assertTrue(error_message, this.isElementLocatedOnTheScreen(locator));
            }
            swipeUpQuick();
            ++already_swiped;
        }
    }

    public boolean isElementLocatedOnTheScreen(String locator) {
        int element_location_by_y = this.waitForElementPresent(locator, "Cannot find element by locator", 1).getLocation().getY();
        if (Platform.getInstance().isMW()) {
            JavascriptExecutor JSExecutor = (JavascriptExecutor)  driver;
            Object js_result = JSExecutor.executeScript("return window.pageYOffset");
            element_location_by_y -= Integer.parseInt(js_result.toString());
        }
        int screen_size_by_y = driver.manage().window().getSize().getHeight();
        return element_location_by_y < screen_size_by_y;
    }


    protected void swipeElementToTheLeft(String locator, String error_message) {
        RemoteWebElement carousel = (RemoteWebElement) waitForElementPresent(
                locator,
                error_message,
                10);
        driver.executeScript("gesture: swipe", ImmutableMap.of("elementId", carousel.getId(), "percentage", 50, "direction", "left"));
    }

    public void clickElementToTheRightUpperCorner(String locator, String error_message) {
        if (driver instanceof AppiumDriver) {
            WebElement element = this.waitForElementPresent(locator + "/..", error_message, 5); //going to parent element from locator
            int right_x = element.getLocation().getX();
            int upper_y = element.getLocation().getY();
            int lower_y = upper_y + element.getSize().getHeight();
            int middle_y = (upper_y + lower_y) / 2;
            int width = element.getSize().getWidth();

            int point_to_click_x = (right_x + width) - 3;
            int point_to_click_y = middle_y;

            TouchAction action = new TouchAction((AppiumDriver) driver);
           // action.tap(point_to_click_x, point_to_click_y).perform();
        } else {
            System.out.println("Method clickElementToTheRightUpperCorner() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }
    //another way of swiping to the left
//    protected void swipeElementToTheLeft(String locator, String error_message) {
    //if (driver instanceof AppiumDriver) {
//        WebElement element = waitForElementPresent(
//                locator,
//                error_message,
//                10);
//        int left_x = element.getLocation().getX();
//        int right_x = left_x + element.getSize().getWidth();
//        int upper_y = element.getLocation().getY();
//        int lower_y = upper_y + element.getSize().getHeight();
//        int middle_y = (upper_y + lower_y) / 2;
//
//        TouchAction action = new TouchAction(driver);
//        action.press(right_x, middle_y);
//        action.waitAction(300);
//
//        if (Platform.getInstance().isAndroid()) {
//            action.moveTo(left_x, middle_y);
//        } else {
//           int offset_x = (-1 * element.getSize().getWidth());
//           action.moveTo(offset_x, 0);
//        }
//        action.release();
//        action.perform();
//    } else {
        //  System.out.println("Method swipeElementToTheLeft() does nothing for platform " + Platform.getInstance().getPlatformVar());

   // }
    // }

        public int getAmountOfElements (String locator){
            By by = this.getLocatorByString(locator);
            List elements = driver.findElements(by);
            return elements.size();
        }
    public void assertElementNotPresent(String locator, String error_message)
    {
        int amount_of_elements = getAmountOfElements(locator);
        if (amount_of_elements > 0) {
            String default_message = "An element " + locator + " supposed to be not present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }

    public void assertElementPresent(String locator, String error_message)
    {
        int amount_of_elements = getAmountOfElements(locator);
        if (amount_of_elements <= 0) {
            String default_message = "Title " + locator + " is not present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }

    public String waitForElementAndGetAttribute(String locator, String attribute, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        return element.getAttribute(attribute);
    }
    public WebElement assertElementHasText(String locator, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.until(ExpectedConditions.textToBePresentInElement(By.xpath(locator), "Search Wikipedia"));
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath(locator))
        );
    }

    private By getLocatorByString(String locator_with_type)
    {
        String[] exploded_locator = locator_with_type.split(Pattern.quote(":"), 2);
        String by_type = exploded_locator[0];
        String locator = exploded_locator[1];

        if(by_type.equals("xpath")) {
            return By.xpath(locator);
        } else if(by_type.equals("id"))
        {
            return By.id(locator);
        } else if(by_type.equals("css"))
        {
            return By.cssSelector(locator);
        } else {
            throw new IllegalArgumentException("Cannot get type of locator. Locator: " + locator_with_type);
        }
    }
}
