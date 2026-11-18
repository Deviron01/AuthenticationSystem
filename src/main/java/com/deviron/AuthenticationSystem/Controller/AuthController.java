package com.deviron.AuthenticationSystem.Controller;

import com.deviron.AuthenticationSystem.DTO.AuthResponseDTO;
import com.deviron.AuthenticationSystem.DTO.LoginDTO;
import com.deviron.AuthenticationSystem.DTO.RegisterDTO;
import com.deviron.AuthenticationSystem.Service.AuthService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterDTO registerDTO) {
        String response = authService.register(registerDTO);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody LoginDTO loginDTO) {

        AuthResponseDTO response = authService.login(loginDTO);
        return ResponseEntity.ok(response);
    }

    // create a protected  profile endpoint
    @GetMapping("/profile")
    public ResponseEntity<Map<String, String>> getProfile(
            Authentication authentication)
    {
        Map<String, String> profile = Map.of(
                "message", "This is an authenticated profile endpoint",
                "user", authentication.getName()
        );
        return ResponseEntity.ok(profile);
    }




}
