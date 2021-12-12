package models.api;

import lombok.Data;

import java.util.List;

@Data
public class RunsResponse {
    int size;
    List<Run> runs;
}
