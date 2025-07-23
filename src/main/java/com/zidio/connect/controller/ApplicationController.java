package com.zidio.connect.controller;

import com.zidio.connect.dto.ApplicationResponseDto;
import com.zidio.connect.dto.RecruiterApplicationResponseDto;
import com.zidio.connect.service.ApplicationService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @PostMapping("/apply")
    public ResponseEntity<String> applyToJob(@RequestParam Long jobId, @RequestParam Long studentId) {
        String result = applicationService.applyToJob(jobId, studentId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/student/{studentId}")
public ResponseEntity<?> getApplicationsForStudent(@PathVariable Long studentId) {
    List<ApplicationResponseDto> apps = applicationService.getApplicationsByStudentId(studentId);
    if (apps.isEmpty()) {
        return ResponseEntity.status(404).body("No applications found for this student.");
    }
    return ResponseEntity.ok(apps);
}
@GetMapping("/recruiter/{recruiterId}")
public ResponseEntity<?> getApplicationsForRecruiter(@PathVariable Long recruiterId) {
    List<RecruiterApplicationResponseDto> apps = applicationService.getApplicationsByRecruiterId(recruiterId);
    if (apps.isEmpty()) {
        return ResponseEntity.status(404).body("No applications found for this recruiter.");
    }
    return ResponseEntity.ok(apps);
}
@PutMapping("/{applicationId}/status")
public ResponseEntity<?> updateApplicationStatus(
        @PathVariable Long applicationId,
        @RequestParam String status) {

    boolean updated = applicationService.updateApplicationStatus(applicationId, status);

    if (!updated) {
        return ResponseEntity.status(400).body("Invalid application ID or status.");
    }
    return ResponseEntity.ok("Status updated successfully.");
}

    
}
