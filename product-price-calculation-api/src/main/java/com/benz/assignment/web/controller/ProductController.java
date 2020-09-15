package com.benz.assignment.web.controller;

import com.benz.assignment.web.entity.Product;
import com.benz.assignment.web.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    @Autowired
    private ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProduct() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @PostMapping(value = "/save", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) throws Exception {
        if (!product.getProductName().trim().isEmpty() && product.getNumberOfUnitInCartoon() != 0 && product.getPriceOfCartoon() != 0.0)
            return new ResponseEntity<>(productService.saveProduct(product), HttpStatus.OK);
        else
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/update", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) throws Exception {
        if (product.getProductId() != 0 && !product.getProductName().trim().isEmpty() &&
                product.getNumberOfUnitInCartoon() != 0 && product.getPriceOfCartoon() != 0.0)
            return new ResponseEntity<Product>(productService.updateProduct(product), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteProduct(@PathVariable("id") int productId) throws Exception {
        
        if (productId != 0)
            productService.deleteProduct(productId);
        else
            throw new IllegalArgumentException();

    }

    @PostMapping(value = "/id", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Product> getProduct(@RequestBody Product product) {
        if (product.getProductId() != 0)
            return ResponseEntity.ok(productService.getProductById(product));
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
