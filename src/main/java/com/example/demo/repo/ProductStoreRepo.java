package com.example.demo.repo;

import com.example.demo.entity.ProductStorePrice;
import com.example.demo.entity.Products;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface ProductStoreRepo extends JpaRepository<ProductStorePrice, Long> {
    List<ProductStorePrice> findByProductId(Products product);
}