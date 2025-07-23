package com.zidio.connect.service;

import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.zidio.connect.dto.StudentProfileDto;
import com.zidio.connect.dto.UpdateStudentProfileRequest;
import com.zidio.connect.entity.StudentProfile;
import com.zidio.connect.entity.User;

import io.jsonwebtoken.io.IOException;

public interface StudentService {
    StudentProfileDto getStudentProfile(User user);
    StudentProfileDto updateStudentProfile(User user, UpdateStudentProfileRequest request);
    StudentProfileDto createStudentProfile(User user, StudentProfileDto studentProfileDto);
    void deleteStudentProfile(User user);
    StudentProfileDto getStudentProfileById(Long id);
    StudentProfileDto getStudentProfileByUserId(Long userId);
    StudentProfileDto getStudentProfileByEmail(String email);
    StudentProfileDto getStudentProfileByFullName(String fullName);
    StudentProfileDto getStudentProfileByResumeUrl(String resumeUrl);
    StudentProfileDto getStudentProfileByEducation(String education);
    StudentProfileDto getStudentProfileBySkills(String skills);
    StudentProfileDto getStudentProfileByLocation(String location);
     Optional<StudentProfile> updateResume(Long userId, MultipartFile resumeFile) throws IOException, java.io.IOException;
    Optional<StudentProfile> storeResume(User user, MultipartFile file);

    // Additional methods can be added as needed
}