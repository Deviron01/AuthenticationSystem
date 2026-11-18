package com.deviron.AuthenticationSystem.Service;

import com.deviron.AuthenticationSystem.DTO.AuthResponseDTO;
import com.deviron.AuthenticationSystem.DTO.LoginDTO;
import com.deviron.AuthenticationSystem.DTO.RegisterDTO;

public interface AuthService {



    String register(RegisterDTO registerDTO);
    AuthResponseDTO login(LoginDTO loginDTO);
}
