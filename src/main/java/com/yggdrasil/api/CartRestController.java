package com.yggdrasil.api;

import com.yggdrasil.model.Cart;
import com.yggdrasil.model.CartItem;
import com.yggdrasil.model.Item;
import com.yggdrasil.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

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

    @GetMapping("/getItems/{token}")
    private ResponseEntity<List<CartItem>> getCartItems(@PathVariable("token") String token) {
        return cartService.getCartItems(token);
    }

    @GetMapping("/getByCartId/{id}")
    private ResponseEntity<List<Item>> getCartItemsByCartId(@PathVariable("id") Long id) {
        return cartService.getCartItemsByCartId(id);
    }

    @DeleteMapping("/cartItem/{id}")
    private void deleteCartItem(@PathVariable("id") Long id) {
        cartService.deleteCartItem(id);
    }

    @PutMapping("/adjustQuantity/{id}/{quantity}")
    private void adjustQuantity(@PathVariable Long id, @PathVariable("quantity") Integer quantity) {
        cartService.adjustQuantity(id, quantity);
    }
}
