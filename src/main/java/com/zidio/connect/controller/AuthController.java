package com.zidio.connect.controller;

import com.zidio.connect.dto.LoginRequest;
import com.zidio.connect.dto.SignUpRequest;
import com.zidio.connect.entity.User;
import com.zidio.connect.service.AuthService;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> createUser(@RequestBody SignUpRequest signUpRequest) {
        User createdUser = authService.createUser(signUpRequest);
        if (createdUser != null) {
            return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Failed to create user", HttpStatus.BAD_REQUEST);
    }


    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody SignUpRequest signUpRequest) {
        // Implement login logic here
        return new ResponseEntity<>("Login functionality not implemented yet", HttpStatus.NOT_IMPLEMENTED);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser() {
        // Implement logout logic here
        return new ResponseEntity<>("Logout functionality not implemented yet", HttpStatus.NOT_IMPLEMENTED);
    }
    public boolean isAuthenticated() {
        // Check if the user is authenticated
        return true; // Placeholder return value
    }
    @PostMapping("/isAuthenticated")
    public ResponseEntity<?> checkAuthentication() {
        boolean isAuthenticated = isAuthenticated();
        if (isAuthenticated) {
            return new ResponseEntity<>("User is authenticated", HttpStatus.OK);
        } else {    
            return new ResponseEntity<>("User is not authenticated", HttpStatus.UNAUTHORIZED);
        }   

}
@PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        Optional<User> userOptional = authService.loginUser(loginRequest.getEmail(), loginRequest.getPassword());

        if (userOptional.isPresent()) {
            // In a real application, you would return a JWT (JSON Web Token) here.
            return new ResponseEntity<>("Login successful", HttpStatus.OK);
        }
        return new ResponseEntity<>("Invalid email or password", HttpStatus.UNAUTHORIZED);
    }   
}