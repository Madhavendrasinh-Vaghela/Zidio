package com.zidio.connect.dto;

import lombok.Data;
import java.util.Date;

@Data
public class ApplicationResponseDto {
    private Long applicationId;
    private String jobTitle;
    private String companyName;
    private String status;
    private Date applicationDate;
}
