package models.api;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Run {
    int id;
    @SerializedName("suite_id")
    int suiteId;
    String name;
    @SerializedName("include_all")
    boolean isIncludeAll;
    @SerializedName("case_ids")
    int[] caseIds;
}
