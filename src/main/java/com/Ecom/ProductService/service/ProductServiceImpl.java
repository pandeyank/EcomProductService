package com.Ecom.ProductService.service;

import com.Ecom.ProductService.dto.ProductRequestDto;
import com.Ecom.ProductService.dto.ProductResponseDto;
import com.Ecom.ProductService.exception.ProductNotFoundException;
import com.Ecom.ProductService.mapper.ProductMapper;
import com.Ecom.ProductService.model.Category;
import com.Ecom.ProductService.model.Price;
import com.Ecom.ProductService.model.Product;
import com.Ecom.ProductService.repo.CategoryRepository;
import com.Ecom.ProductService.repo.PriceRepository;
import com.Ecom.ProductService.repo.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service("productService")
public class ProductServiceImpl implements ProductService{
    ProductRepository productRepository;
    CategoryRepository categoryRepository;
    PriceRepository priceRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, PriceRepository priceRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.priceRepository = priceRepository;
    }

    @Override
    public ProductResponseDto getProductById(UUID id) {
        Product product = productRepository.findById(id).orElseThrow(null);
        ProductResponseDto productResponseDto=ProductMapper.convertProductToProductResponseDto(product);
        return productResponseDto;
    }

    @Override
    public List<ProductResponseDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductResponseDto> productResponseDtos = new ArrayList<>();
        for (Product product: products){
            ProductResponseDto responseDto=ProductMapper.convertProductToProductResponseDto(product);
            productResponseDtos.add(responseDto);
        }
        return productResponseDtos;

    }

    @Override
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) {
        Product product = ProductMapper.convertProductRequestDtoToProduct(productRequestDto);
        Category c = product.getCategory();
        categoryRepository.save(c);   // <-- save child first

        Price price = product.getPrice();
        priceRepository.save(price);
        product = productRepository.save(product);
        ProductResponseDto responseDto = ProductMapper.convertProductToProductResponseDto(product);
        return responseDto;
    }

    @Override
    public ProductResponseDto updateProduct(UUID id, ProductResponseDto productResponseDto) {
        Product product = productRepository.findById(id).orElseThrow(null);
        product.setTitle(productResponseDto.getTitle());
        product.setDescription(productResponseDto.getDescription());
        product.setImage(productResponseDto.getImage());
        Price price = new Price();
        price.setAmount(productResponseDto.getPrice());
        Category category=new Category();
        category.setCatInfo(productResponseDto.getCategory());
        priceRepository.save(price);
        categoryRepository.save(category);
        product = productRepository.save(product);
        return ProductMapper.convertProductToProductResponseDto(product);
    }

    @Override
    public boolean deleteProduct(UUID id) {
        return false;
    }
}
