package com.Intern_Project.Order_Management_System.service.Impl;

import com.Intern_Project.Order_Management_System.model.Cart;
import com.Intern_Project.Order_Management_System.service.CartService;
import com.Intern_Project.Order_Management_System.repository.CartRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


@Service("cartService")
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;



    public void createTable(){
        cartRepository.createTable();
    }

    public void insertCart(Integer id){
            cartRepository.insertCart(id);
    }

    public Cart getCartByCartId(Integer id){
        return cartRepository.getCartById(id);
    }
    public void updateCart(Integer id){
         cartRepository.updateCartById(id);
    }

    public void deleteCartById(Integer id){
        cartRepository.deleteCartById(id);
    }
}
