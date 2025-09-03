package com.Ecom.ProductService.mapper;

import com.Ecom.ProductService.dto.FakeStoreProductRequestDto;
import com.Ecom.ProductService.dto.FakeStoreProductResponseDto;
import com.Ecom.ProductService.dto.ProductRequestDto;
import com.Ecom.ProductService.dto.ProductResponseDto;
import com.Ecom.ProductService.model.Category;
import com.Ecom.ProductService.model.Price;
import com.Ecom.ProductService.model.Product;

public class ProductMapper {
    public static ProductRequestDto fakeStoreProductRequestDtoToProductRequestDto(FakeStoreProductRequestDto fakeStoreProductRequestDto) {
        ProductRequestDto productRequestDto = new ProductRequestDto();
        productRequestDto.setTitle(fakeStoreProductRequestDto.getTitle());
        productRequestDto.setCategory(fakeStoreProductRequestDto.getCategory());
        productRequestDto.setDescription(fakeStoreProductRequestDto.getDescription());
        productRequestDto.setImage(fakeStoreProductRequestDto.getImage());
        productRequestDto.setPrice(fakeStoreProductRequestDto.getPrice());
        return productRequestDto;
    }

    public static FakeStoreProductRequestDto productRequestDtoToFakeStoreProductRequestDto(ProductRequestDto productRequestDto){
        FakeStoreProductRequestDto fakeStoreProductRequestDto=new FakeStoreProductRequestDto();
        fakeStoreProductRequestDto.setCategory(productRequestDto.getCategory());
        fakeStoreProductRequestDto.setDescription(productRequestDto.getDescription());
        fakeStoreProductRequestDto.setImage(productRequestDto.getImage());
        fakeStoreProductRequestDto.setPrice(productRequestDto.getPrice());
        fakeStoreProductRequestDto.setTitle(productRequestDto.getTitle());
        return fakeStoreProductRequestDto;
    }
    public static ProductResponseDto fakeStoreProductResponseDtoToProductResponseDto(FakeStoreProductResponseDto fakeStoreProductResponseDto){
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setId(fakeStoreProductResponseDto.getId());
        productResponseDto.setCategory(fakeStoreProductResponseDto.getCategory());
        productResponseDto.setDescription(fakeStoreProductResponseDto.getDescription());
        productResponseDto.setImage(fakeStoreProductResponseDto.getImage());
        productResponseDto.setPrice(fakeStoreProductResponseDto.getPrice());
        productResponseDto.setTitle(fakeStoreProductResponseDto.getTitle());
        return productResponseDto;
    }
    public static FakeStoreProductResponseDto productResponseDtoToFakeStoreProductResponseDto(ProductResponseDto productResponseDto){
        FakeStoreProductResponseDto fakeStoreProductResponseDto=new FakeStoreProductResponseDto();
        fakeStoreProductResponseDto.setId(productResponseDto.getId());
        fakeStoreProductResponseDto.setPrice(productResponseDto.getPrice());
        fakeStoreProductResponseDto.setTitle(productResponseDto.getTitle());
        fakeStoreProductResponseDto.setImage(productResponseDto.getImage());
        fakeStoreProductResponseDto.setDescription(productResponseDto.getDescription());
        fakeStoreProductResponseDto.setCategory(productResponseDto.getCategory());
        return fakeStoreProductResponseDto;
    }

    public static ProductResponseDto convertProductToProductResponseDto(Product product){
        if(product == null) return null;
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setId(product.getUuid());
        productResponseDto.setPrice(product.getPrice()!=null? product.getPrice().getAmount():0.0);
        productResponseDto.setTitle(product.getTitle());
        productResponseDto.setImage(product.getImage());
        productResponseDto.setCategory(product.getCategory()!=null? product.getCategory().getCatInfo():null);
        productResponseDto.setDescription(product.getDescription());
        return productResponseDto;

    }

    public static Product convertProductRequestDtoToProduct(ProductRequestDto productRequestDto){
        if(productRequestDto == null) return null;
        Product product=new Product();
        product.setTitle(productRequestDto.getTitle());
        product.setImage(productRequestDto.getImage());
        product.setDescription(productRequestDto.getDescription());
        Price price = new Price();
        price.setAmount(productRequestDto.getPrice());
        product.setPrice(price);
        Category category = new Category();
        category.setCatInfo(productRequestDto.getCategory());
        product.setCategory(category);
        return product;
    }
}

