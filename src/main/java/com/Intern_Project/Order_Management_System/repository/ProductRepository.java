package com.Intern_Project.Order_Management_System.repository;

import java.util.List;
import com.Intern_Project.Order_Management_System.model.Product;

public interface ProductRepository {

    //create Table
    void createTable();

    // get list of all the products present.
    List<Product> findAllproducts();

    // get a particular product by id.
    Product findProductById(int id);

    // get a Product by name.
    Product findProductByName(String name);

    // post
    void insertProduct(Product product);

    //batch insert
    void insertBatch(List<Product> products);

    //update product details
    void updateProductById(Product product);

}
