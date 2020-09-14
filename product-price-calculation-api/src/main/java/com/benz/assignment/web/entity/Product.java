package com.benz.assignment.web.entity;

import com.benz.assignment.web.db.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCT", schema = Schema.ASSIGNDB, uniqueConstraints = {
        @UniqueConstraint(name = "productName", columnNames = "PRODUCT_NAME")
})
@Getter
@Setter
public class Product {

    @Id
    @SequenceGenerator(name = "PRODUCT_ID_GEN", sequenceName = Schema.ASSIGNDB + ".PRODUCT_ID_SEQ", initialValue = 1003, allocationSize = 1)
    @GeneratedValue(generator = "PRODUCT_ID_GEN", strategy = GenerationType.SEQUENCE)
    @Column(name = "PRODUCT_ID")
    private int productId;

    @Column(name = "PRODUCT_NAME", nullable = false)
    private String productName;

    @Column(name = "NUMBER_OF_UNIT", nullable = false)
    private int numberOfUnitInCartoon;

    @Column(name = "PRICE_OF_CARTOON", nullable = false)
    private double priceOfCartoon;

    @Column(name = "URL_OF_IMAGE")
    private String urlOfImage;

    @Transient
    private int quantity;


}
