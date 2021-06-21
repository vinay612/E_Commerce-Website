package com.Intern_Project.Order_Management_System;

import com.Intern_Project.Order_Management_System.service.AccountService;
import com.Intern_Project.Order_Management_System.service.CartService;
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

	public static void main(String[] args) {
		SpringApplication.run(OrderManagementSystemApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
/*
		Account account=new Account();
		account.setAccountId(1);
		account.setAddress("1");
		account.setEmailId("qw");
		account.setFirstName("qwqq");
		account.setLastName("yui");
		account.setPassword("123");
		account.setPhoneNumber("12345");
		account.setUserName("90");
		System.out.println(this.accountRepositoryImpl.createAccount(account));
		*/
		accountService.createTable();
		cartService.createTable();
	}
}
