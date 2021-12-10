package adapters;

import com.google.gson.Gson;
import io.qameta.allure.Step;
import models.api.Project;
import models.api.ProjectResponse;
import models.api.ProjectsResponse;

public class ProjectAdapter extends BaseAdapter {

    @Step("Creating project with title '{project.name}'")
    public void createProjectWithValidData(Project project, int expectedStatusCode) {
        post(new Gson().toJson(project), "add_project", expectedStatusCode);
    }

    @Step("Creating project with title '{project.name}'")
    public ProjectResponse createProjectWithInvalidData(Project project, int expectedStatusCode) {
        String body = post(new Gson().toJson(project), "add_project", expectedStatusCode);
        return new Gson().fromJson(body, ProjectResponse.class);
    }

    public String getProjectName(int projectId) {
        String body = get("get_project/" + projectId, 200);
        return new Gson().fromJson(body, ProjectResponse.class).getName();
    }

    public int getProjectId(String projectName) {
        int id = -1;
        String projects = get("get_projects", 200);
        ProjectsResponse response = new Gson().fromJson(projects, ProjectsResponse.class);
        for (Project project : response.getProjects()) {
            if (project.getName().equals(projectName)) {
                id = project.getId();
            }
        }
        return id;
    }

    @Step("Updating primary project to project with title '{project.name}'")
    public void updateProject(Project project, int projectId) {
        patch(new Gson().toJson(project), "update_project/" + projectId, 200);
    }

    @Step("Deleting project with id '{projectId}'")
    public void deleteProject(int projectId) {
        delete("delete_project/" + projectId, 200);
    }

    @Step("Checking the existence of the project with title '{projectName}'")
    public boolean isProjectExist(String projectName) {
        boolean isProjectExist = false;
        String projects = get("get_projects", 200);
        ProjectsResponse response = new Gson().fromJson(projects, ProjectsResponse.class);
        for (Project project : response.getProjects()) {
            if (project.getName().equals(projectName)) {
                isProjectExist = true;
            }
        }
        return isProjectExist;
    }
}