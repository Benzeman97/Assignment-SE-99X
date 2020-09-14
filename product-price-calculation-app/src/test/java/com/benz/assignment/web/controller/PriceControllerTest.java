package com.benz.assignment.web.controller;

import com.benz.assignment.web.service.PriceService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@WebFluxTest
public class PriceControllerTest {

    @Autowired
    PriceService priceService;

    //todo
}
