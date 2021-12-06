package tests;

import models.TestCase;
import models.TestCaseFactory;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class CaseTest extends BaseTest {

    @Test(description = "Check if the test case can be created")
    public void caseShouldBeCreated() {
        loginPage.open();
        loginPage.login(USERNAME, PASSWORD);
        dashboardPage.openProject("TestProject");
        projectPage.openCaseTab();
        suitesAndCasesPage.clickCreateCaseButton();
        TestCase testCase = TestCaseFactory.getCase();
        caseCreationPage.isPageOpened();
        caseCreationPage.createCase(testCase);
        caseCreationPage.clickSaveTestCaseButton();
        caseDetailsPage.openCaseSection();
        assertTrue(suitesAndCasesPage.isCaseOrSuiteExist(testCase.getTitle(), "case"), "Test case was not created");
    }

    @Test(description = "Check if the test case can be updated")
    public void caseShouldBeUpdated() {
        loginPage.open();
        loginPage.login(USERNAME, PASSWORD);
        dashboardPage.openProject("TestProject");
        projectPage.openCaseTab();
        suitesAndCasesPage.clickCreateCaseButton();
        TestCase testCase = TestCaseFactory.getCase();
        caseCreationPage.isPageOpened();
        caseCreationPage.createCase(testCase);
        caseCreationPage.clickSaveTestCaseButton();
        caseDetailsPage.isPageOpened();
        caseDetailsPage.openCaseSection();
        suitesAndCasesPage.clickDeleteOrEditIcon(testCase.getTitle(), "edit", "case");
        TestCase newTestCase = TestCaseFactory.getCaseWithAnotherData();
        caseCreationPage.updateCase(newTestCase);
        caseCreationPage.clickSaveTestCaseButton();
        caseDetailsPage.isPageOpened();
        caseDetailsPage.openCaseSection();
        assertTrue(suitesAndCasesPage.isCaseOrSuiteExist(newTestCase.getTitle(), "case"), "Test case was not updated");
    }

    @Test(description = "Check if the test case can be deleted")
    public void caseShouldBeDeleted() {
        loginPage.open();
        loginPage.login(USERNAME, PASSWORD);
        dashboardPage.openProject("TestProject");
        projectPage.openCaseTab();
        suitesAndCasesPage.clickCreateCaseButton();
        TestCase testCase = TestCaseFactory.getCase();
        caseCreationPage.isPageOpened();
        caseCreationPage.createCase(testCase);
        caseCreationPage.clickSaveTestCaseButton();
        caseDetailsPage.isPageOpened();
        caseDetailsPage.openCaseSection();
        suitesAndCasesPage.clickDeleteOrEditIcon(testCase.getTitle(), "delete", "case");
        suitesAndCasesPage.confirmDeleteCase();
        suitesAndCasesPage.openCaseTab();
        assertFalse(suitesAndCasesPage.isCaseOrSuiteExist(testCase.getTitle(), "case"), "Test case has not been deleted");
    }
}