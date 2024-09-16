package com.rest.simplerest.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "manager")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Manager Name cannot be blank")
    private String name;
    @NotBlank(message = "Surname cannot be blank")
    private String surname;
    @NotBlank(message = "Phone Number cannot be blank")
    private String phoneNumber;
    @NotBlank(message = "Email cannot be blank")
    private String email;

    @OneToOne(mappedBy = "manager")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Project project;
}
