package tests;

import com.github.javafaker.Faker;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class SectionTest extends BaseTest {

    static Faker faker = new Faker();

    @TmsLink("38")
    @Test(description = "Check if the test section can be created")
    public void sectionShouldBeCreated() {
        String sectionName = faker.country().name() + faker.number().randomDigit();
        String sectionDescription = faker.country().capital() + faker.number().randomDigit();

        loginPage.open();
        loginPage.login(USERNAME, PASSWORD);
        dashboardPage.openProject("TestProject");
        projectPage.openCaseTab();
        testCasesTab.isPageOpened();
        testCasesTab.clickCreateSectionButton();
        testCasesTab.createSection(sectionName, sectionDescription);
        testCasesTab.openCaseTab();
        assertTrue(testCasesTab.isSectionExist(sectionName), "Section was not created");
    }

    @TmsLink("39")
    @Test(description = "Check if the test section can be updated")
    public void sectionShouldBeUpdated() {
        String sectionName = faker.country().name() + faker.number().randomDigit();
        String newSectionName = faker.currency().name() + faker.number().randomDigit();
        String sectionDescription = faker.country().capital();
        String newSectionDescription = faker.currency().code();

        loginPage.open();
        loginPage.login(USERNAME, PASSWORD);
        dashboardPage.openProject("TestProject");
        projectPage.openCaseTab();
        testCasesTab.isPageOpened();
        testCasesTab.clickCreateSectionButton();
        testCasesTab.createSection(sectionName, sectionDescription);
        testCasesTab.clickEditSection(sectionName);
        testCasesTab.updateSection(newSectionName, newSectionDescription);
        assertTrue(testCasesTab.isSectionExist(newSectionName), "Section was not updated");
    }

    @TmsLink("40")
    @Test(description = "Check if the test section can be deleted")
    public void sectionShouldBeDeleted() {
        String sectionName = faker.country().name() + faker.number().randomDigit();
        String sectionDescription = faker.country().capital();

        loginPage.open();
        loginPage.login(USERNAME, PASSWORD);
        dashboardPage.openProject("TestProject");
        projectPage.openCaseTab();
        testCasesTab.isPageOpened();
        testCasesTab.clickCreateSectionButton();
        testCasesTab.createSection(sectionName, sectionDescription);
        testCasesTab.clickDeleteSection(sectionName);
        testCasesTab.confirmDeleteSection();
        testCasesTab.openCaseTab();
        assertFalse(testCasesTab.isSectionExist(sectionName), "Section has not been deleted");
    }
}