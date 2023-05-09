package com.lina.webstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lina.webstore.service.ICartService;
import com.lina.webstore.util.JsonResult;
import com.lina.webstore.vo.CartVo;

import jakarta.servlet.http.HttpSession;


@RestController
@RequestMapping("carts")
public class CartController extends BaseController{

    @Autowired
    ICartService cartService;

    @RequestMapping("add_to_cart")
    public JsonResult<Void> addToCart(Integer pid,Integer amount,HttpSession session){
        String username=getUsernameFromSession(session);
        Integer uid=getuidFromSession(session);
        cartService.addToCart(uid, pid, amount, username);
        return new JsonResult<>(ok);
    }

    @RequestMapping({"/",""})
    public JsonResult<List<CartVo>> findVoByUid(HttpSession session){
        Integer uid=getuidFromSession(session);
        List<CartVo> data=cartService.findVoByUid(uid);
        return new JsonResult<>(ok,data);
    }

    @RequestMapping("{cid}/num/add")
    public JsonResult<Integer> addNum(@PathVariable("cid") Integer cid,HttpSession session){
        Integer uid=getuidFromSession(session);
        String username=getUsernameFromSession(session);
        Integer data=cartService.addNum(cid, uid, username);
        return new JsonResult<>(ok,data);
    }

    @RequestMapping("{cid}/num/reduce")
    public JsonResult<Integer> reduceNum(@PathVariable("cid") Integer cid,HttpSession session){
        Integer uid=getuidFromSession(session);
        String username=getUsernameFromSession(session);
        Integer data=cartService.reduceNum(cid, uid, username);
        return new JsonResult<>(ok,data);
    }

    @RequestMapping("list")
    public JsonResult<List<CartVo>> getVoByCid(Integer[] cids,HttpSession session){
        Integer uid=getuidFromSession(session);
        List<CartVo> data=cartService.findVoByCid(uid, cids);
        return new JsonResult<>(ok,data);
    }

}
