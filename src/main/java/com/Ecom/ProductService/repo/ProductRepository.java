package com.Ecom.ProductService.repo;

import com.Ecom.ProductService.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}