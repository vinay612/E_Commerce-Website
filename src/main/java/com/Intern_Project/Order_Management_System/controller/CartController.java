package com.Intern_Project.Order_Management_System.controller;

import com.Intern_Project.Order_Management_System.service.CartService;
import com.Intern_Project.Order_Management_System.model.Cart;
import com.Intern_Project.Order_Management_System.util.ResponseJson;
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
    ResponseEntity<ResponseJson> postCart(@RequestBody Cart cart){
        cartService.insertCart(cart);
        return new ResponseEntity<>(new ResponseJson("A new Product in a cart for Account Id "+cart.getAccountId()+" has been added"),HttpStatus.CREATED);
    }

    @PutMapping()
    ResponseEntity<ResponseJson> updateCartById(@RequestBody Cart cart){
         cartService.updateCart(cart);
         return new ResponseEntity<>(new ResponseJson("Cart with cart id "+cart.getCartId()+" has been updated"),HttpStatus.OK);
    }

    @DeleteMapping(value = URL_ID)
    ResponseEntity<ResponseJson> deleteCartById(@PathVariable(value="id") Integer id){
        cartService.deleteByCartId(id);
        return new ResponseEntity<>(new ResponseJson("Cart with cart id "+id+" has been deleted"),HttpStatus.OK);
    }

    @DeleteMapping(value = URL_ACCOUNT_ID)
    ResponseEntity<ResponseJson> emptyCart( @PathVariable Integer id){
        cartService.deleteByAccountId(id);
        return new ResponseEntity<>(new ResponseJson("Cart of Account with Id "+id+" has been emptied"), HttpStatus.OK);
    }

    @PostMapping(value = URL_CART_CHECKOUT)
    ResponseEntity<ResponseJson> placeAnOrder(@PathVariable Integer id){
        return cartService.cartCheckout(id);
    }
}
