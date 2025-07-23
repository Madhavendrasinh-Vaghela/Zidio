package com.zidio.connect.service;

import java.util.Optional;

import com.zidio.connect.dto.JwtAuthenticationResponse;
import com.zidio.connect.dto.SignInRequest;
import com.zidio.connect.dto.SignUpRequest;
import com.zidio.connect.entity.User;

public interface AuthService {
    User createUser(SignUpRequest signUpRequest);
     JwtAuthenticationResponse signIn(SignInRequest signInRequest);
     Optional<User> loginUser(String email, String password);
}