package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Element;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

public class HomePage extends BasePage {

    public Element AdminLink (){
        return $(By.xpath("//a[normalize-space()='Admin'][1]"));
    }
    public Element BookNowButton(){
        return $(By.xpath("//a[@class='btn btn-primary btn-lg'][.='Book Now']"));
    }
    public Element HomePageTitle(){
        return $(By.xpath(".//a[contains(@class,'navbar-brand')]/span"));
    }

    public Element CheckIn(){
        return $(By.xpath("//label[@for='checkin']/..//input[@class='form-control']"));
    }
    public Element CheckOut(){
        return $(By.xpath("//label[@for='checkout']/..//input[@class='form-control']"));
    }
    public Element CheckAvailabilityButton(){
        return $(By.xpath("//button[.='Check Availability']"));
    }
    public Element Calendar(){
        return $(By.cssSelector("div[aria-label='Choose Date']"));
    }
    public Element SingleBookNow(){
        return $(By.xpath("//h5[.='Single']/../following-sibling::div//a[.='Book now']"));
    }

    public Element CalendarHeader() {
        return $(By.cssSelector(".react-datepicker__current-month"));

    }
    public Element CalendarNext(){
        return $(By.cssSelector("button[aria-label='Next Month']"));
    }
    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {

        return BookNowButton().isDisplayed();
    }

    public String getHeadingText() {
        return HomePageTitle().getText();
    }

    public void navigateToAdmin(){
        AdminLink().click();
    }

    public void setCheckInDate(LocalDate targetDate) {

        CheckIn().click();
        Calendar().waitUntilVisible();

        String day = String.valueOf(targetDate.getDayOfMonth());
        String month = targetDate.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        int year = targetDate.getYear();

        while (true) {

            if (CalendarHeader().getText().contains(month) && CalendarHeader().getText().contains(String.valueOf(year))) {
                break;
            } else {
                CalendarNext().click();
            }
        }

        List<WebElement> cells = driver.findElements(By.cssSelector(".rbc-day-bg"));
        for (WebElement cell : cells) {
            String ariaLabel = cell.getAttribute("aria-label"); // e.g. "Wednesday, August 20, 2025"
            if (ariaLabel != null && ariaLabel.contains(month) && ariaLabel.contains(day)) {
                cell.click();
                break;
            }
        }
    }
}

