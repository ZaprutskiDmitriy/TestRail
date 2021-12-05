package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProjectCreationPage extends BasePage {

    private static final By PROJECT_NAME = By.name("name");
    private static final By PROJECT_ANNOUNCEMENT = By.name("announcement");
    private static final By ADD_PROJECT_BUTTON = By.id("accept");

    public ProjectCreationPage(WebDriver driver) {
        super(driver);
    }

    private void selectType(Type type) {
        String selectType = "suite_mode_%s";
        if (Type.SINGLE.equals(type)) {
            driver.findElement(By.id(String.format(selectType, "single"))).click();
        } else if (Type.SINGLE_WITH_BASELINE.equals(type)) {
            driver.findElement(By.id(String.format(selectType, "single_baseline"))).click();
        } else if (Type.MULTIPLY.equals(type)) {
            driver.findElement(By.id(String.format(selectType, "multi"))).click();
        }
    }

    public void createProject(String projectName, String projectAnnouncement, Type type) {
        driver.findElement(PROJECT_NAME).sendKeys(projectName);
        driver.findElement(PROJECT_ANNOUNCEMENT).sendKeys(projectAnnouncement);
        selectType(type);
        driver.findElement(ADD_PROJECT_BUTTON).click();
    }

    public void updateProject(String projectName, String projectAnnouncement, Type type) {
        driver.findElement(PROJECT_NAME).clear();
        driver.findElement(PROJECT_NAME).sendKeys(projectName);
        driver.findElement(PROJECT_ANNOUNCEMENT).clear();
        driver.findElement(PROJECT_ANNOUNCEMENT).sendKeys(projectAnnouncement);
        selectType(type);
        driver.findElement(ADD_PROJECT_BUTTON).click();
    }

    public enum Type {
        SINGLE, SINGLE_WITH_BASELINE, MULTIPLY
    }
}