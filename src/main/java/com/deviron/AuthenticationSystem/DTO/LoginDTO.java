package com.deviron.AuthenticationSystem.DTO;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginDTO {

    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email!!")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;
}
