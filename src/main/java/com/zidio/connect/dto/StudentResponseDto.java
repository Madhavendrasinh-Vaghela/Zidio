package com.zidio.connect.dto;

import lombok.Data;

@Data
public class StudentResponseDto {
    private Long studentId;
    private String fullName;
    private String email;
    private String university;
    private String major;
    private String year;
}
