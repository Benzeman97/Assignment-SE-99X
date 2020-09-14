package com.benz.assignment.web.service.impl;

import com.benz.assignment.web.dao.ProductDAO;
import com.benz.assignment.web.entity.Product;
import com.benz.assignment.web.service.ProductService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ProductServiceImplTest {

    @MockBean
    private ProductDAO productDAO;

    @Autowired
    private ProductService productService;

    @Test
    public void saveProduct() throws Exception {
        Product product = getProduct();
        Mockito.when(productDAO.save(product)).thenReturn(product);
        Product newProduct = productService.saveProduct(product);
        Assert.assertEquals(newProduct, product);
    }

    @Test
    public void searchProduct() {
        Product product = getProduct();
        int productId = 1020;
        product.setProductId(productId);
        Optional<Product> presentProduct = Optional.of(product);
        Mockito.when(productDAO.getProductById(productId)).thenReturn(presentProduct);
        Product getProduct = productService.getProductById(product);
        Assert.assertEquals(getProduct, product);
    }

    @Test
    public void updateProduct() throws Exception {
        Product product = getProduct();
        String productName = "product test";
        int numOfUnit = 20;
        Mockito.when(productDAO.save(product)).thenReturn(product);
        Product newProduct = productService.saveProduct(product);
        newProduct.setProductName(productName);
        newProduct.setNumberOfUnitInCartoon(numOfUnit);
        Mockito.when(productDAO.save(newProduct)).thenReturn(newProduct);
        Product updatedProduct = productService.saveProduct(product);
        Assert.assertEquals(updatedProduct.getProductName(), productName);
        Assert.assertEquals(updatedProduct.getNumberOfUnitInCartoon(), numOfUnit);
    }

    private Product getProduct() {
        Product product = new Product();
        product.setProductId(1020);
        product.setProductName("Horseshoe Test");
        product.setPriceOfCartoon(825.00);
        product.setNumberOfUnitInCartoon(5);
        product.setUrlOfImage("https://i.ibb.co/MRDwnqj/horseshoe.jpg");
        return product;
    }


}
