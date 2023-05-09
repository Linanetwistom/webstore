package com.lina.webstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lina.webstore.entity.Address;
import com.lina.webstore.service.IAddressService;
import com.lina.webstore.util.JsonResult;

import jakarta.servlet.http.HttpSession;


@RestController
@RequestMapping("addresses")
public class AddressController extends BaseController{

    @Autowired
    IAddressService addressService;

    @RequestMapping("add_new_address")
    public JsonResult<Void> addNewAddress(Address address,HttpSession session){
        String username=getUsernameFromSession(session);
        Integer uid=getuidFromSession(session);
        addressService.addNewAddress(uid,username,address);
        return new JsonResult<>(ok);

    }
    @RequestMapping({"/",""})
    public JsonResult<List<Address>> getByUid(HttpSession session){
        Integer uid=getuidFromSession(session);
        List<Address> data=addressService.getByUid(uid);
        return new JsonResult<>(ok,data);
    }

    @RequestMapping("{aid}/set_default")
    public JsonResult<Void> setDefault(@PathVariable("aid") Integer aid,HttpSession session){
        String username=getUsernameFromSession(session);
        Integer uid=getuidFromSession(session);
        addressService.setDefault(aid, uid, username);
        return new JsonResult<>(ok);
    }

    @RequestMapping("{aid}/delete")
    public JsonResult<Void> delete(@PathVariable("aid") Integer aid,HttpSession session){
        String username=getUsernameFromSession(session);
        Integer uid=getuidFromSession(session);
        addressService.delete(aid, uid, username);
        return new JsonResult<>(ok);
    }

    
}
