package com.deviron.AuthenticationSystem.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponseDTO {
    private String token;
    private String message;
    private String email;
    private String role;

}
