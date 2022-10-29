package com.yggdrasil.service;

import com.yggdrasil.databaseInterface.OrdersDatabase;
import com.yggdrasil.model.Orders;
import com.yggdrasil.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrdersService {

    private final OrdersDatabase ordersDatabase;

    @Autowired
    public OrdersService(OrdersDatabase ordersDatabase) {
        this.ordersDatabase = ordersDatabase;
    }

    public ResponseEntity<List<Orders>> getOrder(String email) {
        List<Orders> ordersList = new ArrayList<>(ordersDatabase.findByUserEmail(email));

        return new ResponseEntity<>(ordersList, HttpStatus.OK);


    }

    public void createOrder(Orders orders) {
        ordersDatabase.save(orders);
    }

    public void editOrder(Long id, Orders orders) {
        Orders order = ordersDatabase.findById(id).orElseThrow();

        order.setOrderValue(orders.getOrderValue());
        order.setUserId(orders.getUserId());
        order.setOrderDate(orders.getOrderDate());
        order.setStreet(orders.getStreet());
        order.setZipCode(orders.getZipCode());

        ordersDatabase.save(order);
    }

    public void deleteOrder(Long id) {
        ordersDatabase.deleteById(id);
    }
}
