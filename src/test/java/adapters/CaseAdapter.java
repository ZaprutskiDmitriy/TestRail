package adapters;

import com.google.gson.Gson;
import io.qameta.allure.Step;
import models.api.*;

public class CaseAdapter extends BaseAdapter {

    @Step("Creating case with title '{testcase.title}'")
    public void createCaseWithValidData(Case testcase, int sectionId, int expectedStatusCode) {
        post(new Gson().toJson(testcase), "add_case/" + sectionId, expectedStatusCode);
    }
    @Step("Creating case with title '{testcase.title}'")
    public CaseResponse createCaseWithInvalidData(Case testcase, int sectionId, int expectedStatusCode) {
        String body = post(new Gson().toJson(testcase), "add_case/" + sectionId, expectedStatusCode);
        return new Gson().fromJson(body, CaseResponse.class);
    }

    public String getCaseName(int caseId) {
        String body = get("get_case/" + caseId, 200);
        return new Gson().fromJson(body, CaseResponse.class).getTitle();
    }

    public int getCaseId(String caseName, int projectId, int suiteId) {
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
        patch(new Gson().toJson(testcase), "update_case/" + caseId, 200);
    }

    @Step("Deleting case with id '{caseId}'")
    public void deleteCase(int caseId) {
        delete("delete_case/" + caseId, 200);
    }

    @Step("Checking the existence of the case with title '{caseName}'")
    public boolean isCaseExist(String caseName, int projectId, int suiteId) {
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