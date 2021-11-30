package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class DashboardPage extends BasePage {

    private static final By TITLE = By.id("navigation-dashboard");
    private static final By USER_MENU = By.xpath("//*[@id='navigation-user']/descendant::span[@class='caret']");
    private static final By LOGOUT_BUTTON = By.id("navigation-user-logout");
    private static final By ADD_PROJECT_BUTTON = By.id("sidebar-projects-add");
    private static final By ALL_PROJECTS = By.cssSelector(".summary-title");
    private String projectLocator = "//*[contains(text(),'%s')]";

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(BASE_URL + "index.php?/dashboard");
    }

    public String getHeader() {
        return driver.findElement(TITLE).getText().toLowerCase();
    }

    public void logout() {
        driver.findElement(USER_MENU).click();
        driver.findElement(LOGOUT_BUTTON).click();
    }

    public void createProject() {
        driver.findElement(ADD_PROJECT_BUTTON).click();
    }

    public boolean isProjectExist(String projectName) {
        List<WebElement> projectsList = driver.findElements(ALL_PROJECTS);
        boolean isProjectCreated = false;
        for (WebElement project : projectsList) {
            if (project.getText().equals(projectName)) {
                isProjectCreated = true;
            }
        }
        return isProjectCreated;
    }

    public void openProject(String projectName) {
        driver.findElement(By.xpath(String.format(projectLocator, projectName))).click();
    }
}