package com.benz.assignment.web.service;

import com.benz.assignment.web.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    Product getProductById(Product product);

    void deleteProduct(Product product) throws Exception;

    Product saveProduct(Product product) throws Exception;

    Product updateProduct(Product product) throws Exception;
}
