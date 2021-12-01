package tests;

import models.TestCase;
import models.TestCaseFactory;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

public class CaseTest extends BaseTest {

    @Test
    public void caseShouldBeCreated() {
        loginPage.open();
        loginPage.login(USERNAME, PASSWORD);
        dashboardPage.openProject(TEST_PROJECT_NAME);
        projectPage.openCaseTab();
        casesPage.clickCreateCaseButton();
        TestCase testCase = TestCaseFactory.get();
        caseCreationPage.createCase(testCase);
        caseCreationPage.clickSaveTestCaseButton();
        projectPage.openCaseTab();
        assertTrue(casesPage.isCaseExist(testCase.getTitle()), "Test case was not created");
    }
}