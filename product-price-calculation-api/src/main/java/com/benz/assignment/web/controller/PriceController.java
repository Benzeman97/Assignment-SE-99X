package com.benz.assignment.web.controller;

import com.benz.assignment.web.model.ProductPrice;
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
    private ResponseEntity<ProductPrice> getProductPrice(@RequestBody ProductPrice price) {
        if (price.getProduct().getProductId() != 0 && price.getProduct().getQuantity() != 0) {
            return new ResponseEntity<>(priceService.getProductPrice(price), HttpStatus.OK);
        } else
            throw new NullPointerException();
    }

    @PostMapping(value = "/total", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    private ResponseEntity<TotalPrice> getTotalPrice(@RequestBody TotalPrice totalPrice) {

       totalPrice.getProducts().forEach(p->{
           System.out.println(p.getQuantity());
       });

        if (totalPrice.getProducts().size() != 0)
            return new ResponseEntity<>(priceService.getTotalPrice(totalPrice), HttpStatus.OK);
        else
            throw new NullPointerException();
    }

}
