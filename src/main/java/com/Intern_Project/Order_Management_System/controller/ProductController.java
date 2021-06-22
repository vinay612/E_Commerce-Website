package com.Intern_Project.Order_Management_System.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.Intern_Project.Order_Management_System.model.Product;
import com.Intern_Project.Order_Management_System.service.ProductService;

import java.util.List;


public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/product")
    int  addProduct(@RequestBody Product product)
    {
        return productService.addProduct(product);
    }

    @GetMapping("/product")
    List<Product> getAllProducts()
    {
        return productService.getAllProducts();
    }

    @GetMapping("/product/{id}")
    Product getProductById(@PathVariable("id") int id)
    {
        return productService.getProductById(id);
    }

    @GetMapping("/productName/{name}")
    Product getProductByName(@PathVariable("name") String name)
    {
        return productService.getProductByName(name);
    }
}
