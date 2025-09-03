package com.Ecom.ProductService.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(UUID ProductId) {
        super("Product not found for the given id:" +ProductId);
    }
}
