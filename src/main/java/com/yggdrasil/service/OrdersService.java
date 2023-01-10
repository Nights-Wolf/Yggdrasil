package com.yggdrasil.service;

import com.yggdrasil.databaseInterface.CartDatabase;
import com.yggdrasil.databaseInterface.OrdersDatabase;
import com.yggdrasil.model.Cart;
import com.yggdrasil.model.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrdersService {

    private final OrdersDatabase ordersDatabase;
    private final CartDatabase cartDatabase;

    @Autowired
    public OrdersService(OrdersDatabase ordersDatabase, CartDatabase cartDatabase) {
        this.ordersDatabase = ordersDatabase;
        this.cartDatabase = cartDatabase;
    }

    public ResponseEntity<List<Orders>> getOrder(String email) {
        List<Orders> ordersList = ordersDatabase.findByUserEmail(email);

        return new ResponseEntity<>(ordersList, HttpStatus.OK);


    }

    public ResponseEntity<Orders> getOrderByCartId(Long id) {
        Cart cart = cartDatabase.findById(id).orElseThrow();
        Orders order = ordersDatabase.findByCartId(cart);

        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    public void createOrder(Orders orders) {
        ordersDatabase.save(orders);
    }

    public void editOrder(Long id, Orders orders) {
        Orders order = ordersDatabase.findById(id).orElseThrow();

        order.setOrderValue(orders.getOrderValue());
        order.setOrderDate(orders.getOrderDate());
        order.setStreet(orders.getStreet());
        order.setZipCode(orders.getZipCode());

        ordersDatabase.save(order);
    }

    public void deleteOrder(Long id) {
        ordersDatabase.deleteById(id);
    }
}
