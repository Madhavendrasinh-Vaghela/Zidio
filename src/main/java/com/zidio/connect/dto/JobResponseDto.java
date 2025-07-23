package com.zidio.connect.dto;

import lombok.Data;

@Data
public class JobResponseDto {
    private Long jobId;
    private String title;
    private String description;
    private String location;
    private String type;
    private String company;
    private String postedBy;
}
