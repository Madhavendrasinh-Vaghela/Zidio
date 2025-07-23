package com.zidio.connect.service;

import java.util.List;

import com.zidio.connect.dto.ApplicationResponseDto;
import com.zidio.connect.dto.RecruiterApplicationResponseDto;

public interface ApplicationService {
    String applyToJob(Long jobId, Long studentId);
    List<ApplicationResponseDto> getApplicationsByStudentId(Long studentId);
List<RecruiterApplicationResponseDto> getApplicationsByRecruiterId(Long recruiterId);
    boolean updateApplicationStatus(Long applicationId, String status);

}
