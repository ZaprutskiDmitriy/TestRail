package tests;

import com.github.javafaker.Faker;
import org.testng.annotations.Test;
import pages.ProjectCreationPage;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class ProjectTest extends BaseTest {

    static Faker faker = new Faker();

    @Test
    public void projectShouldBeCreated() {
        String projectName = faker.app().name() + faker.number().randomDigit();
        String projectAnnouncement = faker.app().version();

        loginPage.open();
        loginPage.login(USERNAME, PASSWORD);
        dashboardPage.clickCreateProjectButton();
        projectCreationPage.createProject(projectName, projectAnnouncement, ProjectCreationPage.Type.MULTIPLY);
        dashboardPage.open();
        assertTrue(dashboardPage.isProjectExist(projectName), "Project was not created");
    }

    @Test
    public void projectShouldBeUpdated() {
        String projectName = faker.app().name() + faker.number().randomDigit();
        String newProjectName = faker.book().title() + faker.number().randomDigit();
        String projectAnnouncement = faker.app().version();
        String newProjectAnnouncement = faker.book().genre();

        loginPage.open();
        loginPage.login(USERNAME, PASSWORD);
        dashboardPage.clickCreateProjectButton();
        projectCreationPage.createProject(projectName, projectAnnouncement, ProjectCreationPage.Type.MULTIPLY);
        administrationPage.isPageOpened();
        administrationPage.editProject(projectName);
        projectCreationPage.updateProject(newProjectName, newProjectAnnouncement, ProjectCreationPage.Type.SINGLE);
        dashboardPage.open();
        assertTrue(dashboardPage.isProjectExist(newProjectName), "Project was not updated");
    }

    @Test
    public void projectShouldBeDeleted() {
        String projectName = faker.app().name() + faker.number().randomDigit();
        String projectAnnouncement = faker.app().version();

        loginPage.open();
        loginPage.login(USERNAME, PASSWORD);
        dashboardPage.clickCreateProjectButton();
        projectCreationPage.createProject(projectName, projectAnnouncement, ProjectCreationPage.Type.MULTIPLY);
        administrationPage.isPageOpened();
        administrationPage.deleteProject(projectName);
        administrationPage.confirmDeleteProject();
        dashboardPage.open();
        assertFalse(dashboardPage.isProjectExist(projectName), "Project has not been deleted");
    }
}