package com.zidio.connect.service;

import com.zidio.connect.entity.Payment;
import com.zidio.connect.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Payment makePayment(String studentEmail, Double amount) {
        Payment payment = new Payment();
        payment.setStudentEmail(studentEmail);
        payment.setAmount(amount);
        payment.setTransactionId(UUID.randomUUID().toString());
        payment.setStatus("SUCCESS"); // Mocking success
        payment.setPaymentDate(new Date());

        return paymentRepository.save(payment);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
@Override
public List<Payment> getPaymentsByEmail(String email) {
    return paymentRepository.findByStudentEmail(email);
}


}
