package tests;

import io.qameta.allure.TmsLink;
import models.ui.TestCase;
import models.ui.TestCaseFactory;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class CaseTest extends BaseTest {

    @TmsLink("46")
    @Test(description = "Check if the test case can be created")
    public void caseShouldBeCreated() {
        loginPage.open();
        loginPage.login(USERNAME, PASSWORD);
        dashboardPage.openProject("TestProject");
        projectPage.openCaseTab();
        testCasesTab.clickCreateCaseButton();
        TestCase testCase = TestCaseFactory.getCase();
        caseCreationPage.isPageOpened();
        caseCreationPage.createCase(testCase);
        caseCreationPage.clickSaveTestCaseButton();
        caseDetailsPage.openCaseSection();
        assertTrue(testCasesTab.isCaseExist(testCase.getTitle()), "Test case was not created");
    }

    @TmsLink("47")
    @Test(description = "Check if the test case can be updated")
    public void caseShouldBeUpdated() {
        loginPage.open();
        loginPage.login(USERNAME, PASSWORD);
        dashboardPage.openProject("TestProject");
        projectPage.openCaseTab();
        testCasesTab.clickCreateCaseButton();
        TestCase testCase = TestCaseFactory.getCase();
        caseCreationPage.isPageOpened();
        caseCreationPage.createCase(testCase);
        caseCreationPage.clickSaveTestCaseButton();
        caseDetailsPage.isPageOpened();
        caseDetailsPage.openCaseSection();
        testCasesTab.clickEditCase(testCase.getTitle());
        TestCase newTestCase = TestCaseFactory.getCaseWithAnotherData();
        caseCreationPage.updateCase(newTestCase);
        caseCreationPage.clickSaveTestCaseButton();
        caseDetailsPage.isPageOpened();
        caseDetailsPage.openCaseSection();
        assertTrue(testCasesTab.isCaseExist(newTestCase.getTitle()), "Test case was not updated");
    }

    @TmsLink("48")
    @Test(description = "Check if the test case can be deleted")
    public void caseShouldBeDeleted() {
        loginPage.open();
        loginPage.login(USERNAME, PASSWORD);
        dashboardPage.openProject("TestProject");
        projectPage.openCaseTab();
        testCasesTab.clickCreateCaseButton();
        TestCase testCase = TestCaseFactory.getCase();
        caseCreationPage.isPageOpened();
        caseCreationPage.createCase(testCase);
        caseCreationPage.clickSaveTestCaseButton();
        caseDetailsPage.isPageOpened();
        caseDetailsPage.openCaseSection();
        testCasesTab.clickDeleteCase(testCase.getTitle());
        testCasesTab.confirmDeleteCase();
        testCasesTab.openCaseTab();
        assertFalse(testCasesTab.isCaseExist(testCase.getTitle()), "Test case has not been deleted");
    }
}