package com.zidio.connect.service;

import com.zidio.connect.dto.ApplicationResponseDto;
import com.zidio.connect.dto.RecruiterApplicationResponseDto;
import com.zidio.connect.entity.Application;
import com.zidio.connect.entity.Job;
import com.zidio.connect.entity.RecruiterProfile;
import com.zidio.connect.entity.StudentProfile;
import com.zidio.connect.repository.ApplicationRepository;
import com.zidio.connect.repository.JobRepository;
import com.zidio.connect.repository.StudentProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private StudentProfileRepository studentProfileRepository;

    @Autowired
    private JobRepository jobRepository;

    @Override
    public String applyToJob(Long jobId, Long studentId) {
        Optional<StudentProfile> studentOpt = studentProfileRepository.findById(studentId);
        Optional<Job> jobOpt = jobRepository.findById(jobId);

        if (studentOpt.isPresent() && jobOpt.isPresent()) {
            StudentProfile student = studentOpt.get();
            Job job = jobOpt.get();

            Optional<Application> existingApp = applicationRepository.findByJobAndStudent(job, student);
            if (existingApp.isPresent()) {
                return "You have already applied to this job.";
            }

            Application app = new Application();
            app.setJob(job);
            app.setStudent(student);
            app.setApplicationDate(new Date());
            app.setStatus("APPLIED");

            applicationRepository.save(app);
            return "Application submitted successfully!";
        }

        return "Invalid student or job ID.";
    }
    @Override
public List<ApplicationResponseDto> getApplicationsByStudentId(Long studentId) {
    Optional<StudentProfile> studentOpt = studentProfileRepository.findById(studentId);
    if (studentOpt.isEmpty()) return List.of();

    List<Application> applications = applicationRepository.findByStudent(studentOpt.get());

    return applications.stream().map(app -> {
        ApplicationResponseDto dto = new ApplicationResponseDto();
        dto.setApplicationId(app.getApplicationId());
        dto.setJobTitle(app.getJob().getTitle());
        dto.setCompanyName(app.getJob().getRecruiter().getCompanyName());
        dto.setStatus(app.getStatus());
        dto.setApplicationDate(app.getApplicationDate());
        return dto;
    }).toList();
}

@Override
public List<RecruiterApplicationResponseDto> getApplicationsByRecruiterId(Long recruiterId) {
    Optional<RecruiterProfile> recruiterOpt = recruiterRepository.findById(recruiterId);
    if (recruiterOpt.isEmpty()) return List.of();

    List<Job> recruiterJobs = jobRepository.findByRecruiter(recruiterOpt.get());
    List<RecruiterApplicationResponseDto> responseList = new ArrayList<>();

    for (Job job : recruiterJobs) {
        List<Application> applications = applicationRepository.findByJob(job);

        for (Application app : applications) {
            RecruiterApplicationResponseDto dto = new RecruiterApplicationResponseDto();
            dto.setApplicationId(app.getApplicationId());
            dto.setJobTitle(job.getTitle());
            dto.setApplicantName(app.getStudent().getFullName());
            dto.setApplicantEmail(app.getStudent().getEmail());
            dto.setResumeLink(app.getStudent().getResumeUrl());
            dto.setStatus(app.getStatus());
            dto.setApplicationDate(app.getApplicationDate());
            responseList.add(dto);
        }
    }

    return responseList;
}
@Override
public boolean updateApplicationStatus(Long applicationId, String status) {
    Optional<Application> appOpt = applicationRepository.findById(applicationId);
    if (appOpt.isEmpty()) return false;

    Application application = appOpt.get();

    // Optional: Validate allowed statuses
    List<String> allowedStatuses = List.of("APPLIED", "UNDER_REVIEW", "REJECTED", "SELECTED");
    if (!allowedStatuses.contains(status.toUpperCase())) return false;

    application.setStatus(status.toUpperCase());
    applicationRepository.save(application);
    return true;
}


}
