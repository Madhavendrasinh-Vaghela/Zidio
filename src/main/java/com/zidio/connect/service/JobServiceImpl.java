package com.zidio.connect.service;

import com.zidio.connect.dto.JobRequestDto;
import com.zidio.connect.dto.RecruiterJobResponseDto;
import com.zidio.connect.entity.Application;
import com.zidio.connect.entity.Job;
import com.zidio.connect.entity.RecruiterProfile;
import com.zidio.connect.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;

    @Override
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    @Override
    public List<Job> searchJobs(String keyword) {
        return jobRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(keyword, keyword);
    }

    @Override
    public List<Job> getJobsByCompanyId(Long companyId) {
        return jobRepository.findByCompanyId(companyId);
    }

    @Override
    public List<Job> getJobsByUserEmail(String email) {
        return jobRepository.findByUserEmail(email);
    }

    @Override
    public List<Job> getJobsByUserFullName(String fullName) {
        return jobRepository.findByUserFullName(fullName);
    }

    @Override
    public List<Job> getJobsByUserId(Long userId) {
        return jobRepository.findByUserId(userId);
    }

    @Override
    public List<Job> getJobsByType(String type) {
        return jobRepository.findByTypeIgnoreCase(type);
    }

    @Override
    public List<Job> getJobsByLocation(String location) {
        return jobRepository.findByLocationContainingIgnoreCase(location);
    }

    @Override
    public List<Job> getJobsBySalaryRange(Double minSalary, Double maxSalary) {
        return jobRepository.findBySalaryBetween(minSalary, maxSalary);
    }

    @Override
    public List<Job> getJobsByPostedDateRange(String startDate, String endDate) {
        return jobRepository.findByPostedDateBetween(startDate, endDate);
    }

    @Override
    public List<Job> getJobsBySkillsRequired(String skill) {
        return jobRepository.findBySkillsContainingIgnoreCase(skill);
    }

    @Override
    public List<Job> getJobsByEducationRequired(String education) {
        return jobRepository.findByEducationRequiredContainingIgnoreCase(education);
    }

    @Override
    public List<Job> getJobsByExperienceRequired(String experience) {
        return jobRepository.findByExperienceRequiredContainingIgnoreCase(experience);
    }

    @Override
    public List<Job> getJobsByJobCategory(String categoryName) {
        return jobRepository.findByCategoryNameIgnoreCase(categoryName);
    }

    @Override
    public Job getJobById(Long id) {
        return jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found with ID: " + id));
    }

    @Override
    public Job createJob(Job job) {
        return jobRepository.save(job);
    }

    @Override
    public Job updateJob(Long id, Job job) {
        Job existingJob = getJobById(id);
        existingJob.setTitle(job.getTitle());
        existingJob.setDescription(job.getDescription());
        existingJob.setLocation(job.getLocation());
        existingJob.setType(job.getType());
        existingJob.setSalary(job.getSalary());
        existingJob.setSkills(job.getSkills());
        existingJob.setEducationRequired(job.getEducationRequired());
        existingJob.setExperienceRequired(job.getExperienceRequired());
        existingJob.setCategoryName(job.getCategoryName());
        existingJob.setResumeUrl(job.getResumeUrl());
        return jobRepository.save(existingJob);
    }
    @Override
public Job postJob(JobRequestDto jobRequestDto) {
    Optional<RecruiterProfile> recruiterOpt = recruiterRepository.findById(jobRequestDto.getRecruiterId());
    if (recruiterOpt.isEmpty()) return null;

    Job job = new Job();
    job.setTitle(jobRequestDto.getTitle());
    job.setDescription(jobRequestDto.getDescription());
    job.setType(jobRequestDto.getType());
    job.setLocation(jobRequestDto.getLocation());
    job.setRequirements(jobRequestDto.getRequirements());
    job.setDeadline(jobRequestDto.getDeadline());
    job.setPostedAt(new Date(0));
    job.setRecruiter(recruiterOpt.get());

    return jobRepository.save(job);
}

@Override
public List<RecruiterJobResponseDto> getJobsByRecruiterId(Long recruiterId) {
    Optional<RecruiterProfile> recruiterOpt = recruiterRepository.findById(recruiterId);
    if (recruiterOpt.isEmpty()) return List.of();

    List<Job> jobs = jobRepository.findByRecruiter(recruiterOpt.get());
    List<RecruiterJobResponseDto> responseList = new ArrayList<>();

    for (Job job : jobs) {
        RecruiterJobResponseDto dto = new RecruiterJobResponseDto();
        dto.setJobId(job.getJobId());
        dto.setTitle(job.getTitle());
        dto.setType(job.getType());
        dto.setLocation(job.getLocation());
        dto.setDeadline(job.getDeadline());
        dto.setPostedAt(job.getPostedAt());

        int applicationCount = Application.findByJob(job).size();
        dto.setTotalApplications(applicationCount);

        responseList.add(dto);
    }

    return responseList;
}

    @Override
    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }

    @Override
    public List<Job> getJobsByDescription(String description) {
        return jobRepository.findByDescriptionContainingIgnoreCase(description);
    }

    @Override
    public List<Job> getJobsByTitle(String title) {
        return jobRepository.findByTitleContainingIgnoreCase(title);
    }

    @Override
    public List<Job> getJobsByResumeUrl(String resumeUrl) {
        return jobRepository.findByResumeUrl(resumeUrl);
    }

    @Override
    public List<Job> getJobsByFullName(String fullName) {
        return jobRepository.findByUserFullName(fullName);
    }

    @Override
    public List<Job> getJobsByEducation(String education) {
        return jobRepository.findByEducationRequiredContainingIgnoreCase(education);
    }

    @Override
    public List<Job> getJobsBySkills(String skills) {
        return jobRepository.findBySkillsContainingIgnoreCase(skills);
    }
}
