package com.zidio.connect.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.zidio.connect.dto.StudentProfileDto;
import com.zidio.connect.dto.UpdateStudentProfileRequest;
import com.zidio.connect.entity.StudentProfile;
import com.zidio.connect.entity.User;
import com.zidio.connect.repository.StudentProfileRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentProfileRepository studentProfileRepository;
    private final Cloudinary cloudinary;

    @Override
    @Transactional(readOnly = true)
    public StudentProfileDto getStudentProfile(User user) {
        StudentProfile profile = studentProfileRepository.findByUser(user);
        if (profile == null) {
        }
        return mapToDto(profile);
    }

    @Override
    @Transactional
    public StudentProfileDto updateStudentProfile(User user, UpdateStudentProfileRequest request) {
        StudentProfile profile = studentProfileRepository.findByUser(user);
        if (profile == null) {
        }

        profile.setFullName(request.getFullName());
        profile.setHeadline(request.getHeadline());
        profile.setContactNumber(request.getContactNumber());
        profile.setLinkedinUrl(request.getLinkedinUrl());
        profile.setGithubUrl(request.getGithubUrl());

        StudentProfile updatedProfile = studentProfileRepository.save(profile);
        return mapToDto(updatedProfile);
    }

    @Override
    public StudentProfileDto createStudentProfile(User user, StudentProfileDto dto) {
        StudentProfile profile = new StudentProfile();

        profile.setUser(user);
        profile.setFullName(dto.getFullName());
        profile.setHeadline(dto.getHeadline());
        profile.setContactNumber(dto.getContactNumber());
        profile.setLinkedinUrl(dto.getLinkedinUrl());
        profile.setGithubUrl(dto.getGithubUrl());
        profile.setResumeUrl(dto.getResumeUrl());

        StudentProfile saved = studentProfileRepository.save(profile);
        return mapToDto(saved);
    }

    @Override
    public void deleteStudentProfile(User user) {
        StudentProfile profile = studentProfileRepository.findByUser(user);
        if (profile != null) {
            studentProfileRepository.delete(profile);
        } else {
        }
    }

    @Override
    public StudentProfileDto getStudentProfileById(Long id) {
        StudentProfile profile = studentProfileRepository.findById(id).get();
        return mapToDto(profile);
    }

    @Override
    public StudentProfileDto getStudentProfileByUserId(Long userId) {
        StudentProfile profile = studentProfileRepository.findByUserId(userId)
.get();        return mapToDto(profile);
    }

    @Override
    public StudentProfileDto getStudentProfileByEmail(String email) {
        StudentProfile profile = studentProfileRepository.findByUserEmail(email)
.get();        return mapToDto(profile);
    }

    @Override
    public StudentProfileDto getStudentProfileByFullName(String fullName) {
        StudentProfile profile = studentProfileRepository.findByFullName(fullName)
.get();        return mapToDto(profile);
    }

    @Override
    public StudentProfileDto getStudentProfileByResumeUrl(String resumeUrl) {
        StudentProfile profile = studentProfileRepository.findByResumeUrl(resumeUrl)
.get();        return mapToDto(profile);
    }

    @Override
    public StudentProfileDto getStudentProfileByEducation(String education) {
        StudentProfile profile = studentProfileRepository.findByEducation(education)
.get();        return mapToDto(profile);
    }

    @Override
    public StudentProfileDto getStudentProfileBySkills(String skills) {
        StudentProfile profile = studentProfileRepository.findBySkills(skills)
.get();        return mapToDto(profile);
    }

    @Override
    public StudentProfileDto getStudentProfileByLocation(String location) {
        StudentProfile profile = studentProfileRepository.findByLocation(location)
.get();        return mapToDto(profile);
    }

    @Override
    public Optional<StudentProfile> storeResume(User user, MultipartFile file) {
        try {
            validateFile(file);

            Map uploadResult = cloudinary.uploader().upload(file.getBytes(),
                ObjectUtils.asMap("folder", "resumes"));
            String fileUrl = (String) uploadResult.get("url");

            StudentProfile profile = studentProfileRepository.findByUser(user);
            if (profile == null) {
                profile = new StudentProfile();
                profile.setUser(user);
            }

            profile.setResumeUrl(fileUrl);
            StudentProfile savedProfile = studentProfileRepository.save(profile);
            return Optional.of(savedProfile);
        } catch (IOException e) {
            log.error("Failed to upload resume", e);
            throw new RuntimeException("Failed to store resume: " + e.getMessage());
        }
    }

    @Override
    public Optional<StudentProfile> updateResume(Long userId, MultipartFile resumeFile) throws IOException {
        validateFile(resumeFile);

        StudentProfile profile = studentProfileRepository.findByUserId(userId)
.get();
        Map uploadResult = cloudinary.uploader().upload(resumeFile.getBytes(),
            ObjectUtils.asMap("folder", "resumes"));
        String fileUrl = (String) uploadResult.get("url");

        profile.setResumeUrl(fileUrl);
        studentProfileRepository.save(profile);
        return Optional.of(profile);
    }

    // ----------- PRIVATE METHODS ------------

    private StudentProfileDto mapToDto(StudentProfile profile) {
        StudentProfileDto dto = new StudentProfileDto();
        dto.setUserId(profile.getUser_id());
        dto.setEmail(profile.getUser().getEmail());
        dto.setFullName(profile.getFullName());
        dto.setHeadline(profile.getHeadline());
        dto.setContactNumber(profile.getContactNumber());
        dto.setLinkedinUrl(profile.getLinkedinUrl());
        dto.setGithubUrl(profile.getGithubUrl());
        dto.setResumeUrl(profile.getResumeUrl());
        return dto;
    }

    private void validateFile(MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("Resume file is empty");
        }

        String contentType = file.getContentType();
        if (contentType == null || !contentType.equals("application/pdf")) {
            throw new IllegalArgumentException("Only PDF files are allowed");
        }

        long maxSizeMB = 5;
        if (file.getSize() > maxSizeMB * 1024 * 1024) {
            throw new IllegalArgumentException("File size exceeds " + maxSizeMB + "MB limit");
        }
    }
}
