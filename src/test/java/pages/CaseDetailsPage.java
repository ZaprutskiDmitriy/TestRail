package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CaseDetailsPage extends BasePage {

    private static final By CASE_SECTION = By.id("navigation-cases-section");

    public CaseDetailsPage(WebDriver driver) {
        super(driver);
    }

    public void openCaseSection() {
        driver.findElement(CASE_SECTION).click();
    }

    public void isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(CASE_SECTION));
    }
}