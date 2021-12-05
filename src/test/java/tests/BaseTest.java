package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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
    WebDriver driver;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    ProjectCreationPage projectCreationPage;
    ProjectPage projectPage;
    SuitesAndCasesPage suitesAndCasesPage;
    CaseCreationPage caseCreationPage;
    AdministrationPage administrationPage;
    CaseDetailsPage caseDetailsPage;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
//        TODO PARALLELING TESTS FOR MULTIPLE BROWSERS
//        WebDriverManager.firefoxdriver().setup();
//        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        projectCreationPage = new ProjectCreationPage(driver);
        projectPage = new ProjectPage(driver);
        suitesAndCasesPage = new SuitesAndCasesPage(driver);
        caseCreationPage = new CaseCreationPage(driver);
        administrationPage = new AdministrationPage(driver);
        caseDetailsPage = new CaseDetailsPage(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void close() {
        driver.quit();
    }
}