package com.yggdrasil.service;

import com.yggdrasil.databaseInterface.ShipmentsDatabase;
import com.yggdrasil.model.Shipments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipmentService {

    private final ShipmentsDatabase shipmentsDatabase;

    @Autowired
    public ShipmentService(ShipmentsDatabase shipmentsDatabase) {
        this.shipmentsDatabase = shipmentsDatabase;
    }

    public ResponseEntity<List<Shipments>> getShipments() {
        List<Shipments> shipments = shipmentsDatabase.findAll();

        return new ResponseEntity<>(shipments, HttpStatus.OK);
    }
}
