package com.anrry.ac1pt2.repository;

import com.anrry.ac1pt2.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE p.price > :price")
    List<Product> findByPriceGreaterThan(Double price);

    @Query("SELECT p FROM Product p WHERE p.price <= :price")
    List<Product> findByPriceLessThan(Double price);

    @Query("SELECT p FROM Product p WHERE p.name LIKE %:name%")
    List<Product> findByNameContaining(String name);
}
