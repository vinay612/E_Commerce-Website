package com.Intern_Project.Order_Management_System.service;

import com.Intern_Project.Order_Management_System.model.CartItem;
import com.Intern_Project.Order_Management_System.util.ResponseJson;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CartItemService {
    void createTable();

    void insertCartItem(CartItem cartItem);

    List<CartItem> findByAccountId(Integer id);

    void updateCartItem(CartItem cartItem);

    void deleteByCartItemId(Integer id);

    void deleteByAccountId(Integer id);

    ResponseEntity<ResponseJson> cartCheckout(Integer id);
}
