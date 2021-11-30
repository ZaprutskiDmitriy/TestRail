package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {

    @Test
    public void loginWithValidCredentionals() {
        loginPage.open();
        loginPage.login(USERNAME, PASSWORD);
        String title = dashboardPage.getHeader();
        assertEquals(title, "dashboard", "Home page was not opened");
    }

    @Test
    public void loginWithEmptyLoginField() {
        loginPage.open();
        loginPage.login("", PASSWORD);
        String errorMessage = loginPage.getErrorLoginField();
        assertEquals(errorMessage, "Email/Login is required.", "Error text is not correct");
    }

    @Test
    public void loginWithEmptyPasswordField() {
        loginPage.open();
        loginPage.login(USERNAME, "");
        String errorMessage = loginPage.getErrorPasswordField();
        assertEquals(errorMessage, "Password is required.", "Error text is not correct");
    }

    @Test
    public void loginWithInvalidCredentionals() {
        loginPage.open();
        loginPage.login("test@test.ru", "12345");
        String errorMessage = loginPage.getError();
        assertEquals(errorMessage, "Email/Login or Password is incorrect. Please try again.",
                "Error text is not correct");
    }

    @Test
    public void logout() {
        loginPage.open();
        loginPage.login(USERNAME, PASSWORD);
        dashboardPage.logout();
        String title = loginPage.getTitle();
        assertEquals(title, "Log into Your Account", "Login page was not opened");
    }
}