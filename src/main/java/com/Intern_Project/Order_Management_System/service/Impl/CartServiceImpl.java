package com.Intern_Project.Order_Management_System.service.Impl;

import com.Intern_Project.Order_Management_System.model.Order;
import com.Intern_Project.Order_Management_System.model.OrderItem;
import com.Intern_Project.Order_Management_System.service.CartService;
import com.Intern_Project.Order_Management_System.model.Cart;
import com.Intern_Project.Order_Management_System.repository.CartRepository;
import com.Intern_Project.Order_Management_System.service.OrderItemService;
import com.Intern_Project.Order_Management_System.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service("cartService")
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderItemService orderItemService;

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

    public void deleteByCartId(Integer id){
        cartRepository.deleteCartByCartId(id);
    }

    public void deleteByAccountId(Integer id){
        List<Cart> cartList=cartRepository.findCartByAccountId(id);
        cartRepository.deleteCartByAccountId(cartList);
        return;

    }

    public ResponseEntity<String> cartCheckout(Integer id){

        double totalPrice=0;
        int accountId;
        List<Cart> carts=cartRepository.findCartByAccountId(id);
        List<OrderItem> orderItemList=new ArrayList<OrderItem>();

        for(Cart cart: carts){
            totalPrice+=cart.getTotalPrice();
        }
        accountId=carts.get(0).getAccountId();
        Order order=new Order(0,accountId,LocalDate.now().toString(),LocalTime.now().toString(),totalPrice);
        orderService.insertOrder(order);

        final int orderId = orderService.findMaxOrderIdForAccountId(accountId).getOrderId();

        carts.forEach(cart -> {
            OrderItem orderItem=new OrderItem();
            orderItem.setOrderId(orderId);
            orderItem.setProductId(cart.getProductId());
            orderItem.setQuantity(cart.getAccountId());
            orderItem.setPrice(cart.getTotalPrice());
            orderItemList.add(orderItem);
        });
        orderItemService.addOrderItem(orderItemList);

        cartRepository.deleteCartByAccountId(carts);
        return new ResponseEntity("Order for Account Id "+carts.get(0).getAccountId()+" has been placed ", HttpStatus.ACCEPTED);
    }
}
