package com.zidio.connect.dto;

import lombok.Data;

import java.util.Date;

@Data
public class RecruiterJobResponseDto {
    private Long jobId;
    private String title;
    private String type;
    private String location;
    private Date deadline;
    private Date postedAt;
    private int totalApplications;
}
