package tests;

import adapters.CaseAdapter;
import adapters.ProjectAdapter;
import adapters.SectionAdapter;
import adapters.SuiteAdapter;
import models.api.Project;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class ApiBaseTest {

    ProjectAdapter projectAdapter;
    SuiteAdapter suiteAdapter;
    SectionAdapter sectionAdapter;
    CaseAdapter caseAdapter;
    int testProjectId;
    int testSuiteId;

    public ApiBaseTest() {
        projectAdapter = new ProjectAdapter();
        suiteAdapter = new SuiteAdapter();
        sectionAdapter = new SectionAdapter();
        caseAdapter = new CaseAdapter();
    }

    @BeforeSuite(description = "Test project creation")
    public void createTestProject() {
        projectAdapter = new ProjectAdapter();
        Project project = Project.builder().
                name("TestProject").
                announcement("Project for testing").
                isShowedAnnouncement(false).
                suiteMode(1).
                build();

        projectAdapter.createProjectWithValidData(project, 200);
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