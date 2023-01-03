package com.yggdrasil.service;

import com.yggdrasil.databaseInterface.OrdersDatabase;
import com.yggdrasil.databaseInterface.ShipmentsDatabase;
import com.yggdrasil.model.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ShipmentServiceUnitTest {

    @Mock
    private ShipmentsDatabase shipmentsDatabase;
    @Mock
    private OrdersDatabase ordersDatabase;
    @Mock
    private ShipmentService shipmentService;

    @BeforeEach
    void setUp() {
        shipmentService = new ShipmentService(shipmentsDatabase, ordersDatabase);
    }

    @AfterEach
    void tearDown() {
        ordersDatabase.deleteAll();
        shipmentsDatabase.deleteAll();
    }

    @Test
    void getShipments() {
        Shipments shipments = new Shipments(1L, "Inpost", 1.0F, 2);
        List<Shipments> shipmentsList = new ArrayList<>();
        shipmentsList.add(shipments);
        Mockito.when(shipmentsDatabase.findAll()).thenReturn(shipmentsList);

        ResponseEntity<List<Shipments>> returnedShipments = shipmentService.getShipments();
        List<Shipments> returnedList = returnedShipments.getBody();

        Assertions.assertEquals(returnedList, shipmentsList);
    }

    @Test
    void getShipmentById() {
        Users users = new Users(1L, "Dawid" , "Całkowski", "koko", "dawinavi@gmail.com", "Podolska",
                "81-200", "Sopot", "Pomorskie", 1111L, true, true, true, true,
                true, true, true, "USER");
        Cart cart = new Cart(1L, users, new Date(), "103120312031");
        Shipments shipments = new Shipments(1L, "inpost", 1.02F, 2);
        Payment payment = new Payment(1L, "Paysafe");
        Orders orders = new Orders(1L, 1, cart, "Dawid", "Całkowski", "dawid.calkowski@wp.pl", new Date(), "Lol", "11-222", "Gdynia", "pomorskie", "Avail", shipments, payment);

        Mockito.when(ordersDatabase.findById(orders.getId())).thenReturn(Optional.of(orders));

        ResponseEntity<Shipments> returnedValue = shipmentService.getShipmentById(orders.getId());
        Shipments returnedShipment = returnedValue.getBody();

        Assertions.assertEquals(returnedShipment, shipments);
    }
}