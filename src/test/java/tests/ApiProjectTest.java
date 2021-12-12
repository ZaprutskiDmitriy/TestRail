package tests;

import com.github.javafaker.Faker;
import io.qameta.allure.TmsLink;
import models.api.Project;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ApiProjectTest extends ApiBaseTest {

    static Faker faker = new Faker();

    @TmsLink("33")
    @Test(description = "Check if the project can be created by API")
    public void projectShouldBeCreatedByApi() {
        Project project = Project.builder().
                name(faker.company().name() + faker.number().randomDigit()).
                announcement(faker.color().hex()).
                isShowedAnnouncement(true).
                suiteMode(faker.random().nextInt(1, 3)).
                build();

        projectAdapter.createProjectWithValidData(project, 200);
        assertTrue(projectAdapter.isProjectExist(project.getName()), "Project was not created");
    }

    @TmsLink("34")
    @Test(description = "Check if the project can be updated by API")
    public void projectShouldBeUpdatedByApi() {
        Project project = Project.builder().
                name(faker.company().name() + faker.number().randomDigit()).
                announcement(faker.color().hex()).
                isShowedAnnouncement(true).
                suiteMode(faker.random().nextInt(1, 3)).
                build();
        Project projectWithAnotherData = Project.builder().
                name(faker.company().name() + faker.number().randomDigit()).
                announcement(faker.color().hex()).
                isShowedAnnouncement(false).
                suiteMode(faker.random().nextInt(1, 3)).
                build();

        projectAdapter.createProjectWithValidData(project, 200);
        int idActualProject = projectAdapter.getProjectId(project.getName());
        projectAdapter.getProjectName(idActualProject);
        projectAdapter.updateProject(projectWithAnotherData, idActualProject);
        projectAdapter.getProjectName(idActualProject);
        assertTrue(projectAdapter.isProjectExist(projectWithAnotherData.getName()), "Project was not updated");
    }

    @TmsLink("35")
    @Test(description = "Check if the project can be deleted by API")
    public void projectShouldBeDeletedByApi() {
        Project project = Project.builder().
                name(faker.company().name() + faker.number().randomDigit()).
                announcement(faker.color().hex()).
                isShowedAnnouncement(true).
                suiteMode(faker.random().nextInt(1, 3)).
                build();

        projectAdapter.createProjectWithValidData(project, 200);
        int idActualProject = projectAdapter.getProjectId(project.getName());
        projectAdapter.getProjectName(idActualProject);
        projectAdapter.deleteProject(idActualProject);
        assertFalse(projectAdapter.isProjectExist(project.getName()), "Project was not deleted");
    }

    @TmsLink("36")
    @Test(description = "Check that project will not be created with an empty title field")
    public void projectShouldNotBeCreatedByApiWithEmptyTitleField() {
        Project project = Project.builder().
                name(" ").
                announcement(faker.color().hex()).
                isShowedAnnouncement(true).
                suiteMode(faker.random().nextInt(1, 3)).
                build();

        String errorMessage = projectAdapter.createProjectWithInvalidData(project, 400).getError();
        assertEquals(errorMessage, "Field :name is a required field.", "Project was created with an empty title field");
    }

    @TmsLink("37")
    @Test(description = "Check that project will not be created with wrong value of suite mode")
    public void projectShouldNotBeCreatedByApiWithSuiteModeOutsideTheRangeFrom1To3() {
        Project project = Project.builder().
                name(faker.company().name() + faker.number().randomDigit()).
                announcement(faker.color().hex()).
                isShowedAnnouncement(true).
                suiteMode(faker.random().nextInt(4, 100)).
                build();

        String expectedErrorMessage = String.format("Field :suite_mode is not a supported enum value (\"%s\").", project.getSuiteMode());
        String actualErrorMessage = projectAdapter.createProjectWithInvalidData(project, 400).getError();
        assertEquals(actualErrorMessage, expectedErrorMessage, "Project was created with wrong value of suite mode");
    }
}