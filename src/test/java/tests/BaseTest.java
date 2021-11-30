package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.*;
import utils.PropertyReader;

import java.util.concurrent.TimeUnit;

public class BaseTest {

    public static final String USERNAME = System.getenv().getOrDefault("TESTRAIL_USER",
            PropertyReader.getProperty("testrail.user"));
    public static final String PASSWORD = System.getenv().getOrDefault("TESTRAIL_PASS",
            PropertyReader.getProperty("testrail.pass"));
    public static final String TEST_PROJECT_NAME = "TestProject";
    WebDriver driver;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    ProjectCreationPage projectCreationPage;
    ProjectPage projectPage;
    CasesPage casesPage;
    CaseCreationPage caseCreationPage;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
//        WebDriverManager.firefoxdriver().setup();
//        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        projectCreationPage = new ProjectCreationPage(driver);
        projectPage = new ProjectPage(driver);
        casesPage = new CasesPage(driver);
        caseCreationPage = new CaseCreationPage(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void close() {
        driver.quit();
    }
}