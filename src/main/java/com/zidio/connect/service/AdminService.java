package com.zidio.connect.service;

import com.zidio.connect.dto.JobResponseDto;
import com.zidio.connect.dto.RecruiterResponseDto;
import com.zidio.connect.dto.StudentResponseDto;
import com.zidio.connect.entity.Admin;

import java.util.List;

public interface AdminService {
    List<Admin> getAllAdmins();
    Admin getAdminByEmail(String email);
List<StudentResponseDto> getAllStudents();
    List<RecruiterResponseDto> getAllRecruiters();
List<JobResponseDto> getAllJobs();
void deleteStudent(Long studentId);
void deleteRecruiter(Long recruiterId);
void deleteJob(Long jobId);
    String login(String email, String password);


}
