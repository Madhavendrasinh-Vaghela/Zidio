package com.zidio.connect.service;

import com.zidio.connect.entity.Payment;

import java.util.List;

public interface PaymentService {
    Payment makePayment(String studentEmail, Double amount);
    List<Payment> getAllPayments();
List<Payment> getPaymentsByEmail(String email);


}
