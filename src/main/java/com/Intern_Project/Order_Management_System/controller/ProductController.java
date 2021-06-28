package com.Intern_Project.Order_Management_System.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.Intern_Project.Order_Management_System.model.Product;
import com.Intern_Project.Order_Management_System.service.ProductService;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    private static final String URL_ADD_PRODUCT="/product";
    private static final String URL_PRODUCT_ID="/product/{id}";
    private static final String URL_PRODUCT_NAME="/productName/{name}";
    private static final String URL_ADD_OR_GET_PRODUCTS="/products";

    @PostMapping(value=URL_ADD_PRODUCT)
    ResponseEntity<String> addProduct(@RequestBody Product product)
    {
         productService.addProduct(product);
         return new ResponseEntity("A new product has been added", HttpStatus.CREATED);
    }

    @GetMapping(value=URL_ADD_OR_GET_PRODUCTS)
    List<Product> getAllProducts()
    {
        return productService.getAllProducts();
    }

    @GetMapping(value=URL_PRODUCT_ID)
    Product getProductById(@PathVariable("id") int id)
    {
        return productService.getProductById(id);
    }

    @GetMapping(value=URL_PRODUCT_NAME)
    Product getProductByName(@PathVariable("name") String name)
    {
        return productService.getProductByName(name);
    }

    @PostMapping(value=URL_ADD_OR_GET_PRODUCTS)
    ResponseEntity<String> insertProducts(@RequestBody List<Product> products){
        productService.insertBatch(products);
        return new ResponseEntity("Products have been successfully added",HttpStatus.CREATED);
    }
}
