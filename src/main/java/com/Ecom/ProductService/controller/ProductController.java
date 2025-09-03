package com.Ecom.ProductService.controller;

import com.Ecom.ProductService.dto.ProductRequestDto;
import com.Ecom.ProductService.dto.ProductResponseDto;
import com.Ecom.ProductService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class ProductController {
    @Autowired
    @Qualifier("productService")
    ProductService productService;
    @GetMapping("/products/{id}")
    public ResponseEntity getProductById(@PathVariable UUID id){
        /*
        Product p1 =new Product(101,"Electronics",195000,"Mehnga Phone","Iphone","www.iphone.com");
        Product p2 = new Product(105,"Accessories",45000,"Normal","Laptop","www.laptop.com");
        List<Product> productList= Arrays.asList(p1,p2);
        return ResponseEntity.ok(productList);
         */
        ProductResponseDto response=productService.getProductById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/products")
    public List<ProductResponseDto> getAllProducts() {
        return productService.getAllProducts();
    }
    @PostMapping("/products")
    public ResponseEntity addProduct(@RequestBody ProductRequestDto productRequestDto){
        ProductResponseDto responseDto=productService.addProduct(productRequestDto);
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity updateProduct(@PathVariable UUID id, @RequestBody ProductResponseDto productResponseDto) {
        ProductResponseDto responseDto = productService.updateProduct(id,productResponseDto);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/products/{id}")
    public boolean deleteProduct(@PathVariable UUID id) {
        return productService.deleteProduct(id);

    }
}
