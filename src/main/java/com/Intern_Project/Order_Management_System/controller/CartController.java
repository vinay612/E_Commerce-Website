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


    private static final String URL_CART_CHECKOUT="/{id}/checkout";
    private static final String URL_ID="{id}";
    private static final String URL_ACCOUNT_ID="/account/{id}";

    @GetMapping(value = URL_ID)
    List<Cart> getCartByAccountId(@PathVariable Integer id){
        return cartService.findByAccountId(id);
    }

    @PostMapping()
    ResponseEntity<String> postCart(@RequestBody Cart cart){
        cartService.insertCart(cart);
        return new ResponseEntity("A new Product in a cart for Account Id "+cart.getAccountId()+" has been added",HttpStatus.ACCEPTED);
    }

    @PutMapping()
    ResponseEntity<String> updateCartById(@RequestBody Cart cart){
         cartService.updateCart(cart);
         return new ResponseEntity("Cart with cart id "+cart.getId()+" has been updated",HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = URL_ID)
    ResponseEntity<String> deleteCartById(@PathVariable(value="id") Integer id){
        cartService.deleteByCartId(id);
        return new ResponseEntity("Cart with cart id "+id+" has been deleted",HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = URL_ACCOUNT_ID)
    ResponseEntity<String> emptyCart( @PathVariable Integer id){
        cartService.deleteByAccountId(id);
        return new ResponseEntity("Cart of Account with Id "+id+" has been emptied", HttpStatus.ACCEPTED);
    }

    @PostMapping(value = URL_CART_CHECKOUT)
    ResponseEntity<String> placeAnOrder(@PathVariable Integer id){
        return cartService.cartCheckout(id);
    }
}
