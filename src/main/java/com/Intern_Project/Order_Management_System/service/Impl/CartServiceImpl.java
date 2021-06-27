package com.Intern_Project.Order_Management_System.service.Impl;

import com.Intern_Project.Order_Management_System.service.CartService;
import com.Intern_Project.Order_Management_System.model.Cart;
import com.Intern_Project.Order_Management_System.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("cartService")
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Override
    public void createTable(){
        cartRepository.createTable();
    }
    @Override
    public void insertCart(Cart cart){
         cartRepository.insertCart(cart);
         return;
    }

    public List<Cart> findByAccountId(Integer id){
        return cartRepository.findCartByAccountId(id);
    }

    public void updateCart(Cart cart){
         cartRepository.updateCartById(cart);
         return;
    }

    public void deleteByAccountId(Integer id){
        cartRepository.deleteCartByCartId(id);
    }

    public void deleteAccountCart(List<Cart> cartList){
        cartRepository.deleteCartByAccountId(cartList);
    }
}
