package com.rest.simplerest.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ManagerDTO {
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;

    public ManagerDTO(@JsonProperty(value = "name", required = true) String name, @JsonProperty(value = "surname", required = true) String surname, @JsonProperty(value = "phoneNumber", required = true) String phoneNumber, @JsonProperty(value = "email", required = true) String email) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
}
