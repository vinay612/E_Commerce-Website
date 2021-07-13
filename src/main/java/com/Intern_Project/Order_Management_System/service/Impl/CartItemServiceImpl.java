package com.Intern_Project.Order_Management_System.service.Impl;

import com.Intern_Project.Order_Management_System.exception.ProductQuantityException;
import com.Intern_Project.Order_Management_System.model.CartItem;
import com.Intern_Project.Order_Management_System.model.Order;
import com.Intern_Project.Order_Management_System.model.OrderItem;
import com.Intern_Project.Order_Management_System.repository.CartItemRepository;
import com.Intern_Project.Order_Management_System.service.*;
import com.Intern_Project.Order_Management_System.util.ResponseJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService {


    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private ProductService productService;

    public static int orderId=99;

    @Override
    public void createTable(){
        cartItemRepository.createTable();
    }

    @Override
    public void insertCartItem(CartItem cartItem) throws ProductQuantityException {
        List<CartItem> cartItemList=cartItemRepository.findCartItemsByCartId(cartItem.getCartId());
        int counter=0;
        for(CartItem cartItem1: cartItemList){
            if(cartItem1.getProductId() == cartItem.getProductId()){
                cartItem.setQuantity(cartItem.getQuantity()+cartItem1.getQuantity());
                cartItem.setItemId(cartItem1.getItemId());
                cartItemRepository.updateCartItemById(cartItem);
                counter=1;
                break;
            }
        }
        if(counter==0) {
            int minQuantity=productService.getProductById(cartItem.getProductId()).getMinQuantity();
            if(minQuantity>cartItem.getQuantity())
                throw new ProductQuantityException("Ordered Quantity of this Product cannot be less than "+ minQuantity);
            cartItemRepository.insertCartItem(cartItem);
        }
    }

    @Override
    public List<CartItem> findByAccountId(Integer id){
        return cartItemRepository.findCartItemsByCartId(id);
    }

    @Override
    public void updateCartItem(CartItem cartItem){
        cartItemRepository.updateCartItemById(cartItem);
    }

    @Override
    public void deleteByCartItemId(Integer id){
        cartItemRepository.deleteCartItemByItemId(id);
    }

    @Override
    public void deleteByAccountId(Integer id){
        cartItemRepository.deleteCartItemByCartId(id);

    }

    @Override
    public ResponseEntity<ResponseJson> cartCheckout(Integer id){


        int accountId;
        List<CartItem> cartItems=cartItemRepository.findCartItemsByCartId(id);
        List<OrderItem> orderItemList=new ArrayList<OrderItem>();

        double totalPrice=cartService.getCartByCartId(id).getTotalPrice();
        accountId=cartItems.get(0).getCartId();
        Order order=new Order(0,accountId, LocalDate.now().toString(), LocalTime.now().toString(),totalPrice);
        orderService.addOrder(order);

        orderId++;
        //final int orderId = orderService.findMaxOrderIdForAccountId(accountId).getOrderId();

        cartItems.forEach(cartItem -> {
            OrderItem orderItem=new OrderItem();
            orderItem.setOrderId(orderId);
            orderItem.setProductId(cartItem.getProductId());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getTotalPrice());
            orderItemList.add(orderItem);
        });
        orderItemService.addOrderItem(orderItemList);

        cartItemRepository.deleteCartItemByCartId(accountId);
        return new ResponseEntity<>(new ResponseJson("Order for Account Id "+accountId+" has been placed. "), HttpStatus.CREATED);
    }

}
