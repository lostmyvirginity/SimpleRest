package com.rest.simplerest.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Set;

@Data
public class CompanyCreateDTO {
    private String name;
    private Set<DepartmentDTO> departments;

    @JsonCreator
    public CompanyCreateDTO(@JsonProperty(value = "name", required = true) String name, @JsonProperty(value = "departments", required = true) Set<DepartmentDTO> departments) {
        this.name = name;
        this.departments = departments;
    }
}
