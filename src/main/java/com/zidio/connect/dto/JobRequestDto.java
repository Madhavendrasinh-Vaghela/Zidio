package com.zidio.connect.dto;

import lombok.Data;

import java.util.Date;

@Data
public class JobRequestDto {
    private String title;
    private String description;
    private String type; // Internship or Full-time
    private String location;
    private String requirements;
    private Date deadline;
    private Long recruiterId;
}
