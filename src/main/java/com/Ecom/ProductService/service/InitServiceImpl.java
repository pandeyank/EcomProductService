package com.Ecom.ProductService.service;

import com.Ecom.ProductService.model.Category;
import com.Ecom.ProductService.model.Order;
import com.Ecom.ProductService.model.Price;
import com.Ecom.ProductService.model.Product;
import com.Ecom.ProductService.repo.CategoryRepository;
import com.Ecom.ProductService.repo.OrderRepository;
import com.Ecom.ProductService.repo.PriceRepository;
import com.Ecom.ProductService.repo.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class InitServiceImpl implements InitService{

    CategoryRepository categoryRepository;
    OrderRepository orderRepository;
    PriceRepository priceRepository;
    ProductRepository productRepository;

    public InitServiceImpl(CategoryRepository categoryRepository, OrderRepository orderRepository, PriceRepository priceRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.orderRepository = orderRepository;
        this.priceRepository = priceRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void initialise() {
        //Category
        Category electronics =new Category();
        electronics.setCatInfo("Electronics");
        electronics=categoryRepository.save(electronics);
        //Price
        Price iphone = new Price();
        iphone.setCurrency("INR");
        iphone.setAmount(150000);
        iphone.setDiscount(0);
        iphone=priceRepository.save(iphone);

        Price macBookLaptop = new Price();
        macBookLaptop.setCurrency("INR");
        macBookLaptop.setAmount(250000);
        macBookLaptop.setDiscount(0);
        macBookLaptop=priceRepository.save(macBookLaptop);

        Price appleWatch = new Price();
        appleWatch.setCurrency("INR");
        appleWatch.setAmount(50000);
        appleWatch.setDiscount(0);
        appleWatch=priceRepository.save(appleWatch);

        //Product

        Product p1 = new Product();
        p1.setTitle("I-Phone");
        p1.setDescription("Kafi mehnga Iphone");
        p1.setImage("www.apple.com");
        p1.setPrice(iphone);
        p1.setCategory(electronics);
        p1=productRepository.save(p1);

        Product p2 =new Product();
        p2.setTitle("Macbook Laptop");
        p2.setDescription("Very expansive Laptop");
        p2.setImage("www.apple.com/laptop");
        p2.setPrice(macBookLaptop);
        p2.setCategory(electronics);
        p2=productRepository.save(p2);

        Product p3 =new Product();
        p3.setTitle("Apple Watch");
        p3.setDescription("Digital Watch");
        p3.setImage("www.apple.com/watch");
        p3.setPrice(appleWatch);
        p3.setCategory(electronics);
        p3=productRepository.save(p3);


        //Order
        Order order =new Order();
        order.setProductList(Arrays.asList(p1,p2,p3));
        order = orderRepository.save(order);
    }
}
