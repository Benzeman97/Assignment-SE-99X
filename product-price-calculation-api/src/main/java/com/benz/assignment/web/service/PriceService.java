package com.benz.assignment.web.service;

import com.benz.assignment.web.model.ProductPrice;
import com.benz.assignment.web.model.TotalPrice;

public interface PriceService {

    ProductPrice getProductPrice(ProductPrice price);

    TotalPrice getTotalPrice(TotalPrice totalPrice);

}
