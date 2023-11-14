package lib.UI;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import jdk.javadoc.internal.doclets.toolkit.util.DocFinder;
import lib.Platform;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;

import java.util.regex.Pattern;

public class MainPageObject {

    protected static String
            SKIP_ELEMENT="xpath://*[contains(@text,'Skip')]";

    protected RemoteWebDriver driver;
    public MainPageObject(RemoteWebDriver driver)
    {
        this.driver = driver;
    }

    @Step("Waiting for element present")
    public WebElement waitForElementPresent(String locator, String error_message, long timeoutInSeconds) {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }
    @Step("Waiting for element present")
    public WebElement waitForElementPresent(String locator, String error_message) {
        return waitForElementPresent(locator,error_message,5);
    }
    @Step("Waiting for element present and click")
    public WebElement waitForElementAndClick(String locator, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(locator,error_message,timeoutInSeconds);
        element.click();
        return element;
    }
    @Step("Waiting for element present and send keys '{value}'")
    public WebElement waitForElementAndSendKeys(String locator, String value, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(locator,error_message,timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }
    @Step("Waiting for element not present")
    public boolean waitForElementNotPresent(String locator, String error_message, long timeoutSeconds)
    {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver,timeoutSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }
    @Step("Check that element has text '{expected_result}'")
    public void assertElementHasText(String locator, String expected_result, String error_message, String assert_error)
    {
        WebElement element = waitForElementPresent(locator,error_message);
        String elementText = element.getAttribute("text");
        Assert.assertEquals(error_message,expected_result,elementText);
    }
    @Step("Waiting for element present and clear it")
    public WebElement waitForElementAndClear(String locator, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(locator,error_message,timeoutInSeconds);
        element.clear();
        return element;
    }

    @Step("Waiting for word '{value}' in element")
    public void waitForWordInElement(String externalLocator, String internalLocator, String error_message, String value) {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            if (Platform.getInstance().isAndroid()) {
                driver.hideKeyboard();
            }
        } else {
            System.out.println("Method waitForWordInElement does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
        By by_external = this.getLocatorByString(externalLocator);
        waitForElementPresent(externalLocator, error_message);
        WebElement element= driver.findElement(by_external);
        By by_internal = this.getLocatorByString(internalLocator);
        List<WebElement> titles= element.findElements(by_internal);
        boolean result = titles.stream().allMatch(selectText -> selectText.getText().toLowerCase().contains(value));
        Assert.assertTrue(error_message, result);
    }

    @Step("Swipe element to the left")
    public void swipeElementToLeft(String locator, String error_message)
    {
        if (driver instanceof AppiumDriver) {
            TouchAction touchAction = new TouchAction((AppiumDriver) driver);
            WebElement element = waitForElementPresent(
                    locator,
                    error_message,
                    10);

            int width = element.getSize().getWidth();
            int height = element.getSize().getHeight();

            int startX = element.getLocation().getX() + width;
            int startY = element.getLocation().getY() + (height / 2);
            int endX = element.getLocation().getX();
            int endY = startY;

            System.out.println(width);
            System.out.println(height);
            System.out.println(startX);
            System.out.println(startY);
            System.out.println(endX);
            System.out.println(endY);
            System.out.println(element.getLocation().getX());
            System.out.println(element.getLocation().getY());

            touchAction.press(PointOption.point(startX, startY))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                    .moveTo(PointOption.point(-100, startY))
                    .release()
                    .perform();
        } else {
            System.out.println("Method swipeElementToLeft does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    @Step("Check that element present")
    public void assertElementPresent (String locator, String error_message)
    {
        By by = this.getLocatorByString(locator);
        try {
            driver.findElement(by);
        } catch (NoSuchElementException e) {
            throw new AssertionError(error_message);
        }
    }

    @Step("Get amount of elements")
    public  int getAmountOfElements(String locator)
    {
        By by = this.getLocatorByString(locator);
        List elements = driver.findElements(by);
        return elements.size();
    }

    @Step("Get locator")
    private By getLocatorByString(String locator_with_type)
    {
        String[] exploded_locator = locator_with_type.split(Pattern.quote(":"),2);
        String by_type = exploded_locator[0];
        String locator = exploded_locator[1];

        if (by_type.equals("xpath")) {
            return By.xpath(locator);
        } else if (by_type.equals("id")) {
            return By.id(locator);
        } else if (by_type.equals("css")) {
            return By.cssSelector(locator);
        } else {
            throw new IllegalArgumentException("Cannot get type of locator. Locator " + locator);
        }
    }

    @Step("Click skip button")
    public void clickSkip()
    {
        this.waitForElementAndClick(SKIP_ELEMENT, "Cannot find button Skip",5);
    }

    public String takeScreenshot (String name)
    {
        TakesScreenshot ts = (TakesScreenshot) this.driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + "/" + name + "_screenshot.png";
        try {
            FileUtils.copyFile(source, new File(path));
            System.out.println("This screenshot was taken: " + path);
        } catch (Exception e) {
            System.out.println("Cannot take screenshot. Error: " + e.getMessage());
        }
        return path;
    }

    @Attachment
    public static byte[] screenshot(String path) {
        byte[] bytes = new byte[0];
        try {
            bytes = Files.readAllBytes(Paths.get(path));
        } catch (IOException e) {
            System.out.println("Cannot get bytes from screenshot. Error: " + e.getMessage());
        }
        return bytes;
    }
}
