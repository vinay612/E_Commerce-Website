package com.Intern_Project.Order_Management_System.controller;

import com.Intern_Project.Order_Management_System.service.CartService;
import com.Intern_Project.Order_Management_System.model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/cart/{id}")
    List<Cart> getCartByAccountId(@PathVariable Integer id){
        return cartService.findByAccountId(id);
    }

    @PostMapping("/cart")
    Cart postCart(@RequestBody Cart cart){
        return cartService.insertCart(cart);
    }

    @PutMapping("/cart")
    Cart putCart(@RequestBody Cart cart){
        return cartService.updateCart(cart);
    }
    @DeleteMapping("/cart/{id}")
    void deleteById(@PathVariable(value="id") Integer id){
        cartService.deleteById(id);
    }
}
