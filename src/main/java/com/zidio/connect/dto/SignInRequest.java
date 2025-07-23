package com.zidio.connect.dto;

import lombok.Data;

@Data
public class SignInRequest {
    private String email;
    private String password;
    private String role;
    private String fullName;    
    private String companyName;
    private String username;    
    private String skills;
    private String jobTitle;
    private String jobDescription;
    private String jobPostDate;
    private Long recruiterId;
    private Long jobId;
    private Long applicationId;
    private String applicationStatus;
    private String applicationDate;
    private Long studentId;
    private Long applicationJobId;

    public String getEmail() {
        return email;
    }   
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;   

    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName; 
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {

        this.username = username;
    }   
    public String getSkills() {
        return skills;
    }
    public void setSkills(String skills) {
        this.skills = skills;

    }
    public String getJobTitle() {
        return jobTitle;
    }
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
    public String getJobDescription() {
        return jobDescription;
    }
    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }
    public String getJobPostDate() {
        return jobPostDate;
    }
    public void setJobPostDate(String jobPostDate) {
        this.jobPostDate = jobPostDate; 
    }
    public Long getRecruiterId() {
        return recruiterId;
    }
    public void setRecruiterId(Long recruiterId) {
        this.recruiterId = recruiterId;
    }
    public Long getJobId() {
        return jobId;
    }
    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }   
    public Long getApplicationId() {
        return applicationId;
    }
    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }
    public String getApplicationStatus() {
        return applicationStatus;
    }
    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }   
    public String getApplicationDate() {
        return applicationDate;
    }
    public void setApplicationDate(String applicationDate) {
        this.applicationDate = applicationDate;
    }   
    public Long getStudentId() {
        return studentId;
    }
    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }       
    public Long getApplicationJobId() {
        return applicationJobId;
    }
    public void setApplicationJobId(Long applicationJobId) {
        this.applicationJobId = applicationJobId;
    }   
    @Override
    public String toString() {
        return "SignInRequest{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +      
                ", fullName='" + fullName + '\'' +
                ", companyName='" + companyName + '\'' +            
                ", username='" + username + '\'' +          

                ", skills='" + skills + '\'' +          
                ", jobTitle='" + jobTitle + '\'' +
                ", jobDescription='" + jobDescription + '\'' +
                ", jobPostDate='" + jobPostDate + '\'' +
                ", recruiterId=" + recruiterId +
                ", jobId=" + jobId +
                ", applicationId=" + applicationId +
                ", applicationStatus='" + applicationStatus + '\'' +
                ", applicationDate='" + applicationDate + '\'' +
                ", studentId=" + studentId +
                ", applicationJobId=" + applicationJobId +
                '}';


    }
}