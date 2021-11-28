package tests;

import com.github.javafaker.Faker;
import org.testng.annotations.Test;
import pages.ProjectCreationPage;

import static org.testng.Assert.assertTrue;

public class CreateProjectTest extends BaseTest {

    static Faker faker;

    @Test
    public void projectShouldBeCreated() {
        faker = new Faker();
        String projectName = faker.app().name() + faker.number().randomDigit();
        String projectAnnouncement = faker.app().version();

        loginPage.open();
        loginPage.login(USERNAME, PASSWORD);
        dashboardPage.createProject();
        projectCreationPage.insertProjectInfo(projectName, projectAnnouncement, ProjectCreationPage.Type.MULTIPLY);
        dashboardPage.open();
        assertTrue(dashboardPage.validateProject(projectName), "Project was not created");
    }
}
