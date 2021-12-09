package tests;

import com.github.javafaker.Faker;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class SuiteTest extends BaseTest {

    static Faker faker = new Faker();

    @Test(description = "Check if the test suite can be created")
    public void suiteShouldBeCreated() {
        String suiteName = faker.country().name() + faker.number().randomDigit();
        String suiteDescription = faker.country().capital() + faker.number().randomDigit();

        loginPage.open();
        loginPage.login(USERNAME, PASSWORD);
        dashboardPage.openProject("TestProject");
        projectPage.openCaseTab();
        testCasesTab.isPageOpened();
        testCasesTab.clickCreateSuiteButton();
        testCasesTab.createSuite(suiteName, suiteDescription);
        testCasesTab.openCaseTab();
        assertTrue(testCasesTab.isSuiteExist(suiteName), "Suite was not created");
    }

    @Test(description = "Check if the test suite can be updated")
    public void suiteShouldBeUpdated() {
        String suiteName = faker.country().name() + faker.number().randomDigit();
        String newSuiteName = faker.currency().name() + faker.number().randomDigit();
        String suiteDescription = faker.country().capital();
        String newSuiteDescription = faker.currency().code();

        loginPage.open();
        loginPage.login(USERNAME, PASSWORD);
        dashboardPage.openProject("TestProject");
        projectPage.openCaseTab();
        testCasesTab.isPageOpened();
        testCasesTab.clickCreateSuiteButton();
        testCasesTab.createSuite(suiteName, suiteDescription);
        testCasesTab.clickEditSuite(suiteName);
        testCasesTab.updateSuite(newSuiteName, newSuiteDescription);
        assertTrue(testCasesTab.isSuiteExist(newSuiteName), "Suite was not updated");
    }

    @Test(description = "Check if the test suite can be deleted")
    public void suiteShouldBeDeleted() {
        String suiteName = faker.country().name() + faker.number().randomDigit();
        String suiteDescription = faker.country().capital();

        loginPage.open();
        loginPage.login(USERNAME, PASSWORD);
        dashboardPage.openProject("TestProject");
        projectPage.openCaseTab();
        testCasesTab.isPageOpened();
        testCasesTab.clickCreateSuiteButton();
        testCasesTab.createSuite(suiteName, suiteDescription);
        testCasesTab.clickDeleteSuite(suiteName);
        testCasesTab.confirmDeleteSuite();
        testCasesTab.openCaseTab();
        assertFalse(testCasesTab.isSuiteExist(suiteName), "Suite has not been deleted");
    }
}