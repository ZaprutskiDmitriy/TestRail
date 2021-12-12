package tests;

import adapters.*;
import models.api.Project;
import models.api.Run;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.util.Date;

public class ApiBaseTest {

    private static final String date = new Date().toString();
    public static final Run TESTRAIL_TEST_RUN = Run.builder().
            name("TestRail Test Run // " + date).
            isIncludeAll(true).
            build();

    ProjectAdapter projectAdapter;
    SuiteAdapter suiteAdapter;
    SectionAdapter sectionAdapter;
    CaseAdapter caseAdapter;
    RunAdapter runAdapter;
    int testProjectId;
    int testSuiteId;

    public ApiBaseTest() {
        projectAdapter = new ProjectAdapter();
        suiteAdapter = new SuiteAdapter();
        sectionAdapter = new SectionAdapter();
        caseAdapter = new CaseAdapter();
        runAdapter = new RunAdapter();
    }

    @BeforeSuite(description = "Test project creation")
    public void createTestProject() {
        Project project = Project.builder().
                name("TestProject").
                announcement("Project for testing").
                isShowedAnnouncement(false).
                suiteMode(1).
                build();

        projectAdapter.createProjectWithValidData(project, 200);
    }

    @BeforeSuite
    public void createTestRun() {
        int projectTestRailId = projectAdapter.getProjectId("TestRail");
        runAdapter.addTestRun(TESTRAIL_TEST_RUN, projectTestRailId);
    }

    @BeforeMethod(description = "Finding id of test project and test suite")
    public void findId() {
        testProjectId = projectAdapter.getProjectId("TestProject");
        testSuiteId = suiteAdapter.getSuiteId(testProjectId, "Master");
    }

    @AfterSuite(description = "Deleting a test project")
    public void deleteTestProject() {
        projectAdapter = new ProjectAdapter();
        int id = projectAdapter.getProjectId("TestProject");
        projectAdapter.deleteProject(id);
    }
}