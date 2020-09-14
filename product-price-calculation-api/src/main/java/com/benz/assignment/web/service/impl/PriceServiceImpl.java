package com.benz.assignment.web.service.impl;

import com.benz.assignment.web.dao.ProductDAO;
import com.benz.assignment.web.entity.Product;
import com.benz.assignment.web.exception.DataNotFoundException;
import com.benz.assignment.web.model.ProductPrice;
import com.benz.assignment.web.model.TotalPrice;
import com.benz.assignment.web.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RefreshScope
public class PriceServiceImpl implements PriceService {

    private ProductDAO productDAO;

    @Autowired
    public PriceServiceImpl(ProductDAO productDAO) {
        this.productDAO = productDAO;

    }

    @Value("${cartoon.minNumOfCartoonToDiscount : 3}")
    public int minNumOfCartoonToDiscount;

    @Value("${cartoon.discountInPercentage : 0.1}")
    public double discountInPercentage;

    @Value("${cartoon.increasedInPercentage : 0.3}")
    public double increaseInPercentage;

    @Override
    public ProductPrice getProductPrice(ProductPrice price) {
        try {
            Product product = productDAO.getProductById(price.getProduct().getProductId()).orElseThrow(() -> new DataNotFoundException(
                    "Data Not Available With " + price.getProduct().getProductId()));
            product.setQuantity(price.getProduct().getQuantity());
            double productPrice = priceCalculation(product.getQuantity(), product.getNumberOfUnitInCartoon(),
                    product.getPriceOfCartoon());
            price.setProductPrice(productPrice);
            price.setProduct(product);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return price;
    }

    @Override
    public TotalPrice getTotalPrice(TotalPrice totalPrice) {

        List<Product> products = new ArrayList<>();

        totalPrice.getProducts().stream().forEach(p -> {
            Product product = productDAO.getProductById(p.getProductId())
                    .orElseThrow(() -> new DataNotFoundException("Product Not Found with " + p.getProductId()));
            product.setQuantity(p.getQuantity());
            products.add(product);
        });
        totalPrice.setProducts(products);
        double totalAmount = calculateTotalPrice(totalPrice);
        totalPrice.setTotal(totalAmount);
        return totalPrice;
    }


    private double calculateTotalPrice(TotalPrice totalPrice) {
        double totalAmount = 0.0;


        List<Double> amount = totalPrice.getProducts().stream().map(product -> {
            double price = 0.0;
            try {
                price = priceCalculation(product.getQuantity(), product.getNumberOfUnitInCartoon()
                        , product.getPriceOfCartoon());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return price;
        }).collect(Collectors.toList());

        for (double price : amount)
            totalAmount += price;

        return totalAmount;

    }

    private double priceCalculation(int quantity, int numOfUnit, double priceOfCartoon) throws Exception {
        double price = 0.0;

        int cartoonByUnit = quantity / numOfUnit;
        int remainingUnit = quantity % numOfUnit;


        double singleUnitPrice = priceOfCartoon / (double) numOfUnit;


        if (cartoonByUnit >= 1) {
            price = ((cartoonByUnit * priceOfCartoon) + ((double) remainingUnit * singleUnitPrice));

            if (cartoonByUnit >= minNumOfCartoonToDiscount) {
                double discount = priceOfCartoon * discountInPercentage;
                price = price - discount;
            }
        } else
            price = ((double) quantity * singleUnitPrice) + (priceOfCartoon * increaseInPercentage);

        return price;

    }
}