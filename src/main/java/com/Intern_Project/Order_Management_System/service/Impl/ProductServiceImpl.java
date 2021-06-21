package com.Intern_Project.Order_Management_System.service.Impl;

import com.Intern_Project.Order_Management_System.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import com.Intern_Project.Order_Management_System.model.Product;
import com.Intern_Project.Order_Management_System.repository.Impl.ProductRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepositoryImpl productRepositoryImpl;

    @Override
    public void createTable() {
        productRepositoryImpl.createTable();
    }

    @Override
    public int  addProduct(Product product) {
        return productRepositoryImpl.addProduct(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepositoryImpl.getAllproducts();
    }

    @Override
    public Product getProductById(int id) {
        return productRepositoryImpl.getProductById(id);
    }

    @Override
    public Product getProductByName(String name) {
        return productRepositoryImpl.getProductByName(name);
    }
}
