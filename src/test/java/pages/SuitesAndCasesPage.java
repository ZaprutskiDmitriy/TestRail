package pages;

import io.qameta.allure.Step;
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
    String deleteSuiteIconLocator = "//span[contains(@id,'sectionName') and text()=\"%s\"]/parent::div/descendant::div[contains(@class,'icon-small-delete')]";
    String editSuiteIconLocator = "//span[contains(@id,'sectionName') and text()=\"%s\"]/parent::div/descendant::div[contains(@class,'icon-small-edit')]";
    String caseLocator = "//div[contains(@class,'grid-container')]/descendant::span[text()=\"%s\"]";
    String suiteLocator = "//div[contains(@class,'grid-container')]/descendant::span[contains(@id,'sectionName') and text()=\"%s\"]";

    public SuitesAndCasesPage(WebDriver driver) {
        super(driver);
    }

    public void clickCreateCaseButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(ADD_CASE_BUTTON));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("blockUI blockOverlay")));
        driver.findElement(ADD_CASE_BUTTON).click();
    }

    @Step("Checking the existence of the {typeOfTestSubject} '{caseOrSuiteName}'")
    public boolean isCaseOrSuiteExist(String caseOrSuiteName, String typeOfTestSubject) {
        By locator = null;
        if (typeOfTestSubject.equalsIgnoreCase("case")) {
            locator = ALL_CASES;
        } else if (typeOfTestSubject.equalsIgnoreCase("suite")) {
            locator = ALL_SUITES;
        }
        List<WebElement> testCasesOrSuitesList = driver.findElements(locator);
        boolean isCaseOrSuiteExist = false;
        for (WebElement testCaseOrSuite : testCasesOrSuitesList) {
            if (testCaseOrSuite.getText().equals(caseOrSuiteName)) {
                isCaseOrSuiteExist = true;
            }
        }
        return isCaseOrSuiteExist;
    }

    public void scroll(String targetLocator, String targetName) {
        WebElement targetTitle = driver.findElement(By.xpath(String.format(targetLocator, targetName)));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", targetTitle);
        ((JavascriptExecutor) driver).executeScript("scrollBy(0, -150)");
    }

    public void hover(String targetLocator, String targetName) {
        Actions actions = new Actions(driver);
        WebElement targetTitle = driver.findElement(By.xpath(String.format(targetLocator, targetName)));
        actions.moveToElement(targetTitle).build().perform();
    }

    public void clickDeleteOrEditIcon(String caseOrSuiteName, String action, String typeOfTestSubject) {
        String iconLocator = null;
        String targetForHoverLocator = null;
        if (typeOfTestSubject.equalsIgnoreCase("case")) {
            targetForHoverLocator = caseLocator;
        } else if (typeOfTestSubject.equalsIgnoreCase("suite")) {
            targetForHoverLocator = suiteLocator;
        }
        scroll(targetForHoverLocator, caseOrSuiteName);
        hover(targetForHoverLocator, caseOrSuiteName);
        if (action.equalsIgnoreCase("edit") && typeOfTestSubject.equalsIgnoreCase("case")) {
            iconLocator = editCaseIconLocator;
        } else if (action.equalsIgnoreCase("edit") && typeOfTestSubject.equalsIgnoreCase("suite")) {
            iconLocator = editSuiteIconLocator;
        } else if (action.equalsIgnoreCase("delete") && typeOfTestSubject.equalsIgnoreCase("case")) {
            iconLocator = deleteCaseIconLocator;
        } else if (action.equalsIgnoreCase("delete") && typeOfTestSubject.equalsIgnoreCase("suite")) {
            iconLocator = deleteSuiteIconLocator;
        }
        WebElement icon = driver.findElement(By.xpath(String.format(iconLocator, caseOrSuiteName)));
        wait.until(ExpectedConditions.elementToBeClickable(icon));
        icon.click();
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
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("[class='blockUI blockOverlay']")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(ADD_SUITE_BUTTON));
        wait.until(ExpectedConditions.elementToBeClickable(ADD_SUITE_BUTTON));
        driver.findElement(ADD_SUITE_BUTTON).click();
    }

    @Step("Creating suite with title '{suiteName}'")
    public void createSuite(String suiteName, String suiteDescription) {
        driver.findElement(SUITE_NAME).sendKeys(suiteName);
        driver.findElement(SUITE_DESCRIPTION).sendKeys(suiteDescription);
        driver.findElement(SUBMIT_SUITE_BUTTON).click();
    }

    public void openCaseTab() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("[class='blockUI blockOverlay']")));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(SUBMIT_SUITE_BUTTON));
        wait.until(ExpectedConditions.elementToBeClickable(CASE_TAB));
        driver.findElement(CASE_TAB).click();
    }

    public void confirmDeleteSuite() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(WARNING_MESSAGE_IN_CONFIRMATION_DELETE_SUITE_WINDOW));
        driver.findElement(DELETE_SUITE_CHECKBOX).click();
        driver.findElement(CONFIRM_DELETE_SUITE_BUTTON).click();
    }

    @Step("Changing a primary suite on suite with title '{newSuiteName}'")
    public void updateSuite(String newSuiteName, String newSuiteDescription) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(SUBMIT_SUITE_BUTTON));
        driver.findElement(SUITE_NAME).clear();
        driver.findElement(SUITE_NAME).sendKeys(newSuiteName);
        driver.findElement(SUITE_DESCRIPTION).clear();
        driver.findElement(SUITE_DESCRIPTION).sendKeys(newSuiteDescription);
        driver.findElement(SUBMIT_SUITE_BUTTON).click();
    }
}