package com.zidio.connect.controller;

import com.zidio.connect.dto.JobRequestDto;
import com.zidio.connect.dto.RecruiterJobResponseDto;
import com.zidio.connect.entity.Job;
import com.zidio.connect.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    @GetMapping
    public ResponseEntity<List<Job>> getAllJobs() {
        return ResponseEntity.ok(jobService.getAllJobs());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Job>> searchJobs(@RequestParam String keyword) {
        return ResponseEntity.ok(jobService.searchJobs(keyword));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id) {
        return ResponseEntity.ok(jobService.getJobById(id));
    }

    @PostMapping
    public ResponseEntity<Job> createJob(@RequestBody Job job) {
        return ResponseEntity.ok(jobService.createJob(job));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Job> updateJob(@PathVariable Long id, @RequestBody Job job) {
        return ResponseEntity.ok(jobService.updateJob(id, job));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id) {
        jobService.deleteJob(id);
        return ResponseEntity.ok("Job deleted successfully.");
    }

    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<Job>> getJobsByCompanyId(@PathVariable Long companyId) {
        return ResponseEntity.ok(jobService.getJobsByCompanyId(companyId));
    }

    @GetMapping("/user/email")
    public ResponseEntity<List<Job>> getJobsByUserEmail(@RequestParam String email) {
        return ResponseEntity.ok(jobService.getJobsByUserEmail(email));
    }

    @GetMapping("/user/fullname")
    public ResponseEntity<List<Job>> getJobsByUserFullName(@RequestParam String fullName) {
        return ResponseEntity.ok(jobService.getJobsByUserFullName(fullName));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Job>> getJobsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(jobService.getJobsByUserId(userId));
    }

    @GetMapping("/type")
    public ResponseEntity<List<Job>> getJobsByType(@RequestParam String type) {
        return ResponseEntity.ok(jobService.getJobsByType(type));
    }

    @GetMapping("/location")
    public ResponseEntity<List<Job>> getJobsByLocation(@RequestParam String location) {
        return ResponseEntity.ok(jobService.getJobsByLocation(location));
    }

    @GetMapping("/salary")
    public ResponseEntity<List<Job>> getJobsBySalaryRange(@RequestParam Double min, @RequestParam Double max) {
        return ResponseEntity.ok(jobService.getJobsBySalaryRange(min, max));
    }

    @GetMapping("/posted")
    public ResponseEntity<List<Job>> getJobsByPostedDateRange(@RequestParam String start, @RequestParam String end) {
        return ResponseEntity.ok(jobService.getJobsByPostedDateRange(start, end));
    }

    @GetMapping("/skills")
    public ResponseEntity<List<Job>> getJobsBySkills(@RequestParam String skill) {
        return ResponseEntity.ok(jobService.getJobsBySkillsRequired(skill));
    }

    @GetMapping("/education")
    public ResponseEntity<List<Job>> getJobsByEducation(@RequestParam String education) {
        return ResponseEntity.ok(jobService.getJobsByEducationRequired(education));
    }

    @GetMapping("/experience")
    public ResponseEntity<List<Job>> getJobsByExperience(@RequestParam String experience) {
        return ResponseEntity.ok(jobService.getJobsByExperienceRequired(experience));
    }

    @GetMapping("/category")
    public ResponseEntity<List<Job>> getJobsByCategory(@RequestParam String category) {
        return ResponseEntity.ok(jobService.getJobsByJobCategory(category));
    }

    @GetMapping("/description")
    public ResponseEntity<List<Job>> getJobsByDescription(@RequestParam String description) {
        return ResponseEntity.ok(jobService.getJobsByDescription(description));
    }

    @GetMapping("/title")
    public ResponseEntity<List<Job>> getJobsByTitle(@RequestParam String title) {
        return ResponseEntity.ok(jobService.getJobsByTitle(title));
    }

    @GetMapping("/resume")
    public ResponseEntity<List<Job>> getJobsByResumeUrl(@RequestParam String resumeUrl) {
        return ResponseEntity.ok(jobService.getJobsByResumeUrl(resumeUrl));
    }

    @GetMapping("/fullname")
    public ResponseEntity<List<Job>> getJobsByFullName(@RequestParam String fullName) {
        return ResponseEntity.ok(jobService.getJobsByFullName(fullName));
    }

    @GetMapping("/edu")
    public ResponseEntity<List<Job>> getJobsByEducationRequired(@RequestParam String education) {
        return ResponseEntity.ok(jobService.getJobsByEducationRequired(education));
    }

    @GetMapping("/skill")
    public ResponseEntity<List<Job>> getJobsBySkillsRequired(@RequestParam String skill) {
        return ResponseEntity.ok(jobService.getJobsBySkillsRequired(skill));
    }
    @PostMapping("/post")
public ResponseEntity<?> postJob(@RequestBody JobRequestDto jobRequestDto) {
    Job createdJob = jobService.postJob(jobRequestDto);
    if (createdJob == null) {
        return ResponseEntity.badRequest().body("Invalid recruiter ID.");
    }
    return ResponseEntity.ok(createdJob);
}
@GetMapping("/recruiter/{recruiterId}")
public ResponseEntity<?> getJobsForRecruiter(@PathVariable Long recruiterId) {
    List<RecruiterJobResponseDto> jobs = jobService.getJobsByRecruiterId(recruiterId);
    if (jobs.isEmpty()) {
        return ResponseEntity.status(404).body("No jobs found for this recruiter.");
    }
    return ResponseEntity.ok(jobs);
}

}
