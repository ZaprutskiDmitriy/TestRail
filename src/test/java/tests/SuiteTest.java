package tests;

import com.github.javafaker.Faker;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class SuiteTest extends BaseTest {

    static Faker faker = new Faker();

    @Test
    public void suiteShouldBeCreated() {
        String suiteName = faker.country().name() + faker.number().randomDigit();
        String suiteDescription = faker.country().capital() + faker.number().randomDigit();

        loginPage.open();
        loginPage.login(USERNAME, PASSWORD);
        dashboardPage.openProject("TestProject");
        projectPage.openCaseTab();
        suitesAndCasesPage.isPageOpened();
        suitesAndCasesPage.clickCreateSuiteButton();
        suitesAndCasesPage.createSuite(suiteName, suiteDescription);
        suitesAndCasesPage.openCaseTab();
        assertTrue(suitesAndCasesPage.isCaseOrSuiteExist(suiteName, "suite"), "Suite was not created");
    }

    @Test
    public void suiteShouldBeUpdated() {
        String suiteName = faker.country().name() + faker.number().randomDigit();
        String newSuiteName = faker.currency().name() + faker.number().randomDigit();
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
        suitesAndCasesPage.clickDeleteOrEditIcon(suiteName, "edit", "suite");
        suitesAndCasesPage.updateSuite(newSuiteName, newSuiteDescription);
        suitesAndCasesPage.openCaseTab();
        assertTrue(suitesAndCasesPage.isCaseOrSuiteExist(newSuiteName, "suite"), "Suite was not updated");
    }

    @Test
    public void suiteShouldBeDeleted() {
        String suiteName = faker.country().name() + faker.number().randomDigit();
        String suiteDescription = faker.country().capital();

        loginPage.open();
        loginPage.login(USERNAME, PASSWORD);
        dashboardPage.openProject("TestProject");
        projectPage.openCaseTab();
        suitesAndCasesPage.isPageOpened();
        suitesAndCasesPage.clickCreateSuiteButton();
        suitesAndCasesPage.createSuite(suiteName, suiteDescription);
        suitesAndCasesPage.openCaseTab();
        suitesAndCasesPage.clickDeleteOrEditIcon(suiteName, "delete", "suite");
        suitesAndCasesPage.confirmDeleteSuite();
        suitesAndCasesPage.openCaseTab();
        assertFalse(suitesAndCasesPage.isCaseOrSuiteExist(suiteName,"suite"), "Suite has not been deleted");
    }
}