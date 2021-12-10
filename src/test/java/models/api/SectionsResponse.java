package models.api;

import lombok.Data;

import java.util.List;

@Data
public class SectionsResponse {
    int size;
    List<Section> sections;
}