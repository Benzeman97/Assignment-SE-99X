package com.benz.assignment.web.controller;

import capital.scalable.restdocs.AutoDocumentation;
import capital.scalable.restdocs.jackson.JacksonResultHandlers;
import capital.scalable.restdocs.response.ResponseModifyingPreprocessors;
import com.benz.assignment.web.entity.Product;
import com.benz.assignment.web.service.PriceService;
import com.benz.assignment.web.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.cli.CliDocumentation;
import org.springframework.restdocs.http.HttpDocumentation;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
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

@ExtendWith({RestDocumentationExtension.class,SpringExtension.class})
@WebMvcTest
@AutoConfigureRestDocs(outputDir = "target/generated-snippets")
public class ProductControllerTest {


    @MockBean
    private ProductService productService;

    @MockBean
    private PriceService priceService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    List<Product> products =null;

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider documentationContextProvider) {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(MockMvcRestDocumentation.documentationConfiguration(documentationContextProvider))
                .alwaysDo(JacksonResultHandlers.prepareJackson(objectMapper))
                .alwaysDo(MockMvcRestDocumentation.document("{ClassName}/{methodName}",
                        Preprocessors.preprocessRequest(),
                        Preprocessors.preprocessResponse(
                                ResponseModifyingPreprocessors.replaceBinaryContent(),
                                ResponseModifyingPreprocessors.limitJsonArrayLength(objectMapper),
                                Preprocessors.prettyPrint())))
                .apply(MockMvcRestDocumentation.documentationConfiguration(documentationContextProvider)
                        .uris()
                        .withScheme("http")
                        .withHost("localhost")
                        .withPort(9090)
                        .and().snippets()
                        .withDefaults(CliDocumentation.curlRequest(),
                                HttpDocumentation.httpRequest(),
                                HttpDocumentation.httpResponse(),
                                AutoDocumentation.requestFields(),
                                AutoDocumentation.responseFields(),
                                AutoDocumentation.pathParameters(),
                                AutoDocumentation.requestParameters(),
                                AutoDocumentation.description(),
                                AutoDocumentation.methodAndPath(),
                                AutoDocumentation.section()))
                .build();

         products=getProducts();
    }

    @Test
    public void getAllProduct() throws Exception {

        String expectedProducts=new ObjectMapper().writeValueAsString(products);

        Mockito.when(productService.getAllProducts()).thenReturn(products);

        MvcResult result= mockMvc.perform(get("/products")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        String actualProducts=result.getResponse().getContentAsString();

        Assertions.assertEquals(expectedProducts,actualProducts);

    }

    @Test
    public void saveProduct() throws Exception
    {


        String expectedProduct= new ObjectMapper().writeValueAsString(product());

        Mockito.when(productService.saveProduct(Mockito.any(Product.class))).thenReturn(product());

       MvcResult result= mockMvc.perform(post("/products/save")
               .accept(MediaType.APPLICATION_JSON_VALUE).content(expectedProduct).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

       String actualProduct= result.getResponse().getContentAsString();

       Assertions.assertEquals(expectedProduct,actualProduct);

    }

    @Test
    public void updateProduct() throws Exception
    {
        Product updateProduct=product();
        updateProduct.setNumberOfUnitInCartoon(45);
        updateProduct.setPriceOfCartoon(200.00);

        String expectedProduct=new ObjectMapper().writeValueAsString(updateProduct);

        Mockito.when(productService.updateProduct(Mockito.any(Product.class))).thenReturn(updateProduct);

       MvcResult result= mockMvc.perform(put("/products/update").accept(MediaType.APPLICATION_JSON_VALUE)
        .content(expectedProduct).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

       String actualProduct=result.getResponse().getContentAsString();

       Assertions.assertEquals(expectedProduct,actualProduct);

    }

    @Test
    public void deleteProduct() throws Exception
    {

        MvcResult result=mockMvc.perform(delete("/products/delete")
        .content(new ObjectMapper().writeValueAsString(product())).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

         Assertions.assertEquals(HttpStatus.OK.value(),result.getResponse().getStatus());
    }

    @Test
    public void getProduct() throws Exception
    {
         String expectedProduct=new ObjectMapper().writeValueAsString(product());

         Mockito.when(productService.getProductById(Mockito.any(Product.class))).thenReturn(product());

         MvcResult result=mockMvc.perform(post("/products/id")
         .accept(MediaType.APPLICATION_JSON_VALUE).content(expectedProduct)
         .contentType(MediaType.APPLICATION_JSON_VALUE))
                 .andExpect(status().isOk())
                 .andReturn();

         String actualProduct=result.getResponse().getContentAsString();

         Assertions.assertEquals(expectedProduct,actualProduct);
    }

    private Product product()
    {
        Product product=new Product();
        product.setProductId(1001);
        product.setProductName("Penguin-ears");
        product.setNumberOfUnitInCartoon(20);
        product.setPriceOfCartoon(175.00);
        product.setUrlOfImage("https://i.ibb.co/pLdM7FL/shutterstock-306427430-scaled.jpg");

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

        Product product_2=new Product();
        product_2.setProductId(1002);
        product_2.setProductName("Horseshoe");
        product_2.setNumberOfUnitInCartoon(5);
        product_2.setPriceOfCartoon(825);
        product_2.setUrlOfImage("https://i.ibb.co/MRDwnqj/horseshoe.jpg");

        return new ArrayList<>(Arrays.asList(product_1,product_2));
    }
}
