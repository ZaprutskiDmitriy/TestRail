package tests;

import com.github.javafaker.Faker;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class SuiteTest extends BaseTest {

    static Faker faker = new Faker();

    @Test
    public void suiteShouldBeCreated() {
        String suiteName = faker.country().name();
        String suiteDescription = faker.country().capital();

        loginPage.open();
        loginPage.login(USERNAME, PASSWORD);
        dashboardPage.openProject("TestProject");
        projectPage.openCaseTab();
        suitesAndCasesPage.isPageOpened();
        suitesAndCasesPage.clickCreateSuiteButton();
        suitesAndCasesPage.createSuite(suiteName, suiteDescription);
        suitesAndCasesPage.openCaseTab();
        assertTrue(suitesAndCasesPage.isSuiteExist(suiteName), "Suite was not created");
    }

    @Test
    public void suiteShouldBeUpdated() {
        String suiteName = faker.country().name();
        String newSuiteName = faker.currency().name();
        String suiteDescription = faker.country().capital();
        String newSuiteDescription = faker.currency().code();

        loginPage.open();
        loginPage.login(USERNAME, PASSWORD);
        dashboardPage.openProject("TestProject");
        projectPage.openCaseTab();
        suitesAndCasesPage.isPageOpened();
        suitesAndCasesPage.clickCreateSuiteButton();
        suitesAndCasesPage.createSuite(suiteName, suiteDescription);
        suitesAndCasesPage.openCaseTab();
        suitesAndCasesPage.editSuite(suiteName);
        suitesAndCasesPage.updateSuite(newSuiteName, newSuiteDescription);
        suitesAndCasesPage.openCaseTab();
        assertTrue(suitesAndCasesPage.isSuiteExist(newSuiteName), "Suite was not updated");
    }

    @Test
    public void suiteShouldBeDeleted() {
        String suiteName = faker.country().name();
        String suiteDescription = faker.country().capital();

        loginPage.open();
        loginPage.login(USERNAME, PASSWORD);
        dashboardPage.openProject("TestProject");
        projectPage.openCaseTab();
        suitesAndCasesPage.isPageOpened();
        suitesAndCasesPage.clickCreateSuiteButton();
        suitesAndCasesPage.createSuite(suiteName, suiteDescription);
        suitesAndCasesPage.openCaseTab();
        suitesAndCasesPage.deleteSuite(suiteName);
        suitesAndCasesPage.confirmDeleteSuite();
        suitesAndCasesPage.openCaseTab();
        assertFalse(suitesAndCasesPage.isSuiteExist(suiteName), "Suite has not been deleted");
    }
}