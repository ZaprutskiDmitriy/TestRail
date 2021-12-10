package models.api;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class CaseResponse {
    int id;
    String title;
    @SerializedName("section_id")
    int sectionId;
    @SerializedName("template_id")
    int templateId;
    @SerializedName("type_id")
    int typeId;
    @SerializedName("priority_id")
    int priorityId;
    String estimate;
    @SerializedName("suite_id")
    int suiteId;
    @SerializedName("custom_automation_type")
    int customAutomationType;
    @SerializedName("custom_preconds")
    String precontitions;
    @SerializedName("custom_steps")
    String steps;
    @SerializedName("custom_expected")
    String expectedResult;
    String error;
}