package models.api;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class SectionResponse {
    int id;
    @SerializedName("suite_id")
    int suiteId;
    String name;
    String description;
    @SerializedName("parent_id")
    String parentId;
    @SerializedName("display_order")
    int displayOrder;
    int depth;
    String error;
}