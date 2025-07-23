package com.zidio.connect.service;

import com.zidio.connect.dto.JwtAuthenticationResponse;
import com.zidio.connect.dto.SignInRequest;
import com.zidio.connect.dto.SignUpRequest;
import com.zidio.connect.entity.RecruiterProfile;
import com.zidio.connect.entity.StudentProfile;
import com.zidio.connect.entity.User;
import com.zidio.connect.repository.RecruiterProfileRepository;
import com.zidio.connect.repository.StudentProfileRepository;
import com.zidio.connect.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor 
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final StudentProfileRepository studentProfileRepository;
    private final RecruiterProfileRepository recruiterProfileRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager; 
    private final JwtService jwtService;

    @Override
    public User createUser(SignUpRequest signUpRequest) {
        User user = new User();
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setRole(signUpRequest.getRole());
        User createdUser = userRepository.save(user);

        if ("STUDENT".equalsIgnoreCase(signUpRequest.getRole())) {
            StudentProfile studentProfile = new StudentProfile();
            studentProfile.setUser(createdUser);
            studentProfile.setFullName(signUpRequest.getFullName());
            studentProfileRepository.save(studentProfile);
        } else if ("RECRUITER".equalsIgnoreCase(signUpRequest.getRole())) {
            RecruiterProfile recruiterProfile = new RecruiterProfile();
            recruiterProfile.setUser(createdUser);
            recruiterProfile.setCompanyName(signUpRequest.getCompanyName());
            recruiterProfileRepository.save(recruiterProfile);
        }

        return createdUser;
    }

    @Override
    public JwtAuthenticationResponse signIn(SignInRequest signInRequest) {
        
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword()));

        var user = userRepository.findByEmail(signInRequest.getEmail())
                .orElseThrow(new java.util.function.Supplier<IllegalArgumentException>() {
                    @Override
                    public IllegalArgumentException get() {
                        return new IllegalArgumentException("Invalid email or password.");
                    }
                });
        var jwt = jwtService.generateToken(user);

        return new JwtAuthenticationResponse(jwt, user.getRole(), user.getId());
    }

    @Override
    public Optional<User> loginUser(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (password.equals(user.getPassword())) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }
}