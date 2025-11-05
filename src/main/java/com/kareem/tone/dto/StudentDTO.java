package com.kareem.tone.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StudentDTO {

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Email is not valid")
    private String email;

    @Min(value = 18, message = "Age must be greater than 18")
    private int age;

    public StudentDTO() {
    }

    public StudentDTO(String name, String email, int age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }

}
