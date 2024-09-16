package com.rest.simplerest.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TeamDTO {
    private String name;
    private ProjectDTO project;

    public TeamDTO(@JsonProperty(value = "name", required = true) String name, @JsonProperty(value = "project", required = true) ProjectDTO project) {
        this.name = name;
        this.project = project;
    }
}

