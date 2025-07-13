package tests;

import base.BaseTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

public class NonFunctionalTest extends BaseTest {
    @Test
    public void testPageLoadPerformance() {
        long start = System.currentTimeMillis();
        getDriver().navigate().to("https://automationintesting.online");
        long duration = System.currentTimeMillis() - start;
        Assertions.assertThat(duration).isLessThan(5000);
    }

    @Test
    public void testResponsiveLayout() {
        getDriver().manage().window().setSize(new Dimension(375, 812)); // iPhone X
        Assertions.assertThat(getDriver().findElement(By.tagName("body")).isDisplayed()).isTrue();
    }
}
