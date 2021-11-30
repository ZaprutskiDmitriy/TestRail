package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage{

    private static final By USER_NAME = By.name("name");
    private static final By PASSWORD = By.name("password");
    private static final By LOGIN_BUTTON = By.id("button_primary");
    private static final By ERROR_LOGIN = By.xpath("//input[@name='name']/ancestor::div[@class='form-group']/following-sibling::div");
    private static final By ERROR_PASSWORD = By.xpath("//input[@name='password']/ancestor::div[@class='form-group']/following-sibling::div/descendant::div[@class='loginpage-message-image loginpage-message ']");
    private static final By ERROR_MESSAGE = By.cssSelector(".error-text");
    private static final By TITLE = By.cssSelector(".loginpage-login-text");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(BASE_URL);
    }

    public void login(String user, String password) {
        driver.findElement(USER_NAME).sendKeys(user);
        driver.findElement(PASSWORD).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
    }

    public String getErrorLoginField() {
        return driver.findElement(ERROR_LOGIN).getText();
    }

    public String getErrorPasswordField() {
        return driver.findElement(ERROR_PASSWORD).getText();
    }

    public String getError() {
        return driver.findElement(ERROR_MESSAGE).getText();
    }

    public String getTitle() {
        return driver.findElement(TITLE).getText();
    }
}