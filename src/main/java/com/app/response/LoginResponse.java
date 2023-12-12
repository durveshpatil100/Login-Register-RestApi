package com.app.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginResponse {

    String message;
    Boolean status;
    String firstName;
    String lastName;
    String role;


    public LoginResponse(String message, Boolean status) {
        this.message = message;
        this.status = status;
    }

    public LoginResponse(String message, Boolean status, String firstName, String lastName) {
        this.message = message;
        this.status = status;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public LoginResponse(String message, Boolean status, String firstName, String lastName,String role) {
        this.message = message;
        this.status = status;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }
}
