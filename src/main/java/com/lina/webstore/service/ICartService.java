package com.lina.webstore.service;

import java.util.List;

import com.lina.webstore.vo.CartVo;

public interface ICartService {

    void addToCart(Integer uid,Integer pid,Integer amount,String username);

    List<CartVo> findVoByUid(Integer uid);

    Integer addNum(Integer cid,Integer uid,String username);

    Integer reduceNum(Integer cid,Integer uid,String username);

    List<CartVo> findVoByCid(Integer uid,Integer[] cids);

}
