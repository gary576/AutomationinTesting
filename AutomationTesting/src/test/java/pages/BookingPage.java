package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.Element;

public class BookingPage extends BasePage {

    public Element NameField() {
        return $(By.id("name"));
    }
    public Element EmailField() {
        return $(By.id("email"));
    }
    public Element PhoneField() {
        return $(By.id("phone"));
    }
    public Element BookButton() {
        return $(By.cssSelector("button[type='submit']"));
    }
    public Element BookingsSection() {
        return $(By.id("bookings"));
    }
    public Element BookingCard(){
        return $(By.className("booking"));
    }
    public Element BookingForm(){
        return $(By.id("form"));
    }

    public BookingPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        return BookingsSection().isDisplayed();
    }

    public boolean isBookingFormPresent() {
        return BookingForm().isDisplayed();
    }

    public void fillBookingForm(String name, String email, String phone) {
        NameField().sendKeys(name);
        EmailField().sendKeys(email);
        PhoneField().sendKeys(phone);
    }

    public void submitBooking() {
        BookButton().click();
    }
}
