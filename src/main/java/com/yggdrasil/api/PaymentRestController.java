package com.yggdrasil.api;

import com.yggdrasil.model.Payment;
import com.yggdrasil.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/payment")
@CrossOrigin(origins = "*")
public class PaymentRestController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentRestController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping
    private ResponseEntity<List<Payment>> getPayments() {
        return paymentService.getPayments();
    }

    @GetMapping("/{id}")
    private ResponseEntity<Payment> getPaymentById(@PathVariable("id") Long id) {
        return paymentService.getPaymentById(id);
    }
}
