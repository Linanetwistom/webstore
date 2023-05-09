package com.lina.webstore.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lina.webstore.entity.Product;
import com.lina.webstore.mapper.ProductMapper;
import com.lina.webstore.service.IProductService;

@Service
public class ProductServiceImpl implements IProductService{
    @Autowired
    ProductMapper productMapper;

    @Override
    public List<Product> findHotList() {
        List<Product> list= productMapper.findhotlist();
        for(Product p:list){
            p.setPriority(null);
            p.setCategoryId(null);
            p.setCreatedTime(null);
            p.setCreatedUser(null);
            p.setModifiedTime(null);
            p.setModifiedUser(null);
        }

        return list;
    }

    @Override
    public Product findByPid(Integer id) {
        Product product= productMapper.findById(id);
        return product;
    }

    
}