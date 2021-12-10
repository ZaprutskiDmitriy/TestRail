package tests;

import com.github.javafaker.Faker;
import models.api.Case;
import models.api.Section;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ApiCaseTest extends ApiBaseTest {
    static Faker faker = new Faker();

    @Test(description = "Check if the test case can be created by API")
    public void caseShouldBeCreatedByApi() {
        Section section = Section.builder().
                name(faker.team().state() + faker.number().randomDigit()).
                suiteId(testSuiteId).
                build();
        sectionAdapter.createSectionWithValidData(section, testProjectId, 200);
        int testSectionId = sectionAdapter.getSectionId(section.getName(), testProjectId, testSuiteId);
        Case testcase = Case.builder().
                title(faker.artist().name() + faker.number().randomDigit()).
                priorityId(faker.random().nextInt(1, 4)).
                typeId(faker.random().nextInt(1, 11)).
                templateId(faker.random().nextInt(1, 3)).
                sectionId(testSectionId).
                build();
        caseAdapter.createCaseWithValidData(testcase, testSectionId, 200);
        assertTrue(caseAdapter.isCaseExist(testcase.getTitle(), testProjectId, testSuiteId), "Case was not created");
    }

    @Test(description = "Check if the test case can be updated by API")
    public void caseShouldBeUpdatedByApi() {
        Section section = Section.builder().
                name(faker.team().state() + faker.number().randomDigit()).
                suiteId(testSuiteId).
                build();
        sectionAdapter.createSectionWithValidData(section, testProjectId, 200);
        int testSectionId = sectionAdapter.getSectionId(section.getName(), testProjectId, testSuiteId);
        Case testcase = Case.builder().
                title(faker.artist().name() + faker.number().randomDigit()).
                priorityId(faker.random().nextInt(1, 4)).
                typeId(faker.random().nextInt(1, 11)).
                templateId(faker.random().nextInt(1, 3)).
                sectionId(testSectionId).
                build();
        Case testcaseWithAnotherData = Case.builder().
                title(faker.programmingLanguage().creator() + faker.number().randomDigit()).
                priorityId(faker.random().nextInt(1, 4)).
                typeId(faker.random().nextInt(1, 11)).
                templateId(faker.random().nextInt(1, 3)).
                sectionId(testSectionId).
                build();
        caseAdapter.createCaseWithValidData(testcase, testSectionId, 200);
        int caseId = caseAdapter.getCaseId(testcase.getTitle(), testProjectId, testSuiteId);
        caseAdapter.updateCase(testcaseWithAnotherData, caseId);
        assertTrue(caseAdapter.isCaseExist(testcaseWithAnotherData.getTitle(), testProjectId, testSuiteId), "Case was not updated");
    }

    @Test(description = "Check if the test case can be deleted by API")
    public void caseShouldBeDeletedByApi() {
        Section section = Section.builder().
                name(faker.team().state() + faker.number().randomDigit()).
                suiteId(testSuiteId).
                build();
        sectionAdapter.createSectionWithValidData(section, testProjectId, 200);
        int testSectionId = sectionAdapter.getSectionId(section.getName(), testProjectId, testSuiteId);
        Case testcase = Case.builder().
                title(faker.artist().name() + faker.number().randomDigit()).
                priorityId(faker.random().nextInt(1, 4)).
                typeId(faker.random().nextInt(1, 11)).
                templateId(faker.random().nextInt(1, 3)).
                sectionId(testSectionId).
                build();
        caseAdapter.createCaseWithValidData(testcase, testSectionId, 200);
        int caseId = caseAdapter.getCaseId(testcase.getTitle(), testProjectId, testSuiteId);
        caseAdapter.deleteCase(caseId);
        assertFalse(caseAdapter.isCaseExist(testcase.getTitle(), testProjectId, testSuiteId), "Case was not deleted");
    }

    @Test(description = "Check that test case will not be created with an empty title field")
    public void caseShouldNotBeCreatedByApiWithEmptyTitleField() {
        Section section = Section.builder().
                name(faker.team().state() + faker.number().randomDigit()).
                suiteId(testSuiteId).
                build();
        sectionAdapter.createSectionWithValidData(section, testProjectId, 200);
        int testSectionId = sectionAdapter.getSectionId(section.getName(), testProjectId, testSuiteId);
        Case testcase = Case.builder().
                title(" ").
                priorityId(faker.random().nextInt(1, 4)).
                typeId(faker.random().nextInt(1, 11)).
                templateId(faker.random().nextInt(1, 3)).
                sectionId(testSectionId).
                build();

        String errorMessage = caseAdapter.createCaseWithInvalidData(testcase, testSectionId, 400).getError();
        assertEquals(errorMessage, "Field :title is a required field.", "Case was created with an empty title field");
    }

    @Test(description = "Check that test case will not be created with an empty template field")
    public void caseShouldNotBeCreatedByApiWithEmptyTemplateField() {
        Section section = Section.builder().
                name(faker.team().state() + faker.number().randomDigit()).
                suiteId(testSuiteId).
                build();
        sectionAdapter.createSectionWithValidData(section, testProjectId, 200);
        int testSectionId = sectionAdapter.getSectionId(section.getName(), testProjectId, testSuiteId);
        Case testcase = Case.builder().
                title(faker.artist().name() + faker.number().randomDigit()).
                priorityId(faker.random().nextInt(1, 4)).
                typeId(faker.random().nextInt(1, 11)).
                sectionId(testSectionId).
                build();

        String errorMessage = caseAdapter.createCaseWithInvalidData(testcase, testSectionId, 400).getError();
        assertEquals(errorMessage, "Field :template_id is not a valid template.", "Case was created with a wrong value of template field");
    }

    @Test(description = "Check that test case will not be created with an empty type field")
    public void caseShouldNotBeCreatedByApiWithEmptyTypeField() {
        Section section = Section.builder().
                name(faker.team().state() + faker.number().randomDigit()).
                suiteId(testSuiteId).
                build();
        sectionAdapter.createSectionWithValidData(section, testProjectId, 200);
        int testSectionId = sectionAdapter.getSectionId(section.getName(), testProjectId, testSuiteId);
        Case testcase = Case.builder().
                title(faker.artist().name() + faker.number().randomDigit()).
                priorityId(faker.random().nextInt(1, 4)).
                templateId(faker.random().nextInt(1, 3)).
                sectionId(testSectionId).
                build();

        String errorMessage = caseAdapter.createCaseWithInvalidData(testcase, testSectionId, 400).getError();
        assertEquals(errorMessage, "Field :type_id is not a valid case type.", "Case was created with a wrong value of type field");
    }

    @Test(description = "Check that test case will not be created with an empty priority field")
    public void caseShouldNotBeCreatedByApiWithEmptyPriorityField() {
        Section section = Section.builder().
                name(faker.team().state() + faker.number().randomDigit()).
                suiteId(testSuiteId).
                build();
        sectionAdapter.createSectionWithValidData(section, testProjectId, 200);
        int testSectionId = sectionAdapter.getSectionId(section.getName(), testProjectId, testSuiteId);
        Case testcase = Case.builder().
                title(faker.artist().name() + faker.number().randomDigit()).
                templateId(faker.random().nextInt(1, 3)).
                typeId(faker.random().nextInt(1, 11)).
                sectionId(testSectionId).
                build();

        String errorMessage = caseAdapter.createCaseWithInvalidData(testcase, testSectionId, 400).getError();
        assertEquals(errorMessage, "Field :priority_id is not a valid priority.", "Case was created with a wrong value of priority field");
    }
}