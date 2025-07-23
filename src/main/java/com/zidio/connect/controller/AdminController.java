package com.zidio.connect.controller;

import com.zidio.connect.dto.JobResponseDto;
import com.zidio.connect.dto.LoginRequest;
import com.zidio.connect.dto.RecruiterResponseDto;
import com.zidio.connect.dto.StudentResponseDto;
import com.zidio.connect.entity.Admin;
import com.zidio.connect.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping
    public ResponseEntity<?> getAllAdmins() {
        List<Admin> admins = adminService.getAllAdmins();
        return ResponseEntity.ok(admins);
    }

    @GetMapping("/email")
    public ResponseEntity<?> getAdminByEmail(@RequestParam String email) {
        Admin admin = adminService.getAdminByEmail(email);
        if (admin == null) {
            return ResponseEntity.status(404).body("Admin not found.");
        }
        return ResponseEntity.ok(admin);
    }
@GetMapping("/students")
public ResponseEntity<?> getAllStudents() {
    List<StudentResponseDto> students = adminService.getAllStudents();
    return ResponseEntity.ok(students);
}
@GetMapping("/recruiters")
public ResponseEntity<?> getAllRecruiters() {
    List<RecruiterResponseDto> recruiters = adminService.getAllRecruiters();
    return ResponseEntity.ok(recruiters);
}
@GetMapping("/jobs")
public ResponseEntity<?> getAllJobs() {
    List<JobResponseDto> jobs = adminService.getAllJobs();
    return ResponseEntity.ok(jobs);
}
@DeleteMapping("/students/{id}")
public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
    adminService.deleteStudent(id);
    return ResponseEntity.ok("Student deleted successfully");
}

@DeleteMapping("/recruiters/{id}")
public ResponseEntity<?> deleteRecruiter(@PathVariable Long id) {
    adminService.deleteRecruiter(id);
    return ResponseEntity.ok("Recruiter deleted successfully");
}

@DeleteMapping("/jobs/{id}")
public ResponseEntity<?> deleteJob(@PathVariable Long id) {
    adminService.deleteJob(id);
    return ResponseEntity.ok("Job deleted successfully");
}
@PostMapping("/login")
public ResponseEntity<?> login(@RequestBody LoginRequest request) {
    String token = adminService.login(request.getEmail(), request.getPassword());
    if (token == null) {
        return ResponseEntity.status(401).body("Invalid credentials");
    }
    return ResponseEntity.ok(new LoginResponse(token, "Login successful"));
}


}
