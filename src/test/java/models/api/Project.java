package models.api;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Project {
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
}