package com.zidio.connect.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicationId;

    private String status; // e.g., "APPLIED", "SHORTLISTED", "REJECTED"

    @Temporal(TemporalType.DATE)
    private Date applicationDate;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private StudentProfile student;

    public static List<Job> findByJob(Job job2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByJob'");
    }
}