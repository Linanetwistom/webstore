package com.lina.webstore.service.impl;


import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lina.webstore.entity.Cart;
import com.lina.webstore.entity.Product;
import com.lina.webstore.mapper.CartMapper;
import com.lina.webstore.mapper.ProductMapper;
import com.lina.webstore.service.ICartService;
import com.lina.webstore.service.ex.AccessDeniedException;
import com.lina.webstore.service.ex.CartNotFoundException;
import com.lina.webstore.service.ex.InsertException;
import com.lina.webstore.service.ex.UpdateExceptrion;
import com.lina.webstore.vo.CartVo;

@Service
public class CartServiceImpl implements ICartService{
    @Autowired
    CartMapper cartMapper;

    @Autowired
    ProductMapper productMapper;

    @Override
    public void addToCart(Integer uid, Integer pid, Integer amount, String username) {

        Cart result=cartMapper.findByUidAndPid(uid, pid);

        if(result==null){
            Cart cart=new Cart();
            cart.setUid(uid);
            cart.setPid(pid);
            cart.setNum(amount);
            Product product=productMapper.findById(pid);
            cart.setPrice(product.getPrice());
            cart.setCreatedUser(username);
            cart.setCreatedTime(new Date());
            cart.setModifiedUser(username);
            cart.setModifiedTime(new Date());

            Integer row=cartMapper.insert(cart);
            if(row!=1){
                throw new InsertException("加入购物车异常 ");
            }

        }else{
            Integer num=result.getNum()+amount;

            Integer rows=cartMapper.updateNumByCid(result.getCid(), num, username, new Date());

            if(rows!=1){
                throw new UpdateExceptrion("更新购物车异常");
            }
        }


    }

    @Override
    public List<CartVo> findVoByUid(Integer uid) {
        return cartMapper.findVoByUid(uid);
    }

    @Override
    public Integer addNum(Integer cid, Integer uid, String username) {
        Cart result=cartMapper.findByCid(cid);
        if(result==null){
            throw new CartNotFoundException("数据不存在");
        }
        if(!result.getUid().equals(uid)){
            throw new AccessDeniedException("非法访问");
        }
        Integer num=result.getNum()+1;
        Integer rows=cartMapper.updateNumByCid(cid,num,username,new Date());
        if(rows!=1){
            throw new UpdateExceptrion("更新商品数量异常");
        }

        return num;
    }

    @Override
    public Integer reduceNum(Integer cid, Integer uid, String username) {
        Cart result=cartMapper.findByCid(cid);
        if(result==null){
            throw new CartNotFoundException("数据不存在");
        }
        if(!result.getUid().equals(uid)){
            throw new AccessDeniedException("非法访问");
        }
        Integer num=result.getNum()-1;
        Integer rows=cartMapper.updateNumByCid(cid,num,username,new Date());
        if(rows!=1){
            throw new UpdateExceptrion("更新商品数量异常");
        }

        return num;
    }

    @Override
    public List<CartVo> findVoByCid(Integer uid, Integer[] cids) {
        List<CartVo> list=cartMapper.findVoByCids(cids);
        Iterator<CartVo> it=list.iterator();
        while(it.hasNext()){
            CartVo cartVo=it.next();
            if(!cartVo.getUid().equals(uid)){
                list.remove(cartVo);
            }
        }
        return list;
    }



    
}