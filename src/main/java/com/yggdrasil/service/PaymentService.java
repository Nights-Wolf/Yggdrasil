package com.yggdrasil.service;

import com.yggdrasil.databaseInterface.PaymentDatabase;
import com.yggdrasil.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    private final PaymentDatabase paymentDatabase;

    @Autowired
    public PaymentService(PaymentDatabase paymentDatabase) {
        this.paymentDatabase = paymentDatabase;
    }

    public ResponseEntity<List<Payment>> getPayments() {
        List<Payment> payments = paymentDatabase.findAll();

        return new ResponseEntity<>(payments, HttpStatus.OK);
    }

    public ResponseEntity<Payment> getPaymentById(Long id) {
        Payment payment = paymentDatabase.findById(id).orElseThrow();

        return new ResponseEntity<>(payment, HttpStatus.OK);
    }
}
