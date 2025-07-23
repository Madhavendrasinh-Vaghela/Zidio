package com.zidio.connect.dto;

import lombok.Data;

@Data
public class UpdateStudentProfileRequest {
    private String fullName;
    private String headline;
    private String contactNumber;
    private String linkedinUrl;
    private String githubUrl;
    private String resumeUrl;
    private String skills;
    private String education;
    private String experience;
    private String location;
    private String profilePictureUrl;
    private String status;
    private String userType;
    private String userStatus;
    private String userIdString;
    private String userFullName;
    private String userEmail;
    private String userRole;
    private String userStatusString;
    private String userCreatedAt;



}