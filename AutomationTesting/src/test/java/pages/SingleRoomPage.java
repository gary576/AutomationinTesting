package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.Element;

public class SingleRoomPage extends BasePage {

    public Element Total() {
        return $(By.xpath("//span[@class='fs-2 fw-bold text-primary me-2']"));
    }

    public Element CleaningFee() {
        return $(By.xpath("//div[@class='d-flex justify-content-between mb-2']/span[.='Cleaning fee']/following-sibling::span"));
    }

    public Element ServiceFee() {
        return $(By.xpath("//div[@class='d-flex justify-content-between']/span[.='Service fee']/following-sibling::span"));
    }

    public Element ReserveNow() {
        return $(By.xpath("//button[normalize-space()='Reserve Now']"));
    }

    public Element RoomCost() {
        return $(By.xpath("//div[@class='d-flex justify-content-between mb-2']/span[.!='Cleaning fee']/following-sibling::span"));
    }

    public Element CostPerStay() {
        return $(By.xpath("//div[@class='d-flex justify-content-between fw-bold']/span[.='Total']/following-sibling::span"));
    }

    public Element Title() {
        return $(By.xpath("//h1[@class='fw-bold mb-2']"));
    }

    public Element FirstName() {
        return $(By.xpath("//input[@placeholder='Firstname']"));
    }
    public Element LastName() {
        return $(By.xpath("//input[@placeholder='Lastname']"));
    }
    public Element Email() {
        return $(By.xpath("//input[@placeholder='Email']"));
    }
    public Element Phone() {
        return $(By.xpath("//input[@placeholder='Phone']"));
    }
    public Element BookingResult(){
        return $(By.xpath("//h2[@class='card-title fs-4 fw-bold mb-3']"));
    }
    public SingleRoomPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isLoaded() {
        return Title().isDisplayed();
    }

}
