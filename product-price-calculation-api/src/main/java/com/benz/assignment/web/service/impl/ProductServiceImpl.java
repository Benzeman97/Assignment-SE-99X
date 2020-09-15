package com.benz.assignment.web.service.impl;

import com.benz.assignment.web.dao.ProductDAO;
import com.benz.assignment.web.entity.Product;
import com.benz.assignment.web.exception.DataNotFoundException;
import com.benz.assignment.web.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductDAO productDAO;

    @Autowired
    public ProductServiceImpl(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }


    @Override
    public List<Product> getAllProducts() {
        List<Product> products = productDAO.getAllProducts().orElseThrow(
                () -> new DataNotFoundException("Data Not Available"));
        return products;
    }

    @Override
    public Product getProductById(Product product) {
        return productDAO.getProductById(product.getProductId())
                .orElseThrow(() -> new DataNotFoundException("Data Not available with " + product.getProductId()));

    }

    @Override
    public void deleteProduct(int productId) throws Exception {
        Product product=productDAO.getProductById(productId).orElseThrow(()-> new DataNotFoundException("Product Not Available with "+productId));
        productDAO.delete(product);
    }

    @Override
    public Product saveProduct(Product product) throws Exception {
        return productDAO.save(product);
    }

    @Override
    public Product updateProduct(Product product) throws Exception {
        return productDAO.save(product);
    }


}
