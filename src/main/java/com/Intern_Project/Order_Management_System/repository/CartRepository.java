package com.Intern_Project.Order_Management_System.repository;

import com.Intern_Project.Order_Management_System.model.Cart;

import java.util.List;

public interface CartRepository {

    void createTable();
    void insertCart(Cart cart);
    List<Cart> findCartByAccountId(Integer id);
    void deleteCartByCartId(Integer id);

    void updateCartById(Cart cart);
    void deleteCartByAccountId(List<Cart> cartList);

}
