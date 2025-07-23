package com.zidio.connect.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "job_listing")
public class JobListing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(name = "discription") // Assuming your table has a typo; if it's meant to be "description", fix the DB schema
    private String discription;

    private String location;

    @Enumerated(EnumType.STRING)
    private JobType type;

    @ManyToOne
    @JoinColumn(name = "recruiter_id")  // FK pointing to User entity
    private User recruiter;

    // Getters and Setters

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDiscription() {
        return discription;
    }
    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public JobType getType() {
        return type;
    }
    public void setType(JobType type) {
        this.type = type;
    }

    public User getRecruiter() {
        return recruiter;
    }
    public void setRecruiter(User recruiter) {
        this.recruiter = recruiter;
    }
}
