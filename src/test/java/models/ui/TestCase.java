package models.ui;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TestCase {

    String title;
    String section;
    String template;
    String type;
    String priority;
    String estimate;
    String references;
    String automationType;
    String preconditions;
    String steps;
    String expectedResults;
}