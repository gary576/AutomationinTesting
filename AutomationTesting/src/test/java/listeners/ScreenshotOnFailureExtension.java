package listeners;

import base.BaseTest;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

public class ScreenshotOnFailureExtension implements AfterTestExecutionCallback, BeforeTestExecutionCallback {

    private static final Logger logger = Logger.getLogger(ScreenshotOnFailureExtension.class.getName());

    @Override
    public void beforeTestExecution(ExtensionContext context) {
        logger.info("➡️ Starting test: " + context.getDisplayName());
    }

    @Override
    public void afterTestExecution(ExtensionContext context) {
        if (context.getExecutionException().isPresent()) {
            Object testInstance = context.getRequiredTestInstance();
            if (testInstance instanceof BaseTest baseTest) {
                WebDriver driver = baseTest.getDriver();
                if (driver != null) {
                    takeScreenshot(context, driver);
                }
            }
        }
    }

    private void takeScreenshot(ExtensionContext context, WebDriver driver) {
        try {
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String testName = context.getDisplayName().replaceAll("[^a-zA-Z0-9]", "_");
            File destFile = new File("screenshots/" + testName + "_" + timestamp + ".png");

            destFile.getParentFile().mkdirs();
            Files.copy(srcFile.toPath(), destFile.toPath());

            logger.info("📸 Screenshot saved: " + destFile.getAbsolutePath());
        } catch (IOException e) {
            logger.severe("❌ Failed to save screenshot: " + e.getMessage());
        }
    }
}
