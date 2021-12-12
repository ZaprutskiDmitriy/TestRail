package adapters;

import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;
import models.api.*;

@Log4j2
public class RunAdapter extends BaseAdapter {

    public void addResultToTestRun(Result result, int testRunId, String caseId) {
        log.info("Transferring result of the test to test run with id '{}'", testRunId);
        String uri = "add_result_for_case/" + testRunId + "/" + caseId;
        post(new Gson().toJson(result), uri, 200);
    }

    public void addTestRun(Run testRun, int projectId) {
        log.info("Creating test run with title '{}'", testRun.getName());
        String uri = "add_run/" + projectId;
        post(new Gson().toJson(testRun), "add_run/" + projectId, 200);
    }

    public int getRunId(String runName, int projectId) {
        log.info("Getting id of the test run with title '{}'", runName);
        int id = -1;
        String runs = get("get_runs/" + projectId, 200);
        RunsResponse response = new Gson().fromJson(runs, RunsResponse.class);
        for (Run run : response.getRuns()) {
            if (run.getName().equals(runName)) {
                id = run.getId();
            }
        }
        return id;
    }
}