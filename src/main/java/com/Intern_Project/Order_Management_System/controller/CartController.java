package com.Intern_Project.Order_Management_System.controller;

import com.Intern_Project.Order_Management_System.model.Order;
import com.Intern_Project.Order_Management_System.service.CartService;
import com.Intern_Project.Order_Management_System.model.Cart;
import com.Intern_Project.Order_Management_System.service.OrderService;
import com.Intern_Project.Order_Management_System.util.Status;
import javafx.util.converter.LocalTimeStringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;

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

    @PostMapping("/cart/{id}/checkout")
    Status cartToOrder(@PathVariable Integer id){
        List<Cart> carts=cartService.findByAccountId(id);
        List<Order> orderList=new ArrayList<Order>();
        carts.forEach(cart -> {
            Order order=new Order();
            order.setOrderId(cart.getId());
            order.setUserId((int) cart.getAccountId());
            order.setProductId(cart.getProductId());
            order.setQuantity(cart.getQuantity());
            order.setTotalPrice(cart.getTotalPrice());
            order.setPurchaseDate(LocalDate.now().toString());
            order.setPurchaseTime(LocalTime.now().toString());
            orderList.add(order);
        });
        orderService.insertOrderFromCart(orderList);
        cartService.deleteAll(carts);
        return Status.SUCCESS;
    }
}
