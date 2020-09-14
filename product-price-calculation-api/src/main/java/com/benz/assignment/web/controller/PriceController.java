package com.benz.assignment.web.controller;

import com.benz.assignment.web.entity.Product;
import com.benz.assignment.web.model.TotalPrice;
import com.benz.assignment.web.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/price")
public class PriceController {

    private PriceService priceService;

    @Autowired
    private PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @PostMapping
    private ResponseEntity<Product> getProductPrice(@RequestBody Product product) {
        if (product.getProductId() != 0 && product.getQuantity() != 0) {
            return new ResponseEntity<>(priceService.getProductPrice(product), HttpStatus.OK);
        } else
            throw new NullPointerException();
    }

    @PostMapping(value = "/total", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    private ResponseEntity<TotalPrice> getTotalPrice(@RequestBody TotalPrice totalPrice) {

        if (totalPrice.getProducts().size() != 0)
            return new ResponseEntity<>(priceService.getTotalPrice(totalPrice), HttpStatus.OK);
        else
            throw new NullPointerException();
    }

}
