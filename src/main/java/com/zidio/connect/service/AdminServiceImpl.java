package com.zidio.connect.service;

import com.zidio.connect.dto.JobResponseDto;
import com.zidio.connect.dto.RecruiterResponseDto;
import com.zidio.connect.dto.StudentResponseDto;
import com.zidio.connect.entity.Admin;
import com.zidio.connect.entity.RecruiterProfile;
import com.zidio.connect.entity.StudentProfile;
import com.zidio.connect.repository.AdminRepository;
import com.zidio.connect.repository.JobRepository;
import com.zidio.connect.repository.RecruiterProfileRepository;
import com.zidio.connect.repository.StudentProfileRepository;
import com.zidio.connect.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    @Override
    public Admin getAdminByEmail(String email) {
        return adminRepository.findByEmail(email).orElse(null);
    }
@Autowired
private StudentProfileRepository studentRepository;

@Override
public List<StudentResponseDto> getAllStudents() {
    List<StudentProfile> students = studentRepository.findAll();
    List<StudentResponseDto> response = new ArrayList<>();

    for (StudentProfile s : students) {
        StudentResponseDto dto = new StudentResponseDto();
        dto.setStudentId(s.getStudentId());
        dto.setFullName(s.getFullName());
        dto.setEmail(s.getEmail());
        dto.setUniversity(s.getUniversity());
        dto.setMajor(s.getMajor());
        dto.setYear(s.getYear());
        response.add(dto);
    }

    return response;
}
@Autowired
private RecruiterProfileRepository recruiterRepository;

@Override
public List<RecruiterResponseDto> getAllRecruiters() {
    List<RecruiterProfile> recruiters = recruiterRepository.findAll();
    List<RecruiterResponseDto> response = new ArrayList<>();

    for (RecruiterProfile r : recruiters) {
        RecruiterResponseDto dto = new RecruiterResponseDto();
        dto.setRecruiterId(r.getRecruiterId());
        dto.setFullName(r.getFullName());
        dto.setEmail(r.getEmail());
        dto.setCompany(r.getCompany());
        dto.setPosition(r.getPosition());
        response.add(dto);
    }

    return response;
}
@Autowired
private JobRepository jobRepository;

@Override
public List<JobResponseDto> getAllJobs() {
    List<com.zidio.connect.entity.Job> jobs = jobRepository.findAll();
    List<JobResponseDto> response = new ArrayList<>();

    for (com.zidio.connect.entity.Job job : jobs) {
        JobResponseDto dto = new JobResponseDto();
        dto.setJobId(job.getJobId());
        dto.setTitle(job.getTitle());
        dto.setDescription(job.getDescription());
        dto.setLocation(job.getLocation());
        dto.setType(job.getType());
        dto.setCompany(job.getRecruiter().getCompany());
        dto.setPostedBy(job.getRecruiter().getFullName());
        response.add(dto);
    }

    return response;
}
@Override
public void deleteStudent(Long studentId) {
    studentRepository.deleteById(studentId);
}

@Override
public void deleteRecruiter(Long recruiterId) {
    recruiterRepository.deleteById(recruiterId);
}

@Override
public void deleteJob(Long jobId) {
    jobRepository.deleteById(jobId);
}
@Autowired
private JwtUtil jwtUtil;

@Override
public String login(String email, String password) {
    Admin admin = adminRepository.findByEmail(email).orElse(null);
    if (admin != null && admin.getPassword().equals(password)) {
        return jwtUtil.generateToken(email);
    }
    return null;
}


}
