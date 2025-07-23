package com.zidio.connect.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jobId;

    private String title;
    private String description;

    @Temporal(TemporalType.DATE)
    private Date postDate;

    private String location;
    private String type;
    private Double salary;
    private String skills;
    private String educationRequired;
    private String experienceRequired;
    private String categoryName;
    private String resumeUrl;

    @ManyToOne
    @JoinColumn(name = "recruiter_id")
    private RecruiterProfile recruiter;

    public void setPostedAt(java.sql.Date date) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setPostedAt'");
    }

    public void setDeadline(Date deadline) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setDeadline'");
    }

    public void setRequirements(String requirements) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setRequirements'");
    }

    public Date getDeadline() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDeadline'");
    }

    public Date getPostedAt() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPostedAt'");
    }
}
