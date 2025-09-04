package com.Ecom.ProductService.service;

import com.Ecom.ProductService.dto.ProductRequestDto;
import com.Ecom.ProductService.dto.ProductResponseDto;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    ProductResponseDto getProductById(UUID id);

    List<ProductResponseDto> getAllProducts();

    ProductResponseDto addProduct(ProductRequestDto productRequestDto);

    ProductResponseDto updateProduct(UUID id, ProductResponseDto productResponseDto);

    boolean deleteProduct(UUID id);

    ProductResponseDto getProductByTitle(String title);

    ProductResponseDto getProductByTitleAndDescription(String titile, String description);

    List<ProductResponseDto> getProductByPriceBetween(double startPrice, double endPrice);
}
