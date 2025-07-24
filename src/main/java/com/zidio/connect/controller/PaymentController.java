package com.zidio.connect.controller;

import com.zidio.connect.entity.Payment;
import com.zidio.connect.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.sql.Date;
import java.util.List;
import java.util.Map;

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

    @PreAuthorize("hasAuthority('STUDENT')")
@PostMapping("/verify")
public ResponseEntity<String> verifyPayment(@RequestBody Map<String, String> payload, Principal principal) {
    try {
        String razorpayOrderId = payload.get("razorpay_order_id");
        String razorpayPaymentId = payload.get("razorpay_payment_id");
        String razorpaySignature = payload.get("razorpay_signature");

        String email = principal.getName();

        // Verify signature
        String generatedSignature = hmacSHA256(razorpayOrderId + "|" + razorpayPaymentId, "YOUR_KEY_SECRET");

        if (!generatedSignature.equals(razorpaySignature)) {
            return ResponseEntity.status(400).body("Invalid signature");
        }

        // Save to DB
        Payment payment = new Payment();
        payment.setStudentEmail(email);
        payment.setAmount(999.0); // Optional: pass from frontend too
        payment.setTransactionId(razorpayPaymentId);
        payment.setStatus("SUCCESS");
        payment.setPaymentDate(new Date(0));

        payment.setRazorpayOrderId(razorpayOrderId);
        payment.setRazorpayPaymentId(razorpayPaymentId);
        payment.setRazorpaySignature(razorpaySignature);

        paymentService.save(payment);

        return ResponseEntity.ok("Payment verified and saved");
    } catch (Exception e) {
        return ResponseEntity.status(500).body("Error: " + e.getMessage());
    }
}

    private String hmacSHA256(String string, String string2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'hmacSHA256'");
    }


    
}
