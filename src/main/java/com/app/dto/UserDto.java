package com.app.dto;

import jakarta.persistence.Column;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @NotBlank(message = "email should not be blank")
    @Email(message = "invalid email address")
    private String email;

    @NotBlank(message = "password should not be blank")
    private String password;

    @NotBlank(message = "name should not be blank")
    private String firstName;

    @NotBlank(message = "lastname required")
    private String lastName;

    @NotBlank(message = "location is mandatory")
    private String location;

    @Pattern(regexp = "^\\d{10}$",message = "invalid mobile number entered ")
    private String mobile;

    @Min(15)
    @Max(60)
    private int age;

    private String role;


}
