package com.rest.simplerest.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Set;

@Data
public class DepartmentDTO {
    private String name;
    private Set<TeamDTO> teams;

    public DepartmentDTO(@JsonProperty(value = "name", required = true) String name, @JsonProperty(value = "teams", required = true) Set<TeamDTO> teams) {
        this.name = name;
        this.teams = teams;
    }
}
