package com.lina.webstore.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CartServiceTests {

    @Autowired 
    ICartService cartService;

    @Test
    public void addToCart(){

        cartService.addToCart(3,10000022,2,"bbb");       
    }


    
}
