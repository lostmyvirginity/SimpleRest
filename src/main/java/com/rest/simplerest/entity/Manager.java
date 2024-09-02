package com.rest.simplerest.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "manager")
@Data
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String surname;
    @NotEmpty
    private String phoneNumber;
    @NotEmpty
    private String email;

    @OneToOne(mappedBy = "manager")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Project project;
}
