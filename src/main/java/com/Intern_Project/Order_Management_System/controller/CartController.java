package com.Intern_Project.Order_Management_System.controller;

import com.Intern_Project.Order_Management_System.service.CartService;
import com.Intern_Project.Order_Management_System.model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;


    private static final String url_cart_checkout="/{id}/checkout";
    private static final String url_id="{id}";

    @GetMapping(value = url_id)
    List<Cart> getCartByAccountId(@PathVariable Integer id){
        return cartService.findByAccountId(id);
    }

    @PostMapping()
    ResponseEntity<String> postCart(@RequestBody Cart cart){
        cartService.insertCart(cart);
        return new ResponseEntity("A new Product in a cart for Account Id "+cart.getAccountId(),HttpStatus.ACCEPTED);
    }

    @PutMapping()
    ResponseEntity<String> updateCartById(@RequestBody Cart cart){
         cartService.updateCart(cart);
         return new ResponseEntity("Cart with cart id "+cart.getId()+" has been updated",HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = url_id)
    ResponseEntity<String> deleteById(@PathVariable(value="id") Integer id){
        cartService.deleteByCartId(id);
        return new ResponseEntity("Cart with cart id "+id+" has been deleted",HttpStatus.ACCEPTED);
    }

    @PostMapping(value = url_cart_checkout)
    ResponseEntity<String> placeAnOrder(@PathVariable Integer id){
        return cartService.cartCheckout(id);
    }
}
