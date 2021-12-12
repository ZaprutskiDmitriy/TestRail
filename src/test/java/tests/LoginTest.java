package tests;

import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {

    @TmsLink("25")
    @Test(description = "Check the login form with valid login and password values")
    public void loginWithValidCredentials() {
        loginPage.open();
        loginPage.login(USERNAME, PASSWORD);
        String title = dashboardPage.getHeader();
        assertEquals(title, "dashboard", "Home page was not opened");
    }

    @TmsLink("26")
    @Test(description = "Check the login form with an empty login field")
    public void loginWithEmptyLoginField() {
        loginPage.open();
        loginPage.login("", PASSWORD);
        String errorMessage = loginPage.getErrorLoginField();
        assertEquals(errorMessage, "Email/Login is required.", "Error text is not correct");
    }

    @TmsLink("27")
    @Test(description = "Check the login form with an empty password field")
    public void loginWithEmptyPasswordField() {
        loginPage.open();
        loginPage.login(USERNAME, "");
        String errorMessage = loginPage.getErrorPasswordField();
        assertEquals(errorMessage, "Password is required.", "Error text is not correct");
    }

    @TmsLink("28")
    @Test(description = "Check the login form with invalid login and password values")
    public void loginWithInvalidCredentials() {
        loginPage.open();
        loginPage.login("test@test.ru", "12345");
        String errorMessage = loginPage.getError();
        assertEquals(errorMessage, "Email/Login or Password is incorrect. Please try again.",
                "Error text is not correct");
    }

    @TmsLink("29")
    @Test(description = "Check logout function")
    public void logout() {
        loginPage.open();
        loginPage.login(USERNAME, PASSWORD);
        dashboardPage.logout();
        String title = loginPage.getTitle();
        assertEquals(title, "Log into Your Account", "Login page was not opened");
    }
}