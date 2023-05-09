package com.lina.webstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lina.webstore.entity.Order;
import com.lina.webstore.service.ICartService;
import com.lina.webstore.service.IOrderService;
import com.lina.webstore.util.JsonResult;
import com.lina.webstore.vo.CartVo;

import jakarta.servlet.http.HttpSession;


@RestController
@RequestMapping("orders")
public class OrderController extends BaseController{

    @Autowired
    IOrderService orderService;

    @RequestMapping("create")
    public JsonResult<Order> create(Integer aid, Integer[] cids, HttpSession session){
        String username=getUsernameFromSession(session);
        Integer uid=getuidFromSession(session);
        Order data=orderService.create(aid, uid, cids, username);
        return new JsonResult<>(ok,data);
    }


}
