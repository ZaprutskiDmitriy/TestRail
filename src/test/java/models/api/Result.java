package models.api;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Result {
        @SerializedName("status_id")
        int statusId;
}
