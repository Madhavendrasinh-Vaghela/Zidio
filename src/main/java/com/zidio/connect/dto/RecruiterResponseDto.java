package com.zidio.connect.dto;

import lombok.Data;

@Data
public class RecruiterResponseDto {
    private Long recruiterId;
    private String fullName;
    private String email;
    private String company;
    private String position;
}
