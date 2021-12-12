package models.api;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Suite {
    int id;
    String name;
    String description;
}