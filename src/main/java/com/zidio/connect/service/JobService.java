package com.zidio.connect.service;

import com.zidio.connect.dto.JobRequestDto;
import com.zidio.connect.dto.RecruiterJobResponseDto;
import com.zidio.connect.entity.Job;
import java.util.List;

public interface JobService {
    List<Job> getAllJobs();
    List<Job> searchJobs(String keyword);
    List<Job> getJobsByCompanyId(Long companyId);
    List<Job> getJobsByUserEmail(String email);
    List<Job> getJobsByUserFullName(String fullName);
    List<Job> getJobsByUserId(Long userId);
    List<Job> getJobsByType(String type);
    List<Job> getJobsByLocation(String location);
    List<Job> getJobsBySalaryRange(Double minSalary, Double maxSalary);
    List<Job> getJobsByPostedDateRange(String startDate, String endDate);
    List<Job> getJobsBySkillsRequired(String skill);
    List<Job> getJobsByEducationRequired(String education);
    List<Job> getJobsByExperienceRequired(String experience);
    List<Job> getJobsByJobCategory(String categoryName);
    Job getJobById(Long id);
    Job createJob(Job job);
    Job postJob(JobRequestDto jobRequestDto);
List<RecruiterJobResponseDto> getJobsByRecruiterId(Long recruiterId);

    Job updateJob(Long id, Job job);
    void deleteJob(Long id);
    List<Job> getJobsByDescription(String description);
    List<Job> getJobsByTitle(String title);
    List<Job> getJobsByResumeUrl(String resumeUrl);
    List<Job> getJobsByFullName(String fullName);
    List<Job> getJobsByEducation(String education);
    List<Job> getJobsBySkills(String skills);


}