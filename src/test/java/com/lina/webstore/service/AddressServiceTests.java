package com.lina.webstore.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.lina.webstore.entity.Address;

@SpringBootTest
public class AddressServiceTests {

    @Autowired 
    IAddressService addressService;

    @Test
    public void addNewAddress(){
        Address address= new Address();
        address.setAid(4);
        address.setName("lina03");
        address.setPhone("123433422");

        addressService.addNewAddress(3,"bbb",address);       
    }

    @Test
    public void setDefault(){

        addressService.setDefault(32,3,"bbb");       
    }


    @Test
    public void delete(){
        addressService.delete(38,3,"bbb");
        
    }

    
}
