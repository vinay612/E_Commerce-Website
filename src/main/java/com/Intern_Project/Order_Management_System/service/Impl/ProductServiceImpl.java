package com.Intern_Project.Order_Management_System.service.Impl;

import com.Intern_Project.Order_Management_System.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import com.Intern_Project.Order_Management_System.model.Product;
import com.Intern_Project.Order_Management_System.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public void createTable() {
        productRepository.createTable();
    }

    @Override
    public int  addProduct(Product product) {
        return productRepository.addProduct(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.getAllproducts();
    }

    @Override
    public Product getProductById(int id) {
        return productRepository.getProductById(id);
    }

    @Override
    public Product getProductByName(String name) {
        return productRepository.getProductByName(name);
    }

    @Override
    public int[] insertBatch(List<Product> products) {
        return productRepository.insertBatch(products);
    }

}
