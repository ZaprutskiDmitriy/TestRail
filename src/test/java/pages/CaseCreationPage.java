package pages;

import models.TestCase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import wrappers.Dropdown;

public class CaseCreationPage extends BasePage {

    private static final By TITLE = By.name("title");
    private static final By ESTIMATE = By.name("estimate");
    private static final By REFERENCES = By.name("refs");
    private static final By PRECONDITIONS = By.id("custom_preconds_display");
    private static final By STEPS = By.id("custom_steps_display");
    private static final By EXPECTED_RESULT = By.id("custom_expected_display");
    private static final By ADD_CASE_BUTTON = By.id("accept");

    public CaseCreationPage(WebDriver driver) {
        super(driver);
    }

    public void createCase(TestCase testCase) {
        driver.findElement(TITLE).sendKeys(testCase.getTitle());
        new Dropdown(driver, "Section").select(testCase.getSection());
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("[class='blockUI blockOverlay']")));
        new Dropdown(driver, "Template").select(testCase.getTemplate());
        new Dropdown(driver, "Type").select(testCase.getType());
        driver.findElement(ESTIMATE).sendKeys(testCase.getEstimate());
        driver.findElement(REFERENCES).sendKeys(testCase.getReferences());
        new Dropdown(driver, "Priority").select(testCase.getPriority());
        new Dropdown(driver, "Automation Type").select(testCase.getAutomationType());
        driver.findElement(PRECONDITIONS).sendKeys(testCase.getPreconditions());
        driver.findElement(STEPS).sendKeys(testCase.getSteps());
        driver.findElement(EXPECTED_RESULT).sendKeys(testCase.getExpectedResults());
    }

    public void updateCase(TestCase testCase) {
        driver.findElement(TITLE).clear();
        driver.findElement(TITLE).sendKeys(testCase.getTitle());
        new Dropdown(driver, "Section").select(testCase.getSection());
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("[class='blockUI blockOverlay']")));
        new Dropdown(driver, "Template").select(testCase.getTemplate());
        new Dropdown(driver, "Type").select(testCase.getType());
        driver.findElement(ESTIMATE).clear();
        driver.findElement(ESTIMATE).sendKeys(testCase.getEstimate());
        driver.findElement(REFERENCES).clear();
        driver.findElement(REFERENCES).sendKeys(testCase.getReferences());
        new Dropdown(driver, "Priority").select(testCase.getPriority());
        new Dropdown(driver, "Automation Type").select(testCase.getAutomationType());
        driver.findElement(PRECONDITIONS).clear();
        driver.findElement(PRECONDITIONS).sendKeys(testCase.getPreconditions());
        driver.findElement(STEPS).clear();
        driver.findElement(STEPS).sendKeys(testCase.getSteps());
        driver.findElement(EXPECTED_RESULT).clear();
        driver.findElement(EXPECTED_RESULT).sendKeys(testCase.getExpectedResults());
    }

    public void clickSaveTestCaseButton() {
        driver.findElement(ADD_CASE_BUTTON).click();
    }

    public void isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(ESTIMATE));
    }
}