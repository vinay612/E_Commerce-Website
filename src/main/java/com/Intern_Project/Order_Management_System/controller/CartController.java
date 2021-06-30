package com.Intern_Project.Order_Management_System.controller;

import com.Intern_Project.Order_Management_System.model.Cart;
import com.Intern_Project.Order_Management_System.model.CartItem;
import com.Intern_Project.Order_Management_System.service.CartItemService;
import com.Intern_Project.Order_Management_System.service.CartService;
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
    private CartItemService cartItemService;

    @Autowired
    private CartService cartService;

    private static final String URL_CART_CHECKOUT="/{id}/checkout";
    private static final String URL_ID="{id}";
    private static final String URL_PRICE_ID="/price/{id}";
    private static final String URL_ACCOUNT_ID="/account/{id}";

    @GetMapping(value = URL_PRICE_ID)
    Cart getCartByCartId(@PathVariable Integer id){
        return cartService.getCartByCartId(id);
    }

    @GetMapping(value = URL_ID)
    List<CartItem> getCartByAccountId(@PathVariable Integer id){
        return cartItemService.findByAccountId(id);
    }

    @PostMapping()
    ResponseEntity<ResponseJson> addCartItem(@RequestBody CartItem cartItem){
        cartItemService.insertCartItem(cartItem);
        return new ResponseEntity<>(new ResponseJson("A new Product in a cart for Account Id "+cartItem.getCartId()+" has been added"),HttpStatus.CREATED);
    }

    @PutMapping()
    ResponseEntity<ResponseJson> updateCartById(@RequestBody CartItem cartItem){
         cartItemService.updateCartItem(cartItem);
         return new ResponseEntity<>(new ResponseJson("Cart Item with id "+cartItem.getItemId()+" has been updated"),HttpStatus.OK);
    }

    @DeleteMapping(value = URL_ID)
    ResponseEntity<ResponseJson> deleteCartById(@PathVariable(value="id") Integer id){
        cartItemService.deleteByCartItemId(id);
        return new ResponseEntity<>(new ResponseJson("Cart Item with id "+id+" has been deleted"),HttpStatus.OK);
    }

    @DeleteMapping(value = URL_ACCOUNT_ID)
    ResponseEntity<ResponseJson> emptyCart( @PathVariable Integer id){
        cartItemService.deleteByAccountId(id);
        return new ResponseEntity<>(new ResponseJson("Cart of Account with Id "+id+" has been emptied"), HttpStatus.OK);
    }

    @PostMapping(value = URL_CART_CHECKOUT)
    ResponseEntity<ResponseJson> placeAnOrder(@PathVariable Integer id){
        return cartItemService.cartCheckout(id);
    }
}
