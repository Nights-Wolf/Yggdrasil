package com.yggdrasil.api;

import com.yggdrasil.model.Shipments;
import com.yggdrasil.service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/shipments")
@CrossOrigin(origins = "*")
public class ShipmentsRestController {

    private final ShipmentService shipmentService;

    @Autowired
    public ShipmentsRestController(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }

    @GetMapping
    public ResponseEntity<List<Shipments>> getShipments() {
        return shipmentService.getShipments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Shipments> getShipmentById(@PathVariable("id") Long id) {
        return shipmentService.getShipmentById(id);
    }
}
