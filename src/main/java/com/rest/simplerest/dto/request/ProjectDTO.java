package com.rest.simplerest.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ProjectDTO {
    private ManagerDTO manager;

    public ProjectDTO(@JsonProperty(value = "manager", required = true) ManagerDTO manager) {
        this.manager = manager;
    }
}
