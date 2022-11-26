package com.yggdrasil.service;

import com.yggdrasil.databaseInterface.OrdersDatabase;
import com.yggdrasil.databaseInterface.ShipmentsDatabase;
import com.yggdrasil.model.Orders;
import com.yggdrasil.model.Shipments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipmentService {

    private final ShipmentsDatabase shipmentsDatabase;
    private final OrdersDatabase ordersDatabase;

    @Autowired
    public ShipmentService(ShipmentsDatabase shipmentsDatabase, OrdersDatabase ordersDatabase) {
        this.shipmentsDatabase = shipmentsDatabase;
        this.ordersDatabase = ordersDatabase;
    }

    public ResponseEntity<List<Shipments>> getShipments() {
        List<Shipments> shipments = shipmentsDatabase.findAll();

        return new ResponseEntity<>(shipments, HttpStatus.OK);
    }

    public ResponseEntity<Shipments> getShipmentById(Long id) {
        Orders orders = ordersDatabase.findById(id).orElseThrow();
        Shipments shipments = orders.getShipmentsId();

        return new ResponseEntity<>(shipments, HttpStatus.OK);
    }
}
