package models.api;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class SuiteResponse {
    int id;
    String name;
    String description;
    @SerializedName("project_id")
    int projectId;
    @SerializedName("is_master")
    boolean isMaster;
    @SerializedName("is_baseline")
    boolean isBaseline;
    @SerializedName("is_completed")
    boolean isCompleted;
    @SerializedName("completed_on")
    String completedOn;
    String url;
}