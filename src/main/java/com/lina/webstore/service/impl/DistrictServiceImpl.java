package com.lina.webstore.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lina.webstore.entity.District;
import com.lina.webstore.mapper.DistrictMapper;
import com.lina.webstore.service.IDistrictService;

@Service
public class DistrictServiceImpl implements IDistrictService{
    @Autowired
    DistrictMapper districtMapper;

    @Override
    public List<District> getByParent(String partent) {
        List<District> result= districtMapper.findByParent(partent);

        for(District d: result){
            d.setId(null);
            d.setParent(null);
        }
        return result;
    }

    @Override
    public String findNameByCode(String code) {
        String name= districtMapper.findNameByCode(code);
        return name;

    }



    
}