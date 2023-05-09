package com.lina.webstore.service.impl;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lina.webstore.entity.Address;
import com.lina.webstore.entity.Order;
import com.lina.webstore.entity.OrderItem;
import com.lina.webstore.mapper.OrderMapper;
import com.lina.webstore.service.IAddressService;
import com.lina.webstore.service.ICartService;
import com.lina.webstore.service.IOrderService;
import com.lina.webstore.service.ex.UpdateExceptrion;
import com.lina.webstore.vo.CartVo;

@Service
public class OrderServiceImpl implements IOrderService{
    @Autowired
    OrderMapper orderMapper;

    @Autowired
    IAddressService addressService;

    @Autowired 
    ICartService cartService;

    @Override
    public Order create(Integer aid, Integer uid, Integer[] cids, String username) {
        List<CartVo> list=cartService.findVoByCid(uid,cids);
        Long totalPrice=0L;
        for(CartVo c:list){
            totalPrice+=c.getRealPrice()*c.getNum();
            
        }
        Address address=addressService.getByAid(aid, uid);
        Order order=new Order();
        order.setUid(uid);
        order.setRecvName(address.getName());
        order.setRecvPhone(address.getPhone());
        order.setRecvProvince(address.getProvinceName());
        order.setRecvCity(address.getCityName());
        order.setRecvArea(address.getAreaName());
        order.setRecvAddress(address.getAddress());
        order.setStatus(0);
        order.setTotalPrice(totalPrice);
        order.setCreatedTime(new Date());
        order.setModifiedTime(new Date());
        order.setCreatedUser(username);
        order.setModifiedUser(username);

        Integer rows=orderMapper.insertOrder(order);
        if(rows!=1){
            throw new UpdateExceptrion("插入订单异常");
        }

        for(CartVo c:list){
            OrderItem  orderItem= new  OrderItem();
            orderItem.setOid(order.getOid());
            orderItem.setPid(c.getPid());
            orderItem.setTitle(c.getTitle());
            orderItem.setImage(c.getImage());
            orderItem.setPrice(c.getPrice());
            orderItem.setNum(c.getNum());
            orderItem.setCreatedTime(new Date());
            orderItem.setModifiedTime(new Date());
            orderItem.setCreatedUser(username);
            orderItem.setModifiedUser(username);

            Integer row=orderMapper.insertOrderItem(orderItem);
            if(row!=1){
                throw new UpdateExceptrion("插入订单异常");
            }
        }
       return order;
    }



    
}