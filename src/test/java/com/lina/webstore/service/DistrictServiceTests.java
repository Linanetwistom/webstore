package com.lina.webstore.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.lina.webstore.entity.District;

@SpringBootTest
public class DistrictServiceTests {

    @Autowired 
    IDistrictService districtService;

    @Test
    public void getByParent(){

        List<District> result= districtService.getByParent("0");

        for(District d:result){
            System.out.println(d);
        }
        
    }





    
}
