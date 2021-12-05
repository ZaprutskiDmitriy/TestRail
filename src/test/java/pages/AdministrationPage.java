package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AdministrationPage extends BasePage {

    private static final By DELETE_CHECKBOX = By.xpath("//*[@id='deleteDialog']/descendant::input[@name='deleteCheckbox']");
    private static final By CONFIRM_BUTTON = By.xpath("//*[@id='deleteDialog']/descendant::a[contains(@class,'button-ok')]");
    private static final By PROJECTS_ON_NAVIGATION_BAR = By.id("navigation-sub-projects");
    String deleteProjectIconLocator = "//*[contains(text(),\"%s\")]/ancestor::tr/descendant::div[contains(@class,'icon-small-delete')]";
    String editProjectIconLocator = "//*[contains(text(),\"%s\")]/ancestor::tr/descendant::div[contains(@class,'icon-small-edit')]";

    public AdministrationPage(WebDriver driver) {
        super(driver);
    }

    public void deleteProject(String projectName) {
        driver.findElement(By.xpath(String.format(deleteProjectIconLocator, projectName))).click();
    }

    public void editProject(String projectName) {
        driver.findElement(By.xpath(String.format(editProjectIconLocator, projectName))).click();
    }

    public void confirmDeleteProject() {
        driver.findElement(DELETE_CHECKBOX).click();
        driver.findElement(CONFIRM_BUTTON).click();
    }

    public void isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(PROJECTS_ON_NAVIGATION_BAR));
    }
}