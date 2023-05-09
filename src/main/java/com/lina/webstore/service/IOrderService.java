package com.lina.webstore.service;

import com.lina.webstore.entity.Order;

public interface IOrderService {

   Order create(Integer aid,Integer uid,Integer [] cids,String username);

}
