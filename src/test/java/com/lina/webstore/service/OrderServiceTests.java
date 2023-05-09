package com.lina.webstore.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.lina.webstore.entity.Order;

@SpringBootTest
public class OrderServiceTests {

    @Autowired 
    IOrderService orderService;

    @Test
    public void create(){
        Integer [] cids={67,69};
        Order order=orderService.create(34,3,cids,"bbb");    
        System.out.println(order);   
    }


    
}
