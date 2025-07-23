package com.zidio.connect.dto;

import lombok.Data;

import java.util.Date;

@Data
public class RecruiterApplicationResponseDto {
    private Long applicationId;
    private String jobTitle;
    private String applicantName;
    private String applicantEmail;
    private String resumeLink;
    private String status;
    private Date applicationDate;
}
