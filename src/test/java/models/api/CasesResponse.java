package models.api;

import lombok.Data;

import java.util.List;

@Data
public class CasesResponse {
    int size;
    List<Case> cases;
}