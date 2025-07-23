package com.zidio.connect.dto;

import com.zidio.connect.entity.User;

import lombok.Data;

@Data
public class JwtAuthenticationResponse {
    private String token;
    private String role;
    private Long userId;

    public JwtAuthenticationResponse(Object jwt, String role, Long userId) {
        this.token = jwt != null ? jwt.toString() : null;
        this.role = role;
        this.userId = userId;
    }
    public JwtAuthenticationResponse(User user, String generatedJwtToken) {
        //TODO Auto-generated constructor stub
    }
    public void setToken(String token) {
        this.token = token;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }   
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {    
        this.userId = userId;
    }   
    @Override
    public String toString() {
        return "JwtAuthenticationResponse{" +
                "token='" + token + '\'' +
                ", role='" + role + '\'' +
                ", userId=" + userId +
                '}';
    }

}