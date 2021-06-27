package com.Intern_Project.Order_Management_System;

import com.Intern_Project.Order_Management_System.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OrderManagementSystemApplication implements CommandLineRunner {

	@Autowired
	private AccountService accountService;
	@Autowired
	private CartService cartService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private ProductService productService;

	@Autowired
	private OrderItemService orderItemService;

	public static void main(String[] args) {
		SpringApplication.run(OrderManagementSystemApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		accountService.createTable();
		cartService.createTable();
		productService.createTable();
		orderService.createTable();
		orderItemService.createTable();

	}
}
