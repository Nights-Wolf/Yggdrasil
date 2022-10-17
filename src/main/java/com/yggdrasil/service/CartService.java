package com.yggdrasil.service;

import com.yggdrasil.databaseInterface.CartDatabase;
import com.yggdrasil.databaseInterface.CartItemDatabase;
import com.yggdrasil.model.Cart;
import com.yggdrasil.model.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

import java.util.Collections;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Service
public class CartService {

    private final CartDatabase cartDatabase;
    private final CartItemDatabase cartItemDatabase;

    @Autowired
    public CartService(CartDatabase cartDatabase, CartItemDatabase cartItemDatabase) {
        this.cartDatabase = cartDatabase;
        this.cartItemDatabase = cartItemDatabase;
    }

    public void createCart(Cart cart, HttpServletResponse response) {
        cartDatabase.save(cart);
        response.setContentType(APPLICATION_JSON_VALUE);
        response.setHeader("cart", cart.getToken());
    }

    public ResponseEntity<Cart> findByToken(String token) {
        Cart cart = cartDatabase.findByToken(token);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    public int getItemQuantity(String token) {
        int sumQuantity = 0;
        Cart cart = cartDatabase.findByToken(token);

        List<CartItem> cartItems = cartItemDatabase.findByCartId(cart.getId());

        for (CartItem cartItem: cartItems) {
            int itemQuantity = cartItem.getQuantity();
            sumQuantity = itemQuantity + sumQuantity;
        }
        return sumQuantity;
    }

    public void addItemToCart(CartItem cartItem, String token) {
        Cart cart = cartDatabase.findByToken(token);

        cartItem.setCartId(cart.getId());

        cartItemDatabase.save(cartItem);
    }

    public ResponseEntity<List<CartItem>>getCartItems(String token) {
        Cart cart = cartDatabase.findByToken(token);

        List<CartItem> cartItems = cartItemDatabase.findByCartId(cart.getId());

        return new ResponseEntity<>(cartItems, HttpStatus.OK);
    }

    public void deleteCartItem(Long id) {
        List<CartItem> itemList = cartItemDatabase.findByItemId(id);
        for (CartItem cartItem: itemList) {
            cartItemDatabase.deleteById(cartItem.getId());
        }
    }
}
