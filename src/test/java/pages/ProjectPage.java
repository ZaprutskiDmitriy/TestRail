package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProjectPage extends BasePage {

    private static final By CASE_TAB = By.id("navigation-suites");

    public ProjectPage(WebDriver driver) {
        super(driver);
    }

    public void openCaseTab() {
        driver.findElement(CASE_TAB).click();
    }
}