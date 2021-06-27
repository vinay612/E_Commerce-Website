package com.Intern_Project.Order_Management_System.controller;

import com.Intern_Project.Order_Management_System.model.Order;
import com.Intern_Project.Order_Management_System.service.CartService;
import com.Intern_Project.Order_Management_System.model.Cart;
import com.Intern_Project.Order_Management_System.service.OrderService;
import com.Intern_Project.Order_Management_System.util.Status;
import javafx.util.converter.LocalTimeStringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/{id}")
    List<Cart> getCartByAccountId(@PathVariable Integer id){
        return cartService.findByAccountId(id);
    }

    @PostMapping()
    ResponseEntity<String> postCart(@RequestBody Cart cart){
        cartService.insertCart(cart);
        return new ResponseEntity("A new Product in a cart for Account Id "+cart.getAccountId(),HttpStatus.ACCEPTED);
    }

    @PutMapping()
    ResponseEntity<String> putCart(@RequestBody Cart cart){
         cartService.updateCart(cart);
         return new ResponseEntity("Cart with cart id "+cart.getId()+" has been updated",HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteById(@PathVariable(value="id") Integer id){
        cartService.deleteByAccountId(id);
        return new ResponseEntity("Cart with cart id "+id+" has been deleted",HttpStatus.ACCEPTED);
    }

    @PostMapping("/{id}/checkout")
    ResponseEntity<String> cartToOrder(@PathVariable Integer id){
        List<Cart> carts=cartService.findByAccountId(id);
        List<Order> orderList=new ArrayList<Order>();
        carts.forEach(cart -> {
            Order order=new Order();
            order.setOrderId(cart.getId());
            order.setUserId((int) cart.getAccountId());
            order.setTotalPrice(cart.getTotalPrice());
            order.setPurchaseDate(LocalDate.now().toString());
            order.setPurchaseTime(LocalTime.now().toString());
            orderList.add(order);
        });
        orderService.insertOrderFromCart(orderList);
        cartService.deleteAccountCart(carts);
        return new ResponseEntity("Order for Account Id "+carts.get(0).getAccountId()+" has been placed ",HttpStatus.ACCEPTED);
    }
}
