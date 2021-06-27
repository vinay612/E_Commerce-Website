package com.Intern_Project.Order_Management_System.service;

import com.Intern_Project.Order_Management_System.model.Cart;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CartService {

    void createTable();

    void insertCart(Cart cart);

    ResponseEntity<String> cartCheckout(Integer id);

    List<Cart> findByAccountId(Integer id);

    void deleteByCartId(Integer id);

    void deleteByAccountId(Integer id);

    void updateCart(Cart cart);

}
