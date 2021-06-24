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
    public Cart insertCart(Cart cart){
        return cartRepository.insertCart(cart);
    }

    public List<Cart> findByAccountId(Integer id){
        return cartRepository.findByAccountId(id);
    }

    public Cart updateCart(Cart cart){
        return cartRepository.updateCart(cart);
    }

    public void deleteById(Integer id){
        cartRepository.deleteById(id);
    }

    public void deleteAll(List<Cart> cartList){
        cartRepository.deleteAll(cartList);
    }
}
