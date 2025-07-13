package tests.HomePage;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import pages.SingleRoomPage;

import java.time.LocalDate;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;

public class CheckAvailability extends BaseTest {

    private static final Logger logger = Logger.getLogger(HomePageTest.class.getName());

    @Test
    public void CheckAvailabilityTest() {
        HomePage homePage = new HomePage(getDriver());
        logger.info("Starting CheckAvailabilityTest");
        assertThat(homePage.isLoaded()).as("Homepage should load first").isTrue();
        logger.info("Home Page is loaded");
        homePage.CheckIn().moveToElement();
        homePage.setCheckInDate(LocalDate.of(2026,3,15));
        logger.info("Input Check in date");
        homePage.CheckAvailabilityButton().moveToElement();
        homePage.CheckAvailabilityButton().click();
        logger.info("Click Check Availability Button");
        homePage.SingleBookNow().isDisplayed();
        logger.info("Single Room Book Now should be displayed");
        homePage.SingleBookNow().moveToElement();
        homePage.SingleBookNow().click();
        logger.info("Click Book Now on Single Room");
        SingleRoomPage singleRoomPage = new SingleRoomPage(getDriver());
        assertThat(singleRoomPage.isLoaded()).as("Single Room should be loaded").isTrue();
        logger.info("Single Room Page should be loaded");
        assertThat(singleRoomPage.Total().getText().replace("£", "")).isEqualTo(singleRoomPage.RoomCost().getText().replace("£", ""));
        logger.info("Assert Room Price is correct in Price Summary");
        int cleaningfee = Integer.parseInt(singleRoomPage.CleaningFee().getText().replace("£", ""));
        int servicefee = Integer.parseInt(singleRoomPage.ServiceFee().getText().replace("£", ""));
        int totalCost = Integer.parseInt(singleRoomPage.CostPerStay().getText().replace("£", ""));
        int roomCost = Integer.parseInt(singleRoomPage.Total().getText().replace("£", ""));
        assertThat(cleaningfee + servicefee + roomCost).isEqualTo(totalCost);
        logger.info("Assert Price per Stay is correct");
        singleRoomPage.ReserveNow().moveToElement();
        singleRoomPage.ReserveNow().click();
        logger.info("Click Reserve Now");
        assertThat(singleRoomPage.FirstName().isDisplayed()).as("Firstname field is displayed").isTrue();
        assertThat(singleRoomPage.LastName().isDisplayed()).as("Lastname field is displayed").isTrue();
        assertThat(singleRoomPage.Email().isDisplayed()).as("Email field is displayed").isTrue();
        assertThat(singleRoomPage.Phone().isDisplayed()).as("Phone field is displayed").isTrue();
        logger.info("Assert Reserve Details fields are displayed");
        singleRoomPage.FirstName().sendKeys("Automation");
        logger.info("Input First Name in Firstname field");
        singleRoomPage.LastName().sendKeys("Test");
        logger.info("Input Last Name in Lastname field");
        singleRoomPage.Email().sendKeys("Automation@test.co.uk");
        logger.info("Input Email Address in Email field");
        singleRoomPage.Phone().sendKeys("07123456789");
        logger.info("Input Phone Number in Phone field");
        singleRoomPage.ReserveNow().moveToElement();
        singleRoomPage.ReserveNow().click();
        logger.info("Click Reserve button");
        assertThat(singleRoomPage.BookingResult().isDisplayed()).as("Booking Result is displayed").isTrue();
        assertThat(singleRoomPage.BookingResult().getText()).as("Booking Confirmed should be displayed").isEqualTo("Booking Confirmed");
        logger.info("Assert Booking Result is displayed and Booking confirmed");
    }
}
