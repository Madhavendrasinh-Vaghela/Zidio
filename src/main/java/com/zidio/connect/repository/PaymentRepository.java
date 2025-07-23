package com.zidio.connect.repository;

import com.zidio.connect.entity.Payment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findByStudentEmail(String email);
}
