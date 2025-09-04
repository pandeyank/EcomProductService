package com.Ecom.ProductService.repo;

import com.Ecom.ProductService.model.Price;
import com.Ecom.ProductService.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    Product findByTitle(String tiltle);
    Product findByTitleAndDescription(String title, String description);

    List<Product> findByPriceAmountBetween(Double min, Double max);
}