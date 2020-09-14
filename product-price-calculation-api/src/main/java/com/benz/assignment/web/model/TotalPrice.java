package com.benz.assignment.web.model;

import com.benz.assignment.web.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TotalPrice {

    private List<Product> products;
    private double total;

}
