package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

@Log4j2
public class DashboardPage extends BasePage {

    private static final By TITLE = By.id("navigation-dashboard");
    private static final By USER_MENU = By.xpath("//*[@id='navigation-user']/descendant::span[@class='caret']");
    private static final By LOGOUT_BUTTON = By.id("navigation-user-logout");
    private static final By ADD_PROJECT_BUTTON = By.id("sidebar-projects-add");
    private static final By ALL_PROJECTS = By.cssSelector(".summary-title");
    private String projectLocator = "//div[@id='content_container']/descendant::a[text()=\"%s\"]";

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        log.info("Opening Dashboard page");
        driver.get(BASE_URL + "index.php?/dashboard");
    }

    public String getHeader() {
        return driver.findElement(TITLE).getText().toLowerCase();
    }

    @Step("Logout")
    public void logout() {
        log.info("Click 'Logout' button");
        driver.findElement(USER_MENU).click();
        driver.findElement(LOGOUT_BUTTON).click();
    }

    public void clickCreateProjectButton() {
        log.info("Click 'Add project' button");
        driver.findElement(ADD_PROJECT_BUTTON).click();
    }

    @Step("Checking the existence of the project '{projectName}'")
    public boolean isProjectExist(String projectName) {
        log.info("Checking the existence of the project '{}'", projectName);
        List<WebElement> projectsList = driver.findElements(ALL_PROJECTS);
        boolean isProjectExist = false;
        for (WebElement project : projectsList) {
            if (project.getText().equals(projectName)) {
                isProjectExist = true;
            }
        }
        return isProjectExist;
    }

    public void openProject(String projectName) {
        log.info("Opening project '{}'", projectName);
        driver.findElement(By.xpath(String.format(projectLocator, projectName))).click();
    }
}