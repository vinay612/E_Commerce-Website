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
   private ProductRepository productRepository;

    @Override
    public void createTable() {
        productRepository.createTable();
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAllProducts();
    }

    @Override
    public Product getProductById(int id) {
        return productRepository.findProductById(id);
    }

    @Override
    public Product getProductByName(String name) {
        return productRepository.findProductByName(name);
    }

    @Override
    public void addBatch(List<Product> products) {
        productRepository.insertBatch(products);
    }

    @Override
    public void updateProduct(Product product) {
        productRepository.updateProductById(product);
    }
}
