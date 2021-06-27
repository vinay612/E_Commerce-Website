package com.Intern_Project.Order_Management_System.model;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Cart {

    private int id;
    private int accountId;
    private int productId;
    private int quantity;
    private double totalPrice;

}
