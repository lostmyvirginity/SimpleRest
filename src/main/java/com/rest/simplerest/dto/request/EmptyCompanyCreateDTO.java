package com.rest.simplerest.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmptyCompanyCreateDTO {
    private String name;

    @JsonCreator
    public EmptyCompanyCreateDTO(@JsonProperty(value = "name", required = true) String name) {
        this.name = name;
    }
}
