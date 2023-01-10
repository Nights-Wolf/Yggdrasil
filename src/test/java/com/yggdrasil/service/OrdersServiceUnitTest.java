package com.yggdrasil.service;

import com.yggdrasil.databaseInterface.CartDatabase;
import com.yggdrasil.databaseInterface.OrdersDatabase;
import com.yggdrasil.model.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class OrdersServiceUnitTest {

    @Mock
    private OrdersDatabase ordersDatabase;

    @Mock
    private CartDatabase cartDatabase;

    @Mock
    private OrdersService ordersService;

    private Users users = new Users(1L, "Dawid" , "Całkowski", "koko", "dawinavi@gmail.com", "Podolska",
            "81-200", "Sopot", "Pomorskie", 1111L, true, true, true, true,
            true, true, true, "USER");

    private Cart cart = new Cart(1L, users, new Date(), "dwaoipdkawop");
    private Shipments shipments = new Shipments(1L, "inpost", 1.02F, 2);
    private Payment payment = new Payment(1L, "Paysafe");
    private Orders orders = new Orders(1L, 1, cart, "Dawid", "Całkowski", "dawid.calkowski@wp.pl", new Date(), "Lol", "11-222", "Gdynia", "pomorskie", "Avail", shipments, payment);

    @BeforeEach
    void setUp() {
        ordersService = new OrdersService(ordersDatabase, cartDatabase);
    }

    @AfterEach
    void tearDown() {
        ordersDatabase.deleteAll();
        cartDatabase.deleteAll();
    }

    @Test
    void getOrder() {
        List<Orders> ordersList = new ArrayList<>();
        ordersList.add(orders);

        Mockito.when(ordersDatabase.findByUserEmail(users.getEmail())).thenReturn((ArrayList<Orders>) ordersList);

        ResponseEntity<List<Orders>> response = ordersService.getOrder(users.getEmail());
        List<Orders> ordersResponseList = response.getBody();

        Assertions.assertEquals(ordersResponseList, ordersList);
    }

    @Test
    void getOrderByCartId() {
        Mockito.when(cartDatabase.findById(cart.getId())).thenReturn(Optional.ofNullable(cart));

        Mockito.when(ordersDatabase.findByCartId(cart)).thenReturn(orders);

        ResponseEntity<Orders> response = ordersService.getOrderByCartId(cart.getId());
        Orders responseOrder = response.getBody();

        Assertions.assertEquals(responseOrder, orders);
    }

    @Test
    void createOrder() {
        Mockito.when(ordersDatabase.save(orders)).thenReturn(orders);

        ordersService.createOrder(orders);

        ArgumentCaptor<Orders> ordersArgumentCaptor = ArgumentCaptor.forClass(Orders.class);
        Mockito.verify(ordersDatabase).save(ordersArgumentCaptor.capture());
        Orders capturedOrder = ordersArgumentCaptor.getValue();

        Assertions.assertEquals(capturedOrder, orders);
    }

    @Test
    void editOrder() {
        Orders ordersEdited = new Orders(1L, 2, cart, "Dawid", "Całkowski", "dawid.calkowski@wp.pl", new Date(), "Lol", "11-222", "Gdynia", "pomorskie", "Avail", shipments, payment);

        Mockito.when(ordersDatabase.findById(orders.getId())).thenReturn(Optional.of(orders));

        ordersService.editOrder(orders.getId(), ordersEdited);

        ArgumentCaptor<Orders> ordersArgumentCaptor = ArgumentCaptor.forClass(Orders.class);
        Mockito.verify(ordersDatabase).save(ordersArgumentCaptor.capture());
        Orders capturedOrder = ordersArgumentCaptor.getValue();

        Assertions.assertNotEquals(capturedOrder.getOrderValue(), 1);
    }

    @Test
    void deleteOrder() {
        Mockito.when(ordersDatabase.save(orders)).thenReturn(orders);

        ordersService.createOrder(orders);

        ordersService.deleteOrder(orders.getId());

        Mockito.verify(ordersDatabase).deleteById(cart.getId());
    }
}