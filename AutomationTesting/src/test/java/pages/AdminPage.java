package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.Element;

public class AdminPage extends BasePage {

    public Element UsernameField() {
        return $(By.id("username"));
    }

    public Element PasswordField() {
        return $(By.id("password"));
    }

    public Element LoginButton() {
        return $(By.cssSelector("button[type='submit']"));
    }


    public AdminPage(WebDriver driver) {
        super(driver);
    }

    public void login(String username, String password) {
        UsernameField().sendKeys(username);
        PasswordField().sendKeys(password);

        LoginButton().click();
    }

    @Override
    public boolean isLoaded() {
        return UsernameField().isDisplayed();
    }

}
