package com.Intern_Project.Order_Management_System.repository;

import java.util.List;
import com.Intern_Project.Order_Management_System.model.Product;

public interface ProductRepository {

    //create Table
    void createTable();

    // get list of all the products present.
    List<Product> getAllproducts();

    // get a particular product by id.
    Product getProductById(int id);

    // get list of Products sorted according to price;
    List<Product> sortByProductPrice();

    // get a Product by name.
    Product getProductByName(String name);

    // post
    void addProduct(Product product);

    //batch insert
    void insertBatch(List<Product> products);

}
