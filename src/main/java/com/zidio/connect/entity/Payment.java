package com.zidio.connect.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String studentEmail;

    private String transactionId;

    private Double amount;

    private String status; // SUCCESS, FAILED, PENDING

    @Temporal(TemporalType.TIMESTAMP)
    private Date paymentDate;
}
