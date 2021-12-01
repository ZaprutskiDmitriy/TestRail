package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CasesPage extends BasePage {

    private static final By ADD_CASE_BUTTON = By.id("sidebar-cases-add");
    private static final By ALL_CASES = By.xpath("//div[@id='groupContent']//table/descendant::*[@class='title']");

    public CasesPage(WebDriver driver) {
        super(driver);
    }

    public void clickCreateCaseButton() {
        driver.findElement(ADD_CASE_BUTTON).click();
    }

    public boolean isCaseExist(String caseName) {
        List<WebElement> testCasesList = driver.findElements(ALL_CASES);
        boolean isCaseCreated = false;
        for (WebElement testCase : testCasesList) {
            if (testCase.getText().equals(caseName)) {
                isCaseCreated = true;
            }
        }
        return isCaseCreated;
    }
}