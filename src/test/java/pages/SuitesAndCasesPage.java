package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class SuitesAndCasesPage extends BasePage {

    private static final By ADD_CASE_BUTTON = By.id("sidebar-cases-add");
    private static final By ALL_CASES = By.xpath("//div[@id='groupContent']//table/descendant::*[@class='title']");
    private static final By DELETE_PERMANENTLY_BUTTON = By.xpath("//*[contains(@class,'ui-dialog')]/descendant::a[contains(text(),'Delete Permanently')]");
    private static final By REPEAT_DELETE_PERMANENTLY_BUTTON = By.xpath("//*[@id='casesDeletionConfirmationDialog']/descendant::a[contains(text(),'Delete Permanently')]");
    private static final By PAGE_TITLE = By.xpath("//*[@id='content-header']/descendant::div[contains(text(),'Test Cases')]");
    private static final By ADD_SUITE_BUTTON = By.id("addSection");
    private static final By SUITE_NAME = By.name("editSectionName");
    private static final By SUITE_DESCRIPTION = By.id("editSectionDescription_display");
    private static final By SUBMIT_SUITE_BUTTON = By.id("editSectionSubmit");
    private static final By ALL_SUITES = By.xpath("//div[contains(@class,'grid-container')]/descendant::span[contains(@id,'sectionName')]");
    private static final By CASE_TAB = By.id("navigation-suites");
    private static final By DELETE_SUITE_CHECKBOX = By.xpath("//*[@id='deleteDialog']/descendant::input[@name='deleteCheckbox']");
    private static final By CONFIRM_DELETE_SUITE_BUTTON = By.xpath("//*[@id='deleteDialog']/descendant::a[contains(@class,'button-ok')]");
    private static final By WARNING_MESSAGE_IN_CONFIRMATION_DELETE_SUITE_WINDOW = By.xpath("//*[@id='deleteDialog']/descendant::p[@class='dialog-extra text-delete']");

    String deleteCaseIconLocator = "//div[contains(@class,'grid-container')]/descendant::span[text()=\"%s\"]/ancestor::tr/descendant::div[contains(@class,'icon-small-delete')]/ancestor::a";
    String editCaseIconLocator = "//div[contains(@class,'grid-container')]/descendant::span[text()=\"%s\"]/ancestor::tr/descendant::a[@class='editLink']";
    String deleteSuiteIconLocator = "//span[contains(@id,'sectionName') and contains(text(),\"%s\")]/parent::div/descendant::div[contains(@class,'icon-small-delete')]";
    String editSuiteIconLocator = "//span[contains(@id,'sectionName') and contains(text(),\"%s\")]/parent::div/descendant::div[contains(@class,'icon-small-edit')]";
    String caseLocator = "//div[contains(@class,'grid-container')]/descendant::span[text()=\"%s\"]";
    String suiteLocator = "//div[contains(@class,'grid-container')]/descendant::span[contains(@id,'sectionName') and contains(text(),\"%s\")]";

    public SuitesAndCasesPage(WebDriver driver) {
        super(driver);
    }

    public void clickCreateCaseButton() {
        driver.findElement(ADD_CASE_BUTTON).click();
    }

    public boolean isCaseExist(String caseName) {
        List<WebElement> testCasesList = driver.findElements(ALL_CASES);
        boolean isCaseExist = false;
        for (WebElement testCase : testCasesList) {
            if (testCase.getText().equals(caseName)) {
                isCaseExist = true;
            }
        }
        return isCaseExist;
    }

    public void deleteCase(String caseName) {
        Actions actions = new Actions(driver);
        WebElement scrolling = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(caseLocator, caseName))));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollTo(0, arguments[0].scrollHeight)", scrolling);
        WebElement caseTitle = driver.findElement(By.xpath(String.format(caseLocator, caseName)));
        actions.moveToElement(caseTitle).build().perform();
        driver.findElement(By.xpath(String.format(deleteCaseIconLocator, caseName))).click();
    }

    public void confirmDeleteCase() {
        driver.findElement(DELETE_PERMANENTLY_BUTTON).click();
        driver.findElement(REPEAT_DELETE_PERMANENTLY_BUTTON).click();
    }

    public void isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(PAGE_TITLE));
    }

    public void editCase(String caseName) {
        Actions actions = new Actions(driver);
        WebElement scrolling = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(caseLocator, caseName))));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollTo(0, arguments[0].scrollHeight)", scrolling);
        WebElement caseTitle = driver.findElement(By.xpath(String.format(caseLocator, caseName)));
        actions.moveToElement(caseTitle).build().perform();
        driver.findElement(By.xpath(String.format(editCaseIconLocator, caseName))).click();
    }

    public void clickCreateSuiteButton() {
        driver.findElement(ADD_SUITE_BUTTON).click();
    }

    public void createSuite(String suiteName, String suiteDescription) {
        driver.findElement(SUITE_NAME).sendKeys(suiteName);
        driver.findElement(SUITE_DESCRIPTION).sendKeys(suiteDescription);
        driver.findElement(SUBMIT_SUITE_BUTTON).click();
    }

    public boolean isSuiteExist(String suiteName) {
        List<WebElement> testSuitesList = driver.findElements(ALL_SUITES);
        boolean isSuiteExist = false;
        for (WebElement testSuite : testSuitesList) {
            if (testSuite.getText().equals(suiteName)) {
                isSuiteExist = true;
            }
        }
        return isSuiteExist;
    }

    public void openCaseTab() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(CASE_TAB));
        driver.findElement(CASE_TAB).click();
    }

    public void deleteSuite(String suiteName) {
        Actions actions = new Actions(driver);
        WebElement scrolling = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(suiteLocator, suiteName))));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollTo(0, arguments[0].scrollHeight)", scrolling);
        WebElement suiteTitle = driver.findElement(By.xpath(String.format(suiteLocator, suiteName)));
        actions.moveToElement(suiteTitle).build().perform();
        driver.findElement(By.xpath(String.format(deleteSuiteIconLocator, suiteName))).click();
    }

    public void confirmDeleteSuite() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(WARNING_MESSAGE_IN_CONFIRMATION_DELETE_SUITE_WINDOW));
        driver.findElement(DELETE_SUITE_CHECKBOX).click();
        driver.findElement(CONFIRM_DELETE_SUITE_BUTTON).click();
    }

    public void editSuite(String suiteName) {
        driver.findElement(By.xpath(String.format(editSuiteIconLocator, suiteName))).click();
    }

    public void updateSuite(String newSuiteName, String newSuiteDescription) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(SUBMIT_SUITE_BUTTON));
        driver.findElement(SUITE_NAME).clear();
        driver.findElement(SUITE_NAME).sendKeys(newSuiteName);
        driver.findElement(SUITE_DESCRIPTION).clear();
        driver.findElement(SUITE_DESCRIPTION).sendKeys(newSuiteDescription);
        driver.findElement(SUBMIT_SUITE_BUTTON).click();
    }

}