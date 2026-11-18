package com.deviron.AuthenticationSystem.Service.IMPL;

import com.deviron.AuthenticationSystem.DTO.AuthResponseDTO;
import com.deviron.AuthenticationSystem.DTO.LoginDTO;
import com.deviron.AuthenticationSystem.DTO.RegisterDTO;
import com.deviron.AuthenticationSystem.Entity.User;
import com.deviron.AuthenticationSystem.Repository.UserRepo;
import com.deviron.AuthenticationSystem.Security.JwtService;
import com.deviron.AuthenticationSystem.Service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthServiceImpl implements AuthService {

    private final JwtService jwtService;
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserRepo userRepo, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }


    @Override
    public String register(RegisterDTO registerDTO) {
        // Check if the email already exists
        if (userRepo.existsByEmail(registerDTO.getEmail())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Email already exists"
            );
        }

        // Create a new user entity and set its properties
        User user = new User();
        user.setName(registerDTO.getName());
        user.setEmail(registerDTO.getEmail());

        // Encode the password before saving it to the database
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

        user.setRole("USER"); // Set the default role for the user

        // Save the user to the database
        userRepo.save(user);

        return "User registered successfully";
    }
    @Override
    public AuthResponseDTO login(LoginDTO loginDTO){

        // Find the user by email
        User user = userRepo
                .findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED,
                        "Invalid email or password"
                ));

        // Check if the provided password matches the stored password
       boolean passwordmatches = passwordEncoder.matches(
               loginDTO.getPassword(),
               user.getPassword()
       );
       if (!passwordmatches) {
           throw new ResponseStatusException(
                   HttpStatus.UNAUTHORIZED,
                   "Invalid email or password"
           );
       }
    String token = jwtService.generateToken(user);

        return new AuthResponseDTO(
                token,
                "Login successful",
                user.getEmail(),
                user.getRole()
        );

    }
}
