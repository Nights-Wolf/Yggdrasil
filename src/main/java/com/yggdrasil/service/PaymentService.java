package com.yggdrasil.service;

import com.yggdrasil.databaseInterface.OrdersDatabase;
import com.yggdrasil.databaseInterface.PaymentDatabase;
import com.yggdrasil.model.Orders;
import com.yggdrasil.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    private final PaymentDatabase paymentDatabase;
    private final OrdersDatabase ordersDatabase;

    @Autowired
    public PaymentService(PaymentDatabase paymentDatabase, OrdersDatabase ordersDatabase) {
        this.paymentDatabase = paymentDatabase;
        this.ordersDatabase = ordersDatabase;
    }

    public ResponseEntity<List<Payment>> getPayments() {
        List<Payment> payments = paymentDatabase.findAll();

        return new ResponseEntity<>(payments, HttpStatus.OK);
    }

    public ResponseEntity<Payment> getPaymentById(Long id) {
        Orders orders = ordersDatabase.findById(id).orElseThrow();
        Payment payment = orders.getPaymentId();

        return new ResponseEntity<>(payment, HttpStatus.OK);
    }
}
