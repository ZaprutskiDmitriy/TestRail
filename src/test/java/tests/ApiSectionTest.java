package tests;

import com.github.javafaker.Faker;
import io.qameta.allure.TmsLink;
import models.api.Section;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ApiSectionTest extends ApiBaseTest {

    static Faker faker = new Faker();

    @TmsLink("41")
    @Test(description = "Check if the section can be created by API")
    public void sectionShouldBeCreatedByApi() {
        Section section = Section.builder().
                name(faker.team().state() + faker.number().randomDigit()).
                description(faker.programmingLanguage().name()).
                suiteId(testSuiteId).
                build();

        sectionAdapter.createSectionWithValidData(section, testProjectId, 200);
        assertTrue(sectionAdapter.isSectionExist(section.getName(), testProjectId, testSuiteId), "Section was not created");
    }

    @TmsLink("42")
    @Test(description = "Check if the subsection can be created by API")
    public void subsectionShouldBeCreatedByApi() {
        Section section = Section.builder().
                name(faker.team().state() + faker.number().randomDigit()).
                description(faker.programmingLanguage().name()).
                suiteId(testSuiteId).
                build();

        sectionAdapter.createSectionWithValidData(section, testProjectId, 200);
        String sectionId = String.valueOf(sectionAdapter.getSectionId(section.getName(), testProjectId, testSuiteId));

        Section subsection = Section.builder().
                name(faker.team().state() + faker.number().randomDigit()).
                description(faker.programmingLanguage().name()).
                suiteId(testSuiteId).
                parentId(sectionId).
                build();

        sectionAdapter.createSectionWithValidData(subsection, testProjectId, 200);
        int subsectionId = sectionAdapter.getSectionId(subsection.getName(), testProjectId, testSuiteId);
        int depthOfSection = sectionAdapter.getSectionInfo(subsectionId).getDepth();
        boolean isSubsectionExist = sectionAdapter.isSectionExist(subsection.getName(), testProjectId, testSuiteId);
        assertTrue(depthOfSection > 0 && isSubsectionExist, "Subsection was not created");
    }

    @TmsLink("43")
    @Test(description = "Check if the section can be updated by API")
    public void sectionShouldBeUpdatedByApi() {
        Section section = Section.builder().
                name(faker.team().state() + faker.number().randomDigit()).
                description(faker.programmingLanguage().name()).
                suiteId(testSuiteId).
                build();

        Section sectionWithAnotherData = Section.builder().
                name(faker.nation().capitalCity() + faker.number().randomDigit()).
                description(faker.programmingLanguage().name()).
                build();

        sectionAdapter.createSectionWithValidData(section, testProjectId, 200);
        int sectionId = sectionAdapter.getSectionId(section.getName(), testProjectId, testSuiteId);
        sectionAdapter.updateSection(sectionWithAnotherData, sectionId);
        assertTrue(sectionAdapter.isSectionExist(sectionWithAnotherData.getName(), testProjectId, testSuiteId), "Section was not updated");
    }

    @TmsLink("44")
    @Test(description = "Check if the section can be deleted by API")
    public void sectionShouldBeDeletedByApi() {
        Section section = Section.builder().
                name(faker.team().state() + faker.number().randomDigit()).
                description(faker.programmingLanguage().name()).
                suiteId(testSuiteId).
                build();

        sectionAdapter.createSectionWithValidData(section, testProjectId, 200);
        int sectionId = sectionAdapter.getSectionId(section.getName(), testProjectId, testSuiteId);
        sectionAdapter.deleteSection(sectionId);
        assertFalse(sectionAdapter.isSectionExist(section.getName(), testProjectId, testSuiteId), "Section was not deleted");
    }

    @TmsLink("45")
    @Test(description = "Check that section will not be created with an empty title field")
    public void sectionShouldNotBeCreatedByApiWithEmptyTitleField() {
        Section section = Section.builder().
                name(" ").
                description(faker.programmingLanguage().name()).
                suiteId(testSuiteId).
                build();

        String errorMessage = sectionAdapter.createSectionWithInvalidData(section, testProjectId, 400).getError();
        assertEquals(errorMessage, "Field :name is a required field.", "Section was created with an empty name field");
    }
}