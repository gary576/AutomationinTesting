package tests.HomePage;

import base.BaseTest;
import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;

public class HomePageTest extends BaseTest {

    private static final Logger logger = Logger.getLogger(HomePageTest.class.getName());

    @Test
    public void testHomepageTitle() {
        logger.info("Starting testHomepageTitle");
        String title = driver.getTitle();
        logger.info("Page title: " + title);
        assertThat(title).containsIgnoringCase("Rest");
        logger.info("Completed testHomepageTitle successfully");
    }
}
