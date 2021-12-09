package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class TestCasesTab extends BasePage {

    private static final By ADD_CASE_BUTTON = By.id("sidebar-cases-add");
    private static final By ALL_CASES = By.xpath("//div[@id='groupContent']//table/descendant::*[@class='title']");
    private static final By DELETE_PERMANENTLY_BUTTON = By.xpath("//*[contains(@class,'ui-dialog')]/descendant::a[contains(text(),'Delete Permanently')]");
    private static final By REPEAT_DELETE_PERMANENTLY_BUTTON = By.xpath("//*[@id='casesDeletionConfirmationDialog']/descendant::a[contains(text(),'Delete Permanently')]");
    private static final By PAGE_TITLE = By.xpath("//*[@id='content-header']/descendant::div[contains(text(),'Test Cases')]");
    private static final By ADD_SUITE_BUTTON = By.id("addSection");
    private static final By ADD_SUITE_BUTTON_FOR_PROJECT_WITHOUT_SUITES = By.id("addSectionInline");
    private static final By SUITE_NAME = By.name("editSectionName");
    private static final By SUITE_DESCRIPTION = By.id("editSectionDescription_display");
    private static final By SUBMIT_SUITE_BUTTON = By.id("editSectionSubmit");
    private static final By ALL_SUITES = By.xpath("//div[contains(@class,'grid-container')]/descendant::span[contains(@id,'sectionName')]");
    private static final By CASE_TAB = By.id("navigation-suites");
    private static final By DELETE_SUITE_CHECKBOX = By.xpath("//*[@id='deleteDialog']/descendant::input[@name='deleteCheckbox']");
    private static final By CONFIRM_DELETE_SUITE_BUTTON = By.xpath("//*[@id='deleteDialog']/descendant::a[contains(@class,'button-ok')]");
    private static final By WARNING_MESSAGE_IN_CONFIRMATION_DELETE_SUITE_WINDOW = By.xpath("//*[@id='deleteDialog']/descendant::p[@class='dialog-extra text-delete']");
    private static final By BLOCK_WINDOW = By.cssSelector("[class='blockUI blockOverlay']");

    String deleteCaseIconLocator = "//div[contains(@class,'grid-container')]/descendant::span[text()=\"%s\"]/ancestor::tr/descendant::div[contains(@class,'icon-small-delete')]/ancestor::a";
    String editCaseIconLocator = "//div[contains(@class,'grid-container')]/descendant::span[text()=\"%s\"]/ancestor::tr/descendant::a[@class='editLink']";
    String deleteSuiteIconLocator = "//span[contains(@id,'sectionName') and text()=\"%s\"]/parent::div/descendant::div[contains(@class,'icon-small-delete')]";
    String editSuiteIconLocator = "//span[contains(@id,'sectionName') and text()=\"%s\"]/parent::div/descendant::div[contains(@class,'icon-small-edit')]";
    String caseLocator = "//div[contains(@class,'grid-container')]/descendant::span[text()=\"%s\"]";
    String suiteLocator = "//div[contains(@class,'grid-container')]/descendant::span[contains(@id,'sectionName') and text()=\"%s\"]";

    public TestCasesTab(WebDriver driver) {
        super(driver);
    }

    public void clickCreateCaseButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(ADD_CASE_BUTTON));
        waitDisappearBlockingWindow();
        driver.findElement(ADD_CASE_BUTTON).click();
    }

    private void waitDisappearBlockingWindow() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(BLOCK_WINDOW));
    }

    private boolean isEntityExist(String entityName, By entityLocator) {
        List<WebElement> entitysList = driver.findElements(entityLocator);
        boolean isEntityExist = false;
        for (WebElement entity : entitysList) {
            if (entity.getText().equals(entityName)) {
                isEntityExist = true;
            }
        }
        return isEntityExist;
    }

    @Step("Checking the existence of the case with title '{caseName}'")
    public boolean isCaseExist(String caseName) {
        return isEntityExist(caseName, ALL_CASES);
    }

    @Step("Checking the existence of the suite with title '{suiteName}'")
    public boolean isSuiteExist(String suiteName) {
        return isEntityExist(suiteName, ALL_SUITES);
    }

    private void scroll(String targetLocator, String targetName) {
        WebElement targetTitle = driver.findElement(By.xpath(String.format(targetLocator, targetName)));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", targetTitle);
        ((JavascriptExecutor) driver).executeScript("scrollBy(0, -200)");
    }

    private void hover(String targetLocator, String targetName) {
        Actions actions = new Actions(driver);
        WebElement targetTitle = driver.findElement(By.xpath(String.format(targetLocator, targetName)));
        actions.moveToElement(targetTitle).build().perform();
    }

    private void clickIcon(String entityName, String entityTitleLocator, String iconActionLocator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(caseLocator, entityName))));
        scroll(entityTitleLocator, entityName);
        hover(entityTitleLocator, entityName);
        WebElement icon = driver.findElement(By.xpath(String.format(iconActionLocator, entityName)));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(iconActionLocator, entityName))));
        icon.click();
    }

    @Step("Edit case with title '{caseName}'")
    public void clickEditCase(String caseName) {
        clickIcon(caseName, caseLocator, editCaseIconLocator);
    }

    @Step("Delete case with title '{caseName}'")
    public void clickDeleteCase(String caseName) {
        clickIcon(caseName, caseLocator, deleteCaseIconLocator);
    }

    @Step("Edit suite with title '{suiteName}'")
    public void clickEditSuite(String suiteName) {
        clickIcon(suiteName, suiteLocator, editSuiteIconLocator);
    }

    @Step("Delete suite with title '{suiteName}'")
    public void clickDeleteSuite(String suiteName) {
        clickIcon(suiteName, suiteLocator, deleteSuiteIconLocator);
    }

    public void confirmDeleteCase() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(DELETE_PERMANENTLY_BUTTON));
        driver.findElement(DELETE_PERMANENTLY_BUTTON).click();
        driver.findElement(REPEAT_DELETE_PERMANENTLY_BUTTON).click();
    }

    public void isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(PAGE_TITLE));
    }

    public void clickCreateSuiteButton() {
        waitDisappearBlockingWindow();
        List<WebElement> testSuitesList = driver.findElements(ALL_SUITES);
        if (testSuitesList.isEmpty()) {
            wait.until(ExpectedConditions.elementToBeClickable(ADD_SUITE_BUTTON_FOR_PROJECT_WITHOUT_SUITES));
            driver.findElement(ADD_SUITE_BUTTON_FOR_PROJECT_WITHOUT_SUITES).click();
        } else {
            wait.until(ExpectedConditions.elementToBeClickable(ADD_SUITE_BUTTON));
            driver.findElement(ADD_SUITE_BUTTON).click();
        }
    }

    @Step("Create suite with title '{suiteName}'")
    public void createSuite(String suiteName, String suiteDescription) {
        waitDisappearBlockingWindow();
        do {
            driver.findElement(SUITE_NAME).sendKeys(suiteName);
            driver.findElement(SUITE_DESCRIPTION).sendKeys(suiteDescription);
            driver.findElement(SUBMIT_SUITE_BUTTON).click();
        } while (!driver.findElement(SUBMIT_SUITE_BUTTON).isEnabled());
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(suiteLocator, suiteName))));
    }

    public void openCaseTab() {
        waitDisappearBlockingWindow();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(SUBMIT_SUITE_BUTTON));
        wait.until(ExpectedConditions.elementToBeClickable(CASE_TAB));
        driver.findElement(CASE_TAB).click();
    }

    public void confirmDeleteSuite() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(WARNING_MESSAGE_IN_CONFIRMATION_DELETE_SUITE_WINDOW));
        driver.findElement(DELETE_SUITE_CHECKBOX).click();
        driver.findElement(CONFIRM_DELETE_SUITE_BUTTON).click();
    }

    @Step("Create new suite with title '{newSuiteName}'")
    public void updateSuite(String newSuiteName, String newSuiteDescription) {
        waitDisappearBlockingWindow();
        wait.until(ExpectedConditions.visibilityOfElementLocated(SUBMIT_SUITE_BUTTON));
        driver.findElement(SUITE_NAME).clear();
        driver.findElement(SUITE_NAME).sendKeys(newSuiteName);
        wait.until(ExpectedConditions.elementToBeClickable(SUBMIT_SUITE_BUTTON));
        driver.findElement(SUITE_DESCRIPTION).clear();
        driver.findElement(SUITE_DESCRIPTION).sendKeys(newSuiteDescription);
        driver.findElement(SUBMIT_SUITE_BUTTON).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(suiteLocator, newSuiteName))));
    }
}