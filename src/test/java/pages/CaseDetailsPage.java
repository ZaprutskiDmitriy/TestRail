package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class CaseDetailsPage extends BasePage {

    private static final By CASE_SECTION = By.id("navigation-cases-section");

    public CaseDetailsPage(WebDriver driver) {
        super(driver);
    }

    public void openCaseSection() {
        log.info("Opening page containing Sections and Cases");
        driver.findElement(CASE_SECTION).click();
    }

    public void isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(CASE_SECTION));
    }
}