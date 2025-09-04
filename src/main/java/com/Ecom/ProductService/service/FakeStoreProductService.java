package com.Ecom.ProductService.service;

import com.Ecom.ProductService.client.FakeStoreProductClient;
import com.Ecom.ProductService.dto.FakeStoreProductRequestDto;
import com.Ecom.ProductService.dto.FakeStoreProductResponseDto;
import com.Ecom.ProductService.dto.ProductRequestDto;
import com.Ecom.ProductService.dto.ProductResponseDto;
import com.Ecom.ProductService.exception.ProductNotFoundException;
import com.Ecom.ProductService.mapper.ProductMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{
   private final RestTemplate restTemplate;
   private FakeStoreProductClient fakeStoreProductClient;

    public FakeStoreProductService(RestTemplate restTemplate, FakeStoreProductClient fakeStoreProductClient) {
        this.restTemplate = restTemplate;
        this.fakeStoreProductClient = fakeStoreProductClient;
    }

    @Override
    public ProductResponseDto getProductById(UUID id) {
        FakeStoreProductResponseDto fakeStoreProductResponseDto=fakeStoreProductClient.getProductById(id);
        if(fakeStoreProductResponseDto == null){
            throw new ProductNotFoundException(id);
        }
        return ProductMapper.fakeStoreProductResponseDtoToProductResponseDto(fakeStoreProductResponseDto);
        /*
        String url="https://fakestoreapi.com/products/" +id;
        return restTemplate.getForEntity(url,ProductResponseDto.class).getBody();

         */
    }

    @Override
    public List<ProductResponseDto> getAllProducts() {
        List<FakeStoreProductResponseDto> fakeStoreProductResponseDtos=fakeStoreProductClient.getAllProducts();
        List<ProductResponseDto> productResponseDtos = new ArrayList<>();
        for(FakeStoreProductResponseDto fakeStoreProductResponseDto: fakeStoreProductResponseDtos){
            ProductResponseDto productResponseDto = ProductMapper
                    .fakeStoreProductResponseDtoToProductResponseDto(fakeStoreProductResponseDto);
            productResponseDtos.add(productResponseDto);
        }
        return productResponseDtos;
        /*
        String url="https://fakestoreapi.com/products";
        ProductResponseDto[] arraysProduct= restTemplate.getForEntity(url,ProductResponseDto[].class).getBody();
        List<ProductResponseDto> list = new ArrayList<>();
        for (ProductResponseDto responseDto: arraysProduct){
            list.add(responseDto);
        }
        return list;

         */
    }



    @Override
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) {
        FakeStoreProductRequestDto fakeStoreProductRequestDto=ProductMapper.productRequestDtoToFakeStoreProductRequestDto(productRequestDto);
        FakeStoreProductResponseDto fakeStoreProductResponseDto=fakeStoreProductClient.addProduct(fakeStoreProductRequestDto);
        return ProductMapper.fakeStoreProductResponseDtoToProductResponseDto(fakeStoreProductResponseDto);
    }

    @Override
    public ProductResponseDto updateProduct(UUID id, ProductResponseDto productResponseDto) {
        FakeStoreProductResponseDto fakeStoreProductResponseDto= ProductMapper.productResponseDtoToFakeStoreProductResponseDto(productResponseDto);
        return ProductMapper
                .fakeStoreProductResponseDtoToProductResponseDto(fakeStoreProductClient
                        .updateProduct(id, fakeStoreProductResponseDto));
        /*
        String url= "https://fakestoreapi.com/products/" +id;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ProductResponseDto> entity = new HttpEntity<>(productResponseDto, headers);
        ResponseEntity<ProductResponseDto> res = restTemplate.exchange(url, HttpMethod.PUT,entity,ProductResponseDto.class);
        return res.getBody();

         */
    }

    @Override
    public boolean deleteProduct(UUID id) {
        boolean flag= fakeStoreProductClient.deleteProduct(id);
        if(flag == false){
            throw new ProductNotFoundException(id);
        }
        else {
            return true;
        }
    }
    @Override
    public ProductResponseDto getProductByTitle(String title) {
        return null;
    }

    @Override
    public ProductResponseDto getProductByTitleAndDescription(String titile, String description) {
        return null;
    }

    @Override
    public List<ProductResponseDto> getProductByPriceBetween(double startPrice, double endPrice) {
        return null;
    }
}
