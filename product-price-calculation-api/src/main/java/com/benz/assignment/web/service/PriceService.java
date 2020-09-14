package com.benz.assignment.web.service;

import com.benz.assignment.web.entity.Product;
import com.benz.assignment.web.model.TotalPrice;

public interface PriceService {

    Product getProductPrice(Product price);

    TotalPrice getTotalPrice(TotalPrice totalPrice);

}
