package com.Intern_Project.Order_Management_System.repository;

import com.Intern_Project.Order_Management_System.model.CartItem;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface CartItemRepository {
    void createTable();

    void insertCartItem(CartItem cartItem);

    List<CartItem> findCartItemsByCartId(Integer id);

    void updateCartItemById(@NotNull CartItem cartItem);

    void deleteCartItemByItemId(Integer id);

    void deleteCartItemByCartId(Integer id);
}
