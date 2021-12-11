package adapters;

import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;
import models.api.SuiteResponse;

@Log4j2
public class SuiteAdapter extends BaseAdapter {

    public int getSuiteId(int projectId, String suiteName) {
        log.info("Getting id of the suite with title '{}'", suiteName);
        int id = 0;
        String suites = get("get_suites/" + projectId, 200);
        SuiteResponse[] arraySuites = new Gson().fromJson(suites, SuiteResponse[].class);
        for (SuiteResponse suite : arraySuites) {
            if (suite.getName().equals(suiteName))
                id = suite.getId();
        }
        return id;
    }
}