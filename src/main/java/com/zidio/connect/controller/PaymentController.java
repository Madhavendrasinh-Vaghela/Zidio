package com.zidio.connect.controller;

import com.zidio.connect.entity.Payment;
import com.zidio.connect.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PreAuthorize("hasAuthority('STUDENT')")
    @PostMapping("/pay")
    public ResponseEntity<Payment> pay(@RequestParam Double amount, Principal principal) {
        String email = principal.getName(); // Extracted from JWT
        Payment payment = paymentService.makePayment(email, amount);
        return ResponseEntity.ok(payment);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<Payment>> getAllPayments() {
        return ResponseEntity.ok(paymentService.getAllPayments());
    }

    
}
