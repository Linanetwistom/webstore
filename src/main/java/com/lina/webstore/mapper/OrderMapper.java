package com.lina.webstore.mapper;

import com.lina.webstore.entity.Order;
import com.lina.webstore.entity.OrderItem;

public interface OrderMapper {

    Integer insertOrder(Order order);

    Integer insertOrderItem(OrderItem orderItem);

}
