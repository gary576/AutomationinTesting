package utils;

import base.BaseTest;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.logging.Logger;

public class Element {
    private final WebDriver driver;
    private final By locator;
    private static final Logger logger = Logger.getLogger(Element.class.getName());

    public Element(WebDriver driver, By locator) {
        this.driver = driver;
        this.locator = locator;
    }

    public void click() {
        try {
            logger.info("Clicking element: " + locator);
            waitUntilClickable().click();
        } catch (Exception e) {
            handleFailure("click", e);
        }
    }

    public void moveToElement() {
        try {
            logger.info("Moving to element: " + locator);
            WebElement element = waitUntilVisible();
            Actions actions = new Actions(driver);
            actions.moveToElement(element).perform();
        } catch (Exception e) {
            handleFailure("moveToElement", e);
        }
    }

    public void sendKeys(CharSequence... keys) {
        try {
            logger.info("Sending keys to element: " + locator);
            waitUntilVisible().sendKeys(keys);
        } catch (Exception e) {
            handleFailure("sendKeys", e);
        }
    }

    public String getText() {
        try {
            logger.info("Getting text from element: " + locator);
            return waitUntilVisible().getText();
        } catch (Exception e) {
            handleFailure("getText", e);
            return null;
        }
    }

    public void clear() {
        try {
            logger.info("Clearing element: " + locator);
            waitUntilVisible().clear();
        } catch (Exception e) {
            handleFailure("clear", e);
        }
    }

    public String getAttribute(String attribute) {
        try {
            logger.info("Getting attribute '" + attribute + "' from element: " + locator);
            return waitUntilVisible().getAttribute(attribute);
        } catch (Exception e) {
            handleFailure("getAttribute", e);
            return null;
        }
    }

    public boolean isDisplayed() {
        try {
            return waitUntilVisible().isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isEnabled() {
        try {
            return waitUntilVisible().isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSelected() {
        try {
            return waitUntilVisible().isSelected();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean exists() {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public WebElement waitUntilVisible() {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitUntilClickable() {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    private void handleFailure(String action, Exception e) {
        captureScreenshot(action);
        logger.severe("Failed to perform action '" + action + "' on element " + locator + ": " + e.getMessage());
        throw new RuntimeException(e);
    }

    private void captureScreenshot(String action) {
        if (!(driver instanceof TakesScreenshot)) return;
        try {
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String filename = "screenshots/element_failure_" + action + "_" + timestamp + ".png";
            File destFile = new File(filename);
            destFile.getParentFile().mkdirs();
            Files.copy(srcFile.toPath(), destFile.toPath());
            logger.info("Screenshot captured: " + filename);
        } catch (IOException ioe) {
            logger.severe("Screenshot capture failed: " + ioe.getMessage());
        }
    }
}
