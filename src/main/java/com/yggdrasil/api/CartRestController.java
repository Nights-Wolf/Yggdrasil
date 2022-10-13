package com.yggdrasil.api;

import com.yggdrasil.model.Cart;
import com.yggdrasil.model.CartItem;
import com.yggdrasil.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api/cart")
@CrossOrigin(origins = "*")
public class CartRestController {

    private final CartService cartService;

    @Autowired
    public CartRestController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/create")
    private void createCart(@RequestBody Cart cart, HttpServletResponse response) {
        cartService.createCart(cart, response);
    }

    @GetMapping("/{token}")
    private ResponseEntity<Cart> findByToken(@PathVariable("token") String token) {
        return cartService.findByToken(token);
    }

    @GetMapping("/itemsQuantity/{token}")
    private int getItemsQuantity(@PathVariable("token") String token) {
        return cartService.getItemQuantity(token);
    }

    @PostMapping("/addItem/{token}")
    private void addItemToCart(@RequestBody CartItem cartItem, @PathVariable("token") String token) {
        cartService.addItemToCart(cartItem, token);
    }
}
