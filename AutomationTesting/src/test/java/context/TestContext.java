package context;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.DriverType;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

public class TestContext {

    private static final String GRID_URL = System.getProperty("SELENIUM_GRID_URL", System.getenv("SELENIUM_GRID_URL"));
    private static final Logger logger = Logger.getLogger(TestContext.class.getName());

    protected WebDriver initializeDriver(DriverType type) {
        boolean useGrid = GRID_URL != null && !GRID_URL.isBlank();
        logger.info("Initializing WebDriver for " + type + " | Use grid: " + useGrid);

        try {
            return switch (type) {
                case FIREFOX -> useGrid ? createRemoteDriver(new FirefoxOptions()) : createLocalFirefoxDriver();
                case EDGE -> useGrid ? createRemoteDriver(new EdgeOptions()) : createLocalEdgeDriver();
                case CHROME -> useGrid ? createRemoteDriver(new ChromeOptions()) : createLocalChromeDriver();
            };
        } catch (MalformedURLException e) {
            logger.severe("Grid URL is invalid: " + e.getMessage());
            throw new RuntimeException("Invalid Selenium Grid URL: " + GRID_URL, e);
        }
    }

    private WebDriver createRemoteDriver(org.openqa.selenium.Capabilities options) throws MalformedURLException {
        logger.info("Creating remote driver with options: " + options);
        return new RemoteWebDriver(new URL(GRID_URL), options);
    }

    private WebDriver createLocalChromeDriver() {
        WebDriverManager.chromedriver().setup();
        logger.info("Creating local ChromeDriver");
        return new ChromeDriver();
    }

    private WebDriver createLocalFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        logger.info("Creating local FirefoxDriver");
        return new FirefoxDriver();
    }

    private WebDriver createLocalEdgeDriver() {
        WebDriverManager.edgedriver().setup();
        logger.info("Creating local EdgeDriver");
        return new EdgeDriver();
    }
}
