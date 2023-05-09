package com.lina.webstore.mapper;

import java.util.List;

import com.lina.webstore.entity.Product;

public interface ProductMapper {

    List<Product> findhotlist();

    Product findById(Integer id);

}
