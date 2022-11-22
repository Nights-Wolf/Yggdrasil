package com.yggdrasil.service;

import com.yggdrasil.databaseInterface.CartDatabase;
import com.yggdrasil.databaseInterface.CartItemDatabase;
import com.yggdrasil.databaseInterface.ItemDatabase;
import com.yggdrasil.model.Cart;
import com.yggdrasil.model.CartItem;
import com.yggdrasil.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Service
public class CartService {

    private final CartDatabase cartDatabase;
    private final CartItemDatabase cartItemDatabase;
    private final ItemDatabase itemDatabase;

    @Autowired
    public CartService(CartDatabase cartDatabase, CartItemDatabase cartItemDatabase, ItemDatabase itemDatabase) {
        this.cartDatabase = cartDatabase;
        this.cartItemDatabase = cartItemDatabase;
        this.itemDatabase = itemDatabase;
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

        List<CartItem> cartItems = cartItemDatabase.findByCartId(cart);

        for (CartItem cartItem: cartItems) {
            int itemQuantity = cartItem.getQuantity();
            sumQuantity = itemQuantity + sumQuantity;
        }
        return sumQuantity;
    }

    public void addItemToCart(CartItem cartItem, String token) {
        Cart cart = cartDatabase.findByToken(token);
        cartItem.setCartId(cart);

        cartItemDatabase.save(cartItem);
    }

    public ResponseEntity<List<CartItem>>getCartItems(String token) {
        Cart cart = cartDatabase.findByToken(token);

        List<CartItem> cartItems = cartItemDatabase.findByCartId(cart);

        return new ResponseEntity<>(cartItems, HttpStatus.OK);
    }

    public ResponseEntity<List<Item>>getCartItemsByCartId(Long id) {
        Cart cart = cartDatabase.findById(id).orElseThrow();

        List<CartItem> cartItemList = cartItemDatabase.findByCartId(cart);
        List<Item> itemList = new ArrayList<>();

        for (CartItem cartItem: cartItemList) {
            itemList.add(itemDatabase.findById(cartItem.getItemId().getId()).orElseThrow());
        }

        return new ResponseEntity<>(itemList, HttpStatus.OK);
    }

    public void deleteCartItem(Long id) {
        Item item = itemDatabase.findById(id).orElseThrow();
        List<CartItem> itemList = cartItemDatabase.findByItemId(item);
        for (CartItem cartItem: itemList) {
            cartItemDatabase.deleteById(cartItem.getId());
        }
    }

    public void adjustQuantity( Long id, Integer quantity) {
        CartItem cartItem = cartItemDatabase.findById(id).orElseThrow();

        if(quantity >= 1) {
            cartItem.setQuantity(cartItem.getQuantity() + 1);
        } else {
            cartItem.setQuantity(cartItem.getQuantity() - 1);
        }
        cartItemDatabase.save(cartItem);
    }

    public ResponseEntity<Item> getItemsByCartItemId(Long id) {
        CartItem cartItem = cartItemDatabase.findById(id).orElseThrow();

        Item item = itemDatabase.findById(cartItem.getItemId().getId()).orElseThrow();

        return new ResponseEntity<>(item, HttpStatus.OK);
    }
}
