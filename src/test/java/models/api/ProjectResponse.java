package models.api;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class ProjectResponse {
    int id;
    String name;
    String announcement;
    @SerializedName("show_announcement")
    boolean isShowedAnnouncement;
    @SerializedName("is_completed")
    boolean isCompleted;
    @SerializedName("completed_on")
    String completedOn;
    @SerializedName("suite_mode")
    int suiteMode;
    String url;
    String error;
}