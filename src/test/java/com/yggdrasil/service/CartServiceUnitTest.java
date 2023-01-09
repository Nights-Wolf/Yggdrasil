package com.yggdrasil.service;

import com.yggdrasil.databaseInterface.CartDatabase;
import com.yggdrasil.databaseInterface.CartItemDatabase;
import com.yggdrasil.databaseInterface.ItemDatabase;
import com.yggdrasil.databaseInterface.OrdersDatabase;
import com.yggdrasil.model.*;
import org.apache.catalina.connector.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({MockitoExtension.class})
class CartServiceUnitTest {

    @Mock
    private CartDatabase cartDatabase;

    @Mock
    private CartItemDatabase cartItemDatabase;

    @Mock
    private ItemDatabase itemDatabase;

    @Mock
    private OrdersDatabase ordersDatabase;

    @Mock
    private CartService cartService;

    private Users users = new Users(1L, "Dawid" , "Całkowski", "koko", "dawinavi@gmail.com", "Podolska",
                                                             "81-200", "Sopot", "Pomorskie", 1111L, true, true, true, true,
                                                             true, true, true, "USER");

    private Cart cart = new Cart(1L, users, new Date(), "dwaoipdkawop");

    @Mock
    private HttpServletResponse response;
    private final Category category = new Category(1L, "bursztynowe");
    private final Item item = new Item(1L, "Drzewko", "wadawd",
            new Date(), category, 122, "dawda", 1);
    private CartItem cartItem = new CartItem(1L, item, 2, new Date(), cart);
    private Shipments shipments = new Shipments(1L, "inpost", 1.02F, 2);
    private Payment payment = new Payment(1L, "Paysafe");
    private Orders orders = new Orders(1L, 1, cart, "Dawid", "Całkowski", "dawid.calkowski@wp.pl", new Date(), "Lol", "11-222", "Gdynia", "pomorskie", "Avail", shipments, payment);

    @BeforeEach
    void setUp() {
        cartService = new CartService(cartDatabase, cartItemDatabase, itemDatabase, ordersDatabase);
    }

    @AfterEach
    void tearDown() {
        cartDatabase.deleteAll();
        cartItemDatabase.deleteAll();
        itemDatabase.deleteAll();
        ordersDatabase.deleteAll();
    }

    @Test
    void createCart() {
        Mockito.when(cartDatabase.save(cart)).thenReturn(cart);

        cartService.createCart(cart, response);

        ArgumentCaptor<Cart> cartArgumentCaptor = ArgumentCaptor.forClass(Cart.class);

        Mockito.verify(cartDatabase).save(cartArgumentCaptor.capture());
        Cart capturedCart = cartArgumentCaptor.getValue();

        Assertions.assertEquals(capturedCart, cart);
    }

    @Test
    void findByToken() {
        Mockito.when(cartDatabase.findByToken(cart.getToken())).thenReturn(cart);

        ResponseEntity<Cart> response = cartService.findByToken(cart.getToken());
        Cart responseCart = response.getBody();

        Assertions.assertEquals(responseCart, cart);
    }

    @Test
    void getItemQuantity() {
        Mockito.when(cartDatabase.findByToken(cart.getToken())).thenReturn(cart);

        List<CartItem> cartItemsList = new ArrayList<>();
        cartItemsList.add(cartItem);
        Mockito.when(cartItemDatabase.findByCartId(cart)).thenReturn(cartItemsList);

        int response = cartService.getItemQuantity(cart.getToken());

        Assertions.assertEquals(response, cartItem.getQuantity());
    }

    @Test
    void addItemToCart() {
        Mockito.when(cartDatabase.findByToken(cart.getToken())).thenReturn(cart);

        Mockito.when(cartItemDatabase.save(cartItem)).thenReturn(cartItem);

        cartService.addItemToCart(cartItem, cart.getToken());

        ArgumentCaptor<CartItem> cartItemArgumentCaptor = ArgumentCaptor.forClass(CartItem.class);
        Mockito.verify(cartItemDatabase).save(cartItemArgumentCaptor.capture());
        CartItem capturedItem = cartItemArgumentCaptor.getValue();

        Assertions.assertEquals(capturedItem, cartItem);
    }

    @Test
    void getCartItems() {
        Mockito.when(cartDatabase.findByToken(cart.getToken())).thenReturn(cart);

        List<CartItem> cartItemList = new ArrayList<>();
        cartItemList.add(cartItem);

        Mockito.when(cartItemDatabase.findByCartId(cart)).thenReturn(cartItemList);

        ResponseEntity<List<CartItem>> response = cartService.getCartItems(cart.getToken());
        List<CartItem> cartItemsResponseList = response.getBody();

        Assertions.assertEquals(cartItemsResponseList, cartItemList);
    }

    @Test
    void getCartItemsByCartId() {
        Mockito.when(ordersDatabase.findById(orders.getId())).thenReturn(Optional.ofNullable(orders));

        List<CartItem> cartItemsList = new ArrayList<>();
        cartItemsList.add(cartItem);

        List<Item> itemList = new ArrayList<>();
        itemList.add(item);

        Mockito.when(cartItemDatabase.findByCartId(cart)).thenReturn(cartItemsList);

        Mockito.when(itemDatabase.findById(cartItem.getItemId().getId())).thenReturn(Optional.of(item));

        ResponseEntity<List<Item>> response = cartService.getCartItemsByCartId(cart.getId());
        List<Item> responseItemList = response.getBody();

        Assertions.assertEquals(responseItemList, itemList);
    }

    @Test
    void deleteCartItem() {
        Mockito.when(cartDatabase.save(cart)).thenReturn(cart);

        cartService.createCart(cart, response);
    }

    @Test
    void adjustQuantity() {
        Mockito.when(cartItemDatabase.findById(cartItem.getId())).thenReturn(Optional.ofNullable(cartItem));

        CartItem cartItemAfterChange = new CartItem(1L, item, 3, new Date(), cart);

        Mockito.when(cartItemDatabase.save(cartItem)).thenReturn(cartItemAfterChange);

        cartService.adjustQuantity(cartItem.getId(), 1);

        ArgumentCaptor<CartItem> cartItemArgumentCaptor = ArgumentCaptor.forClass(CartItem.class);
        Mockito.verify(cartItemDatabase).save(cartItemArgumentCaptor.capture());

        CartItem capturedCartItem = cartItemArgumentCaptor.getValue();

        Assertions.assertEquals(capturedCartItem.getQuantity(), cartItemAfterChange.getQuantity());
    }

    @Test
    void getItemsByCartItemId() {
        Mockito.when(cartItemDatabase.findById(cartItem.getId())).thenReturn(Optional.ofNullable(cartItem));

        Mockito.when(itemDatabase.findById(cartItem.getItemId().getId())).thenReturn(Optional.of(item));

        ResponseEntity<Item> response = cartService.getItemsByCartItemId(cartItem.getId());
        Item responseItem = response.getBody();

        Assertions.assertEquals(responseItem, item);
    }
}