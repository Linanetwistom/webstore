package com.lina.webstore.mapper;


import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.lina.webstore.entity.District;
@SpringBootTest
public class DistrictMapperTests {

    @Autowired DistrictMapper districtMapper;

    @Test
    public void findByParent(){
        List<District> result= districtMapper.findByParent("0");
        for(int i=0;i<result.size();i++){
            System.out.println(result);
        }  
    }
    @Test
    public void findNameByCode(){

        String name= districtMapper.findNameByCode("130000");
        System.out.println(name);
        
    }



}
