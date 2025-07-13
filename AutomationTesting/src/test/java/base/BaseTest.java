package base;

import context.TestContext;
import listeners.ScreenshotOnFailureExtension;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import utils.DriverType;

import java.util.logging.Logger;

@ExtendWith(ScreenshotOnFailureExtension.class)
public abstract class BaseTest extends TestContext {

    protected WebDriver driver;
    private static final Logger logger = Logger.getLogger(BaseTest.class.getName());

    @BeforeEach
    public void setUp() {
        logger.info("Setting up test with Chrome browser");
        this.driver = initializeDriver(DriverType.CHROME);
        driver.manage().window().maximize();
        driver.get("https://automationintesting.online");
        waitForHomePageToLoad();
    }

    @AfterEach
    public void tearDown() {
        logger.info("Tearing down test");
        if (driver != null) {
            driver.quit();
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    private void waitForHomePageToLoad() {
        String title = driver.getTitle();
        logger.info("Verifying homepage title: " + title);
        if (!title.toLowerCase().contains("rest")) {
            throw new IllegalStateException("Home page did not load correctly: " + title);
        }
    }
}
