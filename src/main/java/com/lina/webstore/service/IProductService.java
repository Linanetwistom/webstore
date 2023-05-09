package com.lina.webstore.service;

import java.util.List;

import com.lina.webstore.entity.Product;

public interface IProductService {

    List<Product> findHotList();

    Product findByPid(Integer id);



}
