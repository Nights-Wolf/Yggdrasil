package com.yggdrasil.service;

import com.yggdrasil.databaseInterface.OrdersDatabase;
import com.yggdrasil.databaseInterface.PaymentDatabase;
import com.yggdrasil.model.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceUnitTest {

    @Mock
    private PaymentDatabase paymentDatabase;

    @Mock
    private OrdersDatabase ordersDatabase;

    @Mock
    private PaymentService paymentService;

    @BeforeEach
    void setUp() {
        paymentService = new PaymentService(paymentDatabase, ordersDatabase);
    }

    @AfterEach
    void tearDown() {
        paymentDatabase.deleteAll();
        ordersDatabase.deleteAll();
    }

    @Test
    void getPayments() {
        Payment payment = new Payment(1L, "Paysafe");

        List<Payment> paymentList = new ArrayList<>();
        paymentList.add(payment);

        Mockito.when(paymentDatabase.findAll()).thenReturn(paymentList);

        ResponseEntity<List<Payment>> response = paymentService.getPayments();
        List<Payment> responseList = response.getBody();

        Assertions.assertEquals(responseList, paymentList);
    }

    @Test
    void getPaymentById() {
        Users users = new Users(1L, "Dawid" , "Całkowski", "koko", "dawinavi@gmail.com", "Podolska",
                "81-200", "Sopot", "Pomorskie", 1111L, true, true, true, true,
                true, true, true, "USER");
        Cart cart = new Cart(1L, users, new Date(), "103120312031");
        Shipments shipments = new Shipments(1L, "inpost", 1.02F, 2);
        Payment payment = new Payment(1L, "Paysafe");
        Orders orders = new Orders(1L, 1, cart, "Dawid", "Całkowski", "dawid.calkowski@wp.pl", new Date(), "Lol", "11-222", "Gdynia", "pomorskie", "Avail", shipments, payment);

        Mockito.when(ordersDatabase.findById(orders.getId())).thenReturn(Optional.of(orders));

        ResponseEntity<Payment> response = paymentService.getPaymentById(orders.getId());
        Payment responsePayment = response.getBody();

        Assertions.assertEquals(responsePayment, payment);
    }
}