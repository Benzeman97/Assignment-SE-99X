package com.benz.assignment.web.controller;

import com.benz.assignment.web.entity.Product;
import com.benz.assignment.web.model.TotalPrice;
import com.benz.assignment.web.service.PriceService;
import com.benz.assignment.web.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@WebMvcTest
public class PriceControllerTest {

    @MockBean
    private PriceService priceService;

    @MockBean
    private ProductService productService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;


    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider documentationContextProvider)
    {
        this.mockMvc= MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(MockMvcRestDocumentation.documentationConfiguration(documentationContextProvider))
                .build();
    }

    @Test
    public void getProductPrice() throws Exception
    {
          String expectedProduct=new ObjectMapper().writeValueAsString(product());

         Mockito.when(priceService.getProductPrice(Mockito.any(Product.class))).thenReturn(product());

          MvcResult result=mockMvc.perform(post("/price")
          .accept(MediaType.APPLICATION_JSON_VALUE).content(expectedProduct).contentType(MediaType.APPLICATION_JSON_VALUE))
                  .andExpect(status().isOk())
                  .andReturn();

          String actualProduct=result.getResponse().getContentAsString();

        Assertions.assertEquals(expectedProduct,actualProduct);
    }

    @Test
    public void getTotalPrice() throws Exception
    {
        String expectedTotalPrice=new ObjectMapper().writeValueAsString(totalPrice());

        Mockito.when(priceService.getTotalPrice(Mockito.any(TotalPrice.class))).thenReturn(totalPrice());

        MvcResult result=mockMvc.perform(post("/price/total")
        .accept(MediaType.APPLICATION_JSON_VALUE).content(expectedTotalPrice).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        String actualTotalPrice=result.getResponse().getContentAsString();

        Assertions.assertEquals(expectedTotalPrice,actualTotalPrice);

    }

    private Product product()
    {
        Product product=new Product();
        product.setProductId(1001);
        product.setProductName("Penguin-ears");
        product.setNumberOfUnitInCartoon(20);
        product.setPriceOfCartoon(175.00);
        product.setUrlOfImage("https://i.ibb.co/pLdM7FL/shutterstock-306427430-scaled.jpg");
        product.setQuantity(15);
        product.setPriceByUnit(183.75);

        return product;
    }

    private List<Product> getProducts()
    {
        Product product_1=new Product();
        product_1.setProductId(1001);
        product_1.setProductName("Penguin-ears");
        product_1.setNumberOfUnitInCartoon(20);
        product_1.setPriceOfCartoon(175.00);
        product_1.setUrlOfImage("https://i.ibb.co/pLdM7FL/shutterstock-306427430-scaled.jpg");
        product_1.setQuantity(20);
        product_1.setPriceByUnit(175.00);

        Product product_2=new Product();
        product_2.setProductId(1002);
        product_2.setProductName("Horseshoe");
        product_2.setNumberOfUnitInCartoon(5);
        product_2.setPriceOfCartoon(825.00);
        product_2.setUrlOfImage("https://i.ibb.co/MRDwnqj/horseshoe.jpg");
        product_2.setQuantity(5);
        product_2.setPriceByUnit(825.00);

        return new ArrayList<>(Arrays.asList(product_1,product_2));
    }

    private TotalPrice totalPrice()
    {
        TotalPrice totalPrice=new TotalPrice();
        totalPrice.setProducts(getProducts());
        totalPrice.setTotal(1000.00);
        return totalPrice;
    }
}
