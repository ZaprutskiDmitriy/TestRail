package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class ProjectPage extends BasePage {

    private static final By CASE_TAB = By.id("navigation-suites");

    public ProjectPage(WebDriver driver) {
        super(driver);
    }

    public void openCaseTab() {
        log.info("Opening page containing Sections and Cases");
        driver.findElement(CASE_TAB).click();
    }
}