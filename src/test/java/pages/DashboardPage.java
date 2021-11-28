package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage extends BasePage{

    private static final By TITLE = By.id("navigation-dashboard");
    private static final By USER_MENU = By.xpath("//*[@id='navigation-user']/descendant::span[@class='caret']");
    private static final By LOGOUT = By.id("navigation-user-logout");

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(BASE_URL + "index.php?/dashboard");
    }

    public String getHeader() {
        return driver.findElement(TITLE).getText().toLowerCase();
    }

    public void logout() {
        driver.findElement(USER_MENU).click();
        driver.findElement(LOGOUT).click();
    }
}
