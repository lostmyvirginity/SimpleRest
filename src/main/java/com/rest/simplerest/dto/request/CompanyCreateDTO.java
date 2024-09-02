package com.rest.simplerest.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyCreateDTO {
    private String name;

    @JsonCreator
    public CompanyCreateDTO(@JsonProperty(value = "name",required = true) String name) {
        this.name = name;
    }
}
