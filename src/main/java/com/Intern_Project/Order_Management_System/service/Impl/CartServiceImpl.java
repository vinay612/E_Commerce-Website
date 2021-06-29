package com.Intern_Project.Order_Management_System.service.Impl;

import com.Intern_Project.Order_Management_System.model.Order;
import com.Intern_Project.Order_Management_System.model.OrderItem;
import com.Intern_Project.Order_Management_System.service.CartService;
import com.Intern_Project.Order_Management_System.model.Cart;
import com.Intern_Project.Order_Management_System.repository.CartRepository;
import com.Intern_Project.Order_Management_System.service.OrderItemService;
import com.Intern_Project.Order_Management_System.service.OrderService;
import com.Intern_Project.Order_Management_System.util.ResponseJson;
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

    public static int orderId=99;

    @Override
    public void createTable(){
        cartRepository.createTable();
    }
    @Override
    public void insertCart(Cart cart){
        List<Cart> cartList=cartRepository.findCartByAccountId(cart.getAccountId());
        int counter=0;
        for(Cart cart1: cartList){
            if(cart1.getProductId() == cart.getProductId()){
                cart.setQuantity(cart.getQuantity()+cart1.getQuantity());
                cart.setCartId(cart1.getCartId());
                cartRepository.updateCartById(cart);
                counter=1;
                break;
            }
        }
        if(counter==0)
            cartRepository.insertCart(cart);
    }

    public List<Cart> findByAccountId(Integer id){
        return cartRepository.findCartByAccountId(id);
    }

    public void updateCart(Cart cart){
         cartRepository.updateCartById(cart);
    }

    public void deleteByCartId(Integer id){
        cartRepository.deleteCartByCartId(id);
    }

    public void deleteByAccountId(Integer id){
        cartRepository.deleteCartByAccountId(cartRepository.findCartByAccountId(id));

    }

    public ResponseEntity<ResponseJson> cartCheckout(Integer id){

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

        orderId++;
        //final int orderId = orderService.findMaxOrderIdForAccountId(accountId).getOrderId();

        carts.forEach(cart -> {
            OrderItem orderItem=new OrderItem();
            orderItem.setOrderId(orderId);
            orderItem.setProductId(cart.getProductId());
            orderItem.setQuantity(cart.getQuantity());
            orderItem.setPrice(cart.getTotalPrice());
            orderItemList.add(orderItem);
        });
        orderItemService.addOrderItem(orderItemList);

        cartRepository.deleteCartByAccountId(carts);
        return new ResponseEntity<>(new ResponseJson("Order for Account Id "+carts.get(0).getAccountId()+" has been placed. "), HttpStatus.ACCEPTED);
    }
}
