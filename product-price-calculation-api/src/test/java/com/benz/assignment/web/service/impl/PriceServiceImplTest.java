package com.benz.assignment.web.service.impl;

import com.benz.assignment.web.dao.ProductDAO;
import com.benz.assignment.web.entity.Product;
import com.benz.assignment.web.model.TotalPrice;
import com.benz.assignment.web.service.PriceService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PriceServiceImplTest {

    @MockBean
    private ProductDAO productDAO;

    @Autowired
    private PriceService priceService;

    @Test
    public void calculateProductPriceForSingleUnits() throws Exception {
       Product prod=getProduct();
        Optional<Product> product = Optional.of(prod);
        Mockito.when(productDAO.getProductById(prod.getProductId()))
                .thenReturn(product);
        Product newProduct = priceService.getProductPrice(prod);
        double price = newProduct.getPriceByUnit();
        Assert.assertEquals(price, 175.00, 0.0);

    }

    @Test
    public void calculateProductPriceWithCartoon() {

        Product prod=getProduct();
        prod.setQuantity(25);
        Optional<Product> product = Optional.of(prod);
        Mockito.when(productDAO.getProductById(prod.getProductId()))
                .thenReturn(product);
        Product newProduct= priceService.getProductPrice(prod);

        Assert.assertEquals(newProduct.getPriceByUnit(), 218.75, 0.0);
    }

    @Test
    public void calculateTotalPriceWithMultipleProducts() {
        double price = 0.0;
        TotalPrice totalPrice = getTotalPrice_2();
        for (Product prod : totalPrice.getProducts()) {
            Optional<Product> product = Optional.of(prod);
            Mockito.when(productDAO.getProductById(prod.getProductId()))
                    .thenReturn(product);
            price += priceService.getTotalPrice(totalPrice).getTotal();
        }
        Assert.assertEquals(1000, price, 0.0);
    }

    private TotalPrice getTotalPrice_2() {
        TotalPrice totalPrice = new TotalPrice();
        Product prod1 = getProduct();

        Product prod2 = getProduct_2();


        List<Product> totalPrices = new ArrayList<>();
        totalPrices.add(prod1);
        totalPrices.add(prod2);

        totalPrice.setProducts(totalPrices);
        return totalPrice;
    }

    private Product getProduct() {
        Product product = new Product();
        product.setProductId(1001);
        product.setProductName("Penguin-ears Test");
        product.setPriceOfCartoon(175.00);
        product.setNumberOfUnitInCartoon(20);
        product.setQuantity(20);
        product.setUrlOfImage("https://i.ibb.co/pLdM7FL/shutterstock-306427430-scaled.jpg");
        return product;
    }

    private Product getProduct_2() {
        Product product = new Product();
        product.setProductId(1003);
        product.setProductName("Horseshoe Test");
        product.setPriceOfCartoon(825.00);
        product.setNumberOfUnitInCartoon(5);
        product.setQuantity(5);
        product.setUrlOfImage("https://i.ibb.co/MRDwnqj/horseshoe.jpg");
        return product;
    }

}
