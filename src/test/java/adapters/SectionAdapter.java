package adapters;

import com.google.gson.Gson;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import models.api.Section;
import models.api.SectionResponse;
import models.api.SectionsResponse;

@Log4j2
public class SectionAdapter extends BaseAdapter {

    @Step("Creating section with title '{section.name}'")
    public void createSectionWithValidData(Section section, int projectId, int expectedStatusCode) {
        log.info("Creating section with title '{}'", section.getName());
        post(new Gson().toJson(section), "add_section/" + projectId, expectedStatusCode);
    }

    @Step("Creating section with title '{section.name}'")
    public SectionResponse createSectionWithInvalidData(Section section, int projectId, int expectedStatusCode) {
        log.info("Creating section with title '{}'", section.getName());
        String body = post(new Gson().toJson(section), "add_section/" + projectId, expectedStatusCode);
        return new Gson().fromJson(body, SectionResponse.class);
    }

    public SectionResponse getSectionInfo(int sectionId) {
        log.info("Getting information about section with id '{}'", sectionId);
        String body = get("get_section/" + sectionId, 200);
        return new Gson().fromJson(body, SectionResponse.class);
    }

    public int getSectionId(String sectionName, int projectId, int suiteId) {
        log.info("Getting id of the section with title '{}'", sectionName);
        int id = -1;
        String uri = String.format("get_sections/%s&suite_id=%s", projectId, suiteId);
        String sections = get(uri, 200);
        SectionsResponse response = new Gson().fromJson(sections, SectionsResponse.class);
        for (Section section : response.getSections()) {
            if (section.getName().equals(sectionName)) {
                id = section.getId();
            }
        }
        return id;
    }

    @Step("Updating primary section to section with title '{section.name}'")
    public void updateSection(Section section, int sectionId) {
        log.info("Updating to section with title '{}'", section.getName());
        patch(new Gson().toJson(section), "update_section/" + sectionId, 200);
    }

    @Step("Deleting section with id '{sectionId}'")
    public void deleteSection(int sectionId) {
        log.info("Deleting the section with id '{}'", sectionId);
        delete("delete_section/" + sectionId, 200);
    }

    @Step("Checking the existence of the section with title '{sectionName}'")
    public boolean isSectionExist(String sectionName, int projectId, int suiteId) {
        log.info("Checking the existence of the section with title '{}'", sectionName);
        boolean isSectionExist = false;
        String uri = String.format("get_sections/%s&suite_id=%s", projectId, suiteId);
        String sections = get(uri, 200);
        SectionsResponse response = new Gson().fromJson(sections, SectionsResponse.class);
        for (Section section : response.getSections()) {
            if (section.getName().equals(sectionName)) {
                isSectionExist = true;
            }
        }
        return isSectionExist;
    }
}