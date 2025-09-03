package com.Ecom.ProductService.client;

import com.Ecom.ProductService.dto.FakeStoreProductRequestDto;
import com.Ecom.ProductService.dto.FakeStoreProductResponseDto;
import com.Ecom.ProductService.dto.ProductResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Component
public class FakeStoreProductClient {
    private RestTemplate restTemplate;

    @Value("${fakeStoreProductUrl}")
    private String fakeStoreProductUrl;

    public FakeStoreProductClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public FakeStoreProductResponseDto addProduct(FakeStoreProductRequestDto fakeStoreProductRequestDto) {
        String url= fakeStoreProductUrl;
        return restTemplate.postForEntity(url, fakeStoreProductRequestDto, FakeStoreProductResponseDto.class).getBody();
    }

    public FakeStoreProductResponseDto getProductById(UUID id) {
        String url=fakeStoreProductUrl + "/" +id;
        return restTemplate.getForEntity(url,FakeStoreProductResponseDto.class).getBody();
    }
    public List<FakeStoreProductResponseDto> getAllProducts() {
        String url=fakeStoreProductUrl;
        FakeStoreProductResponseDto[] arraysProduct= restTemplate.getForEntity(url,FakeStoreProductResponseDto[].class).getBody();
        List<FakeStoreProductResponseDto> list = new ArrayList<>();
        for (FakeStoreProductResponseDto responseDto: arraysProduct){
            list.add(responseDto);
        }
        return list;
    }
    public FakeStoreProductResponseDto updateProduct(UUID id, FakeStoreProductResponseDto fakeStoreProductResponseDto) {
        String url= fakeStoreProductUrl +"/" +id;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<FakeStoreProductResponseDto> entity = new HttpEntity<>(fakeStoreProductResponseDto, headers);
        ResponseEntity<FakeStoreProductResponseDto> res = restTemplate.exchange(url, HttpMethod.PUT,entity,FakeStoreProductResponseDto.class);
        return res.getBody();
    }
    public boolean deleteProduct(UUID id) {
        String url= fakeStoreProductUrl+ "/" +id;
        try {
            FakeStoreProductResponseDto fakeStoreProductResponseDto = restTemplate.getForEntity(url,
                    FakeStoreProductResponseDto.class).getBody();
            return true;
        }
        catch (NullPointerException ex) {
            return false;
        }

    }

}
