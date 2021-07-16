package com.Intern_Project.Order_Management_System.controller;

import com.Intern_Project.Order_Management_System.util.ResponseJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.Intern_Project.Order_Management_System.model.Product;
import com.Intern_Project.Order_Management_System.service.ProductService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    private static final String URL_PRODUCT_ID="{id}";
    private static final String URL_PRODUCT_NAME="/productName/{name}";


    @GetMapping()
    List<Product> getAllProducts()
    {
        return productService.getAllProducts();
    }

    @GetMapping(value=URL_PRODUCT_ID)
    Product getProductDetailsByProductId(@PathVariable("id") int id)
    {
        return productService.getProductById(id);
    }

    @GetMapping(value=URL_PRODUCT_NAME)
    Product getProductDetailsByName(@PathVariable("name") String name)
    {
        return productService.getProductByName(name);
    }

    @PostMapping()
    ResponseEntity<ResponseJson> insertProducts(@RequestBody List<Product> products){
        productService.addBatch(products);
        return new ResponseEntity<>(new ResponseJson("Products have been successfully added"),HttpStatus.CREATED);
    }

    @PutMapping()
    ResponseEntity<ResponseJson> updateProductDetails(@RequestBody Product product)
    {
        productService.updateProduct(product);
        return new ResponseEntity<>(new ResponseJson("Product with product id "+product.getProductId()+" has been updated."),HttpStatus.OK);
    }

    @DeleteMapping(value=URL_PRODUCT_ID)
    ResponseEntity<ResponseJson> deleteProductByProductId(@PathVariable("id") int id){
        productService.deleteProduct(id);
        return new ResponseEntity<>(new ResponseJson("Product with product id "+id+" has been deleted."),HttpStatus.OK);
    }
}
