package adapters;

import com.google.gson.Gson;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import models.api.*;

@Log4j2
public class CaseAdapter extends BaseAdapter {

    @Step("Creating case with title '{testcase.title}'")
    public void createCaseWithValidData(Case testcase, int sectionId, int expectedStatusCode) {
        log.info("Creating case with title '{}'", testcase.getTitle());
        post(new Gson().toJson(testcase), "add_case/" + sectionId, expectedStatusCode);
    }
    @Step("Creating case with title '{testcase.title}'")
    public CaseResponse createCaseWithInvalidData(Case testcase, int sectionId, int expectedStatusCode) {
        log.info("Creating case with title '{}'", testcase.getTitle());
        String body = post(new Gson().toJson(testcase), "add_case/" + sectionId, expectedStatusCode);
        return new Gson().fromJson(body, CaseResponse.class);
    }

    public String getCaseName(int caseId) {
        log.info("Getting title of case with id '{}'", caseId);
        String body = get("get_case/" + caseId, 200);
        return new Gson().fromJson(body, CaseResponse.class).getTitle();
    }

    public int getCaseId(String caseName, int projectId, int suiteId) {
        log.info("Getting id of the case with title '{}'", caseName);
        int id = -1;
        String uri = String.format("get_cases/%s&suite_id=%s", projectId, suiteId);
        String cases = get(uri, 200);
        CasesResponse response = new Gson().fromJson(cases, CasesResponse.class);
        for (Case testCase : response.getCases()) {
            if (testCase.getTitle().equals(caseName)) {
                id = testCase.getId();
            }
        }
        return id;
    }

    @Step("Updating primary case to case with title '{testcase.title}'")
    public void updateCase(Case testcase, int caseId) {
        log.info("Updating to case with title '{}'", testcase.getTitle());
        patch(new Gson().toJson(testcase), "update_case/" + caseId, 200);
    }

    @Step("Deleting case with id '{caseId}'")
    public void deleteCase(int caseId) {
        log.info("Deleting case with id '{}'", caseId);
        delete("delete_case/" + caseId, 200);
    }

    @Step("Checking the existence of the case with title '{caseName}'")
    public boolean isCaseExist(String caseName, int projectId, int suiteId) {
        log.info("Checking the existence of the case with title '{}'", caseName);
        boolean isCaseExist = false;
        String uri = String.format("get_cases/%s&suite_id=%s", projectId, suiteId);
        String cases = get(uri, 200);
        CasesResponse response = new Gson().fromJson(cases, CasesResponse.class);
        for (Case testcase : response.getCases()) {
            if (testcase.getTitle().equals(caseName)) {
                isCaseExist = true;
            }
        }
        return isCaseExist;
    }
}