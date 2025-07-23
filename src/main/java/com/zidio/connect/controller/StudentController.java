package com.zidio.connect.controller;

import com.zidio.connect.dto.StudentProfileDto;
import com.zidio.connect.dto.UpdateStudentProfileRequest;
import com.zidio.connect.entity.StudentProfile;
import com.zidio.connect.entity.User;
import com.zidio.connect.service.StudentService;

import lombok.RequiredArgsConstructor;
import java.io.IOException;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/profile")
    @PreAuthorize("hasAuthority('STUDENT')")
    public ResponseEntity<StudentProfileDto> getMyProfile(@AuthenticationPrincipal User user) {
        StudentProfileDto profileDto = studentService.getStudentProfile(user);
        return ResponseEntity.ok(profileDto);
    }

    @PutMapping("/profile")
    @PreAuthorize("hasAuthority('STUDENT')")
    public ResponseEntity<StudentProfileDto> updateMyProfile(@AuthenticationPrincipal User user, @RequestBody UpdateStudentProfileRequest request) {
        StudentProfileDto updatedProfile = studentService.updateStudentProfile(user, request);
        return ResponseEntity.ok(updatedProfile);
    }
    @PostMapping("/profile")
    @PreAuthorize("hasAuthority('STUDENT')")
    public ResponseEntity<StudentProfileDto> createProfile(@AuthenticationPrincipal User user, @RequestBody
    StudentProfileDto studentProfileDto) {
        StudentProfileDto createdProfile = studentService.createStudentProfile(user, studentProfileDto);
        return ResponseEntity.ok(createdProfile);
    }       
    @DeleteMapping("/profile")
    @PreAuthorize("hasAuthority('STUDENT')")
    public ResponseEntity<Void> deleteMyProfile(@AuthenticationPrincipal User user) {
        studentService.deleteStudentProfile(user);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/profile/{id}")
    @PreAuthorize("hasAuthority('STUDENT')")
    public ResponseEntity<StudentProfileDto> getProfileById(@PathVariable Long id) {
        StudentProfileDto profileDto = studentService.getStudentProfileById(id);
        return ResponseEntity.ok(profileDto);
    }
    @GetMapping("/profile/user/{userId}")
    @PreAuthorize("hasAuthority('STUDENT')")
    public ResponseEntity<StudentProfileDto> getProfileByUserId(@PathVariable Long userId
    ) {
        StudentProfileDto profileDto = studentService.getStudentProfileByUserId(userId);
        return ResponseEntity.ok(profileDto);
    }   
    @GetMapping("/profile/email/{email}")
    @PreAuthorize("hasAuthority('STUDENT')")
    public ResponseEntity<StudentProfileDto> getProfileByEmail(@PathVariable String email) {
        StudentProfileDto profileDto = studentService.getStudentProfileByEmail(email);
        return ResponseEntity.ok(profileDto);
    }
    @GetMapping("/profile/fullname/{fullName}")
    @PreAuthorize("hasAuthority('STUDENT')")
    public ResponseEntity<StudentProfileDto> getProfileByFullName(@PathVariable String fullName) {
        StudentProfileDto profileDto = studentService.getStudentProfileByFullName(fullName);
        return ResponseEntity.ok(profileDto);
    }
    @GetMapping("/profile/resume/{resumeUrl}")
    @PreAuthorize("hasAuthority('STUDENT')")
    public ResponseEntity<StudentProfileDto> getProfileByResumeUrl(@PathVariable String resumeUrl
    ) {
        StudentProfileDto profileDto = studentService.getStudentProfileByResumeUrl(resumeUrl);
        return ResponseEntity.ok(profileDto);
    }
    @GetMapping("/profile/education/{education}")
    @PreAuthorize("hasAuthority('STUDENT')")
    public ResponseEntity<StudentProfileDto> getProfileByEducation(@PathVariable String education) {
        StudentProfileDto profileDto = studentService.getStudentProfileByEducation(education);
        return ResponseEntity.ok(profileDto);   
    }
    @GetMapping("/profile/skills/{skills}")
    @PreAuthorize("hasAuthority('STUDENT')")
    public ResponseEntity<StudentProfileDto> getProfileBySkills(@PathVariable String skills) {      
        StudentProfileDto profileDto = studentService.getStudentProfileBySkills(skills);
        return ResponseEntity.ok(profileDto);
    }
    @GetMapping("/profile/location/{location}")
    @PreAuthorize("hasAuthority('STUDENT')")
    public ResponseEntity<StudentProfileDto> getProfileByLocation(@PathVariable String location) {
        StudentProfileDto profileDto = studentService.getStudentProfileByLocation(location);
        return ResponseEntity.ok(profileDto);
    }
    @GetMapping("/profile")
    @PreAuthorize("hasAuthority('STUDENT')")        
    public ResponseEntity<StudentProfileDto> getProfile(@AuthenticationPrincipal User user) {
        StudentProfileDto profileDto = studentService.getStudentProfile(user);
        return ResponseEntity.ok(profileDto);
    }
    @PutMapping("/profile")
    @PreAuthorize("hasAuthority('STUDENT')")
    public ResponseEntity<StudentProfileDto> updateProfile(@AuthenticationPrincipal User user, @RequestBody
    UpdateStudentProfileRequest request) {
        StudentProfileDto updatedProfile = studentService.updateStudentProfile(user, request);
        return ResponseEntity.ok(updatedProfile);
    }

    @PostMapping("/profile/resume")
    @PreAuthorize("hasAuthority('STUDENT')")
    public ResponseEntity<?> uploadResume(@AuthenticationPrincipal User user, @RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please select a file to upload.");
        }
        studentService.storeResume(user, file);
        return ResponseEntity.ok("Resume uploaded successfully.");
    }

     @PostMapping("/profile/{userId}/resume")
    public ResponseEntity<?> uploadResume(@PathVariable Long userId, @RequestParam("file") MultipartFile file) {
        try {
            Optional<StudentProfile> updatedProfile = studentService.updateResume(userId, file);
            if (updatedProfile.isPresent()) {
                return new ResponseEntity<>("Resume uploaded successfully: " + updatedProfile.get().getResumeUrl(), HttpStatus.OK);
            }
            return new ResponseEntity<>("Profile not found", HttpStatus.NOT_FOUND);
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to upload resume", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}