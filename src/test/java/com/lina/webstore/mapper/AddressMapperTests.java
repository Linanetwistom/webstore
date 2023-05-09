package com.lina.webstore.mapper;


import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.lina.webstore.entity.Address;
@SpringBootTest
public class AddressMapperTests {

    @Autowired AddressMapper addressMapper;

    @Test
    public void insert(){
        Address address=new Address();
        address.setAid(3);
        address.setName("lina01");
        address.setPhone("123456789");
        Integer row= addressMapper.insert(address);
        System.out.println(row);
        
    }

    @Test
    public void countByuid(){
        Integer row= addressMapper.countByuid(3);
        System.out.println(row);
        
    }

    @Test
    public void findByUid(){
        List<Address> list= addressMapper.findByUid(3);
        System.out.println(list);
        
    }



}
