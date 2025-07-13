package tests.AdminPage;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import pages.AdminPage;
import pages.HomePage;

import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;

public class AdminPageFieldValidationTest extends BaseTest {

    private static final Logger logger = Logger.getLogger(AdminPageFieldValidationTest.class.getName());

    @Test
    public void testAdminLoginFieldsAreDisplayedAndEditable() {
        HomePage homePage = new HomePage(getDriver());
        assertThat(homePage.isLoaded()).as("Homepage should load first").isTrue();
        homePage.navigateToAdmin();

        AdminPage adminPage = new AdminPage(getDriver());
        assertThat(adminPage.isLoaded()).as("Admin page should load").isTrue();

        assertThat(adminPage.UsernameField().isDisplayed()).as("Username field should be visible").isTrue();
        assertThat(adminPage.UsernameField().isEnabled()).as("Username field should be enabled").isTrue();

        assertThat(adminPage.PasswordField().isDisplayed()).as("Password field should be visible").isTrue();
        assertThat(adminPage.PasswordField().isEnabled()).as("Password field should be enabled").isTrue();

        assertThat(adminPage.LoginButton().isDisplayed()).as("Login button should be visible").isTrue();
        assertThat(adminPage.LoginButton().isEnabled()).as("Login button should be enabled").isTrue();
    }
}
