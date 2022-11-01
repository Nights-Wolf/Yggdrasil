package com.yggdrasil.api;

import com.yggdrasil.model.Orders;
import com.yggdrasil.service.OrdersService;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/order")
@CrossOrigin(origins = "*")
public class OrdersRestController {

    @Autowired
    private OrdersService ordersService;

    @GetMapping("/getOrder/{email}")
    private ResponseEntity<List<Orders>> getOrder(@PathVariable String email) {
       return ordersService.getOrder(email);
    }

    @GetMapping("/{id}")
    private ResponseEntity<Orders> getOrderById(@PathVariable("id") Long id) {
        return ordersService.getOrderById(id);
    }

    @PostMapping
    private void createOrder(@RequestBody Orders orders) {
        ordersService.createOrder(orders);
    }

    @PutMapping("{id}")
    private void editOrder(@PathVariable("id") Long id, @RequestBody Orders orders) {
        ordersService.editOrder(id, orders);
    }

    @DeleteMapping("{id}")
    private void deleteOrder(@PathVariable("id") Long id) {
        ordersService.deleteOrder(id);
    }
}
