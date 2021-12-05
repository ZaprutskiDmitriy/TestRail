package tests;

import models.TestCase;
import models.TestCaseFactory;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class CaseTest extends BaseTest {

    @Test
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
        assertTrue(suitesAndCasesPage.isCaseExist(testCase.getTitle()), "Test case was not created");
    }

    @Test
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
        suitesAndCasesPage.editCase(testCase.getTitle());
        TestCase newTestCase = TestCaseFactory.getSecondCase();
        caseCreationPage.updateCase(newTestCase);
        caseCreationPage.clickSaveTestCaseButton();
        caseDetailsPage.isPageOpened();
        caseDetailsPage.openCaseSection();
        assertTrue(suitesAndCasesPage.isCaseExist(newTestCase.getTitle()), "Test case was not updated");
    }

    @Test
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
        suitesAndCasesPage.deleteCase(testCase.getTitle());
        suitesAndCasesPage.confirmDeleteCase();
        suitesAndCasesPage.openCaseTab();
        assertFalse(suitesAndCasesPage.isCaseExist(testCase.getTitle()), "Test case has not been deleted");
    }
}