package models.api;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Case {
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
}