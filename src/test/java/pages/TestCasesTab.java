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
    private static final By ADD_SECTION_BUTTON = By.id("addSection");
    private static final By ADD_SECTION_BUTTON_FOR_PROJECT_WITHOUT_SECTIONS = By.id("addSectionInline");
    private static final By SECTION_NAME = By.name("editSectionName");
    private static final By SECTION_DESCRIPTION = By.id("editSectionDescription_display");
    private static final By SUBMIT_SECTION_BUTTON = By.id("editSectionSubmit");
    private static final By ALL_SECTIONS = By.xpath("//div[contains(@class,'grid-container')]/descendant::span[contains(@id,'sectionName')]");
    private static final By CASE_TAB = By.id("navigation-suites");
    private static final By DELETE_SECTION_CHECKBOX = By.xpath("//*[@id='deleteDialog']/descendant::input[@name='deleteCheckbox']");
    private static final By CONFIRM_DELETE_SECTION_BUTTON = By.xpath("//*[@id='deleteDialog']/descendant::a[contains(@class,'button-ok')]");
    private static final By WARNING_MESSAGE_IN_CONFIRMATION_DELETE_SECTION_WINDOW = By.xpath("//*[@id='deleteDialog']/descendant::p[@class='dialog-extra text-delete']");
    private static final By BLOCK_WINDOW = By.cssSelector("[class='blockUI blockOverlay']");

    String deleteCaseIconLocator = "//div[contains(@class,'grid-container')]/descendant::span[text()=\"%s\"]/ancestor::tr/descendant::div[contains(@class,'icon-small-delete')]/ancestor::a";
    String editCaseIconLocator = "//div[contains(@class,'grid-container')]/descendant::span[text()=\"%s\"]/ancestor::tr/descendant::a[@class='editLink']";
    String deleteSectionIconLocator = "//span[contains(@id,'sectionName') and text()=\"%s\"]/parent::div/descendant::div[contains(@class,'icon-small-delete')]";
    String editSectionIconLocator = "//span[contains(@id,'sectionName') and text()=\"%s\"]/parent::div/descendant::div[contains(@class,'icon-small-edit')]";
    String caseLocator = "//div[contains(@class,'grid-container')]/descendant::span[text()=\"%s\"]";
    String sectionLocator = "//div[contains(@class,'grid-container')]/descendant::span[contains(@id,'sectionName') and text()=\"%s\"]";

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
        List<WebElement> entitiesList = driver.findElements(entityLocator);
        boolean isEntityExist = false;
        for (WebElement entity : entitiesList) {
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

    @Step("Checking the existence of the section with title '{sectionName}'")
    public boolean isSectionExist(String sectionName) {
        return isEntityExist(sectionName, ALL_SECTIONS);
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

    @Step("Editing case with title '{caseName}'")
    public void clickEditCase(String caseName) {
        clickIcon(caseName, caseLocator, editCaseIconLocator);
    }

    @Step("Deleting case with title '{caseName}'")
    public void clickDeleteCase(String caseName) {
        clickIcon(caseName, caseLocator, deleteCaseIconLocator);
    }

    @Step("Editing section with title '{sectionName}'")
    public void clickEditSection(String sectionName) {
        clickIcon(sectionName, sectionLocator, editSectionIconLocator);
    }

    @Step("Deleting section with title '{sectionName}'")
    public void clickDeleteSection(String sectionName) {
        clickIcon(sectionName, sectionLocator, deleteSectionIconLocator);
    }

    public void confirmDeleteCase() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(DELETE_PERMANENTLY_BUTTON));
        driver.findElement(DELETE_PERMANENTLY_BUTTON).click();
        driver.findElement(REPEAT_DELETE_PERMANENTLY_BUTTON).click();
    }

    public void isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(PAGE_TITLE));
    }

    public void clickCreateSectionButton() {
        waitDisappearBlockingWindow();
        List<WebElement> testSectionsList = driver.findElements(ALL_SECTIONS);
        if (testSectionsList.isEmpty()) {
            wait.until(ExpectedConditions.elementToBeClickable(ADD_SECTION_BUTTON_FOR_PROJECT_WITHOUT_SECTIONS));
            driver.findElement(ADD_SECTION_BUTTON_FOR_PROJECT_WITHOUT_SECTIONS).click();
        } else {
            wait.until(ExpectedConditions.elementToBeClickable(ADD_SECTION_BUTTON));
            driver.findElement(ADD_SECTION_BUTTON).click();
        }
    }

    @Step("Creating section with title '{sectionName}'")
    public void createSection(String sectionName, String sectionDescription) {
        waitDisappearBlockingWindow();
        do {
            driver.findElement(SECTION_NAME).sendKeys(sectionName);
            driver.findElement(SECTION_DESCRIPTION).sendKeys(sectionDescription);
            driver.findElement(SUBMIT_SECTION_BUTTON).click();
        } while (!driver.findElement(SUBMIT_SECTION_BUTTON).isEnabled());
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(sectionLocator, sectionName))));
    }

    public void openCaseTab() {
        waitDisappearBlockingWindow();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(SUBMIT_SECTION_BUTTON));
        wait.until(ExpectedConditions.elementToBeClickable(CASE_TAB));
        driver.findElement(CASE_TAB).click();
    }

    public void confirmDeleteSection() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(WARNING_MESSAGE_IN_CONFIRMATION_DELETE_SECTION_WINDOW));
        driver.findElement(DELETE_SECTION_CHECKBOX).click();
        driver.findElement(CONFIRM_DELETE_SECTION_BUTTON).click();
    }

    @Step("Creating new section with title '{newSectionName}'")
    public void updateSection(String newSectionName, String newSectionDescription) {
        waitDisappearBlockingWindow();
        wait.until(ExpectedConditions.visibilityOfElementLocated(SUBMIT_SECTION_BUTTON));
        driver.findElement(SECTION_NAME).clear();
        driver.findElement(SECTION_NAME).sendKeys(newSectionName);
        wait.until(ExpectedConditions.elementToBeClickable(SUBMIT_SECTION_BUTTON));
        driver.findElement(SECTION_DESCRIPTION).clear();
        driver.findElement(SECTION_DESCRIPTION).sendKeys(newSectionDescription);
        driver.findElement(SUBMIT_SECTION_BUTTON).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(sectionLocator, newSectionName))));
    }
}