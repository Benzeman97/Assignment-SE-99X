package com.benz.assignment.web.dao;

import com.benz.assignment.web.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductDAO extends JpaRepository<Product, Integer> {

    @Query("from Product ")
    Optional<List<Product>> getAllProducts();

    @Query("from Product where productId= :id ")
    Optional<Product> getProductById(@Param("id") int id);
}
