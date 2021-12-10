package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;
import pages.*;
import utils.PropertyReader;

import java.util.concurrent.TimeUnit;

public class BaseTest extends ApiBaseTest {

    public static final String USERNAME = System.getenv().getOrDefault("TESTRAIL_USER",
            PropertyReader.getProperty("testrail.user"));
    public static final String PASSWORD = System.getenv().getOrDefault("TESTRAIL_PASS",
            PropertyReader.getProperty("testrail.pass"));
    WebDriver driver;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    ProjectCreationPage projectCreationPage;
    ProjectPage projectPage;
    TestCasesTab testCasesTab;
    CaseCreationPage caseCreationPage;
    AdministrationPage administrationPage;
    CaseDetailsPage caseDetailsPage;

    @Parameters({"browser", "headless"})
    @BeforeMethod(description = "Opening browser")
    public void setUp(@Optional("firefox") String browser, @Optional("not headless") String headless, ITestContext context) {
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions chromeOptions = new ChromeOptions();
            if (headless.equalsIgnoreCase("headless")) {
                chromeOptions.addArguments("--headless");
            }
            driver = new ChromeDriver(chromeOptions);
        } else if (browser.equalsIgnoreCase("opera")) {
            WebDriverManager.operadriver().setup();
            driver = new OperaDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            if (headless.equalsIgnoreCase("headless")) {
                firefoxOptions.addArguments("--headless");
            }
            driver = new FirefoxDriver(firefoxOptions);
        }
        context.setAttribute("driver", driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        projectCreationPage = new ProjectCreationPage(driver);
        projectPage = new ProjectPage(driver);
        testCasesTab = new TestCasesTab(driver);
        caseCreationPage = new CaseCreationPage(driver);
        administrationPage = new AdministrationPage(driver);
        caseDetailsPage = new CaseDetailsPage(driver);
    }

    @AfterMethod(description = "Closing browser", alwaysRun = true)
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}