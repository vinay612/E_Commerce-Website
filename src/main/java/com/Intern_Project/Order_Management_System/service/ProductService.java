package com.Intern_Project.Order_Management_System.service;

import java.util.List;
import com.Intern_Project.Order_Management_System.model.Product;

public interface ProductService {

    //create Table
    void createTable();

    // add a new Product.
    void addProduct(Product product);

    // get a list of all Products.
    List<Product> getAllProducts();

    // get details of a particular product by id.
    Product getProductById(int id);

    //get details of a particular product by name.
    Product getProductByName(String name);

    //batch insert
    void insertBatch(List<Product> products);


}
