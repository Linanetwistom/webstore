package com.lina.webstore.service.impl;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.lina.webstore.entity.Address;
import com.lina.webstore.mapper.AddressMapper;
import com.lina.webstore.service.IAddressService;
import com.lina.webstore.service.IDistrictService;
import com.lina.webstore.service.ex.AccessDeniedException;
import com.lina.webstore.service.ex.AddressCountLimitException;
import com.lina.webstore.service.ex.AddressNotFoundException;
import com.lina.webstore.service.ex.DeleteExceptrion;
import com.lina.webstore.service.ex.InsertException;
import com.lina.webstore.service.ex.UpdateExceptrion;

@Service
public class AddressServiceImpl implements IAddressService{
    @Autowired
    AddressMapper addressMapper;

    @Autowired
    IDistrictService districtService;

    @Value("${user.address.max-count}")
    private Integer maxCount;

    @Override
    public void addNewAddress(Integer uid, String username, Address address) {
        Integer count=addressMapper.countByuid(uid);
        if(count>=maxCount){
            throw new AddressCountLimitException("收货地址数量超出范围");

        }
        address.setUid(uid);
        Integer isdefault=count==0? 1:0;
        address.setIsDefault(isdefault);

        String provinceName=districtService.findNameByCode(address.getProvinceCode());
        String cityName=districtService.findNameByCode(address.getCityCode());
        String areaName=districtService.findNameByCode(address.getAreaCode());
        address.setProvinceName(provinceName);
        address.setCityCode(cityName);
        address.setAreaName(areaName);

        address.setCreatedTime(new Date());
        address.setModifiedTime(new Date());
        address.setCreatedUser(username);
        address.setModifiedUser(username);

        Integer row = addressMapper.insert(address);
        if(row!=1){
            throw new InsertException("新增用户地址时产生异常");
        }
    }

    @Override
    public List<Address> getByUid(Integer uid) {
        List<Address> list=addressMapper.findByUid(uid);
        for(Address address:list){
            address.setProvinceCode(null);
            address.setCityCode(null);
            address.setAreaCode(null);
            address.setCreatedTime(null);
            address.setCreatedUser(null);
            address.setModifiedTime(null);
            address.setModifiedUser(null);
            
        }
        return list;
    }

    @Override
    public void setDefault(Integer aid, Integer uid, String username) {
        Address address=addressMapper.findByAid(aid);
        if(address==null){
            throw new AddressNotFoundException("用户地址不存在");
        }
        if(!address.getUid().equals(uid)){
            throw new AccessDeniedException("非法访问");
        }
        Integer rows = addressMapper.updateNonDefault(uid);
        if(rows<1){
            throw new UpdateExceptrion("更新时产生未知异常");
        }

        Integer row=addressMapper.updateDefaultByAid(aid, username, new Date());
        if(row!=1){
            throw new UpdateExceptrion("更新时产生未知异常");
        }

    }

    @Override
    public void delete(Integer aid, Integer uid, String username) {

        Address address= addressMapper.findByAid(aid);
        if(address==null){
            throw new AddressNotFoundException("用户地址不存在");
        }
        if(!address.getUid().equals(uid)){
            throw new AccessDeniedException("非法访问");
        }

        Integer rows = addressMapper.deleteByAid(aid);
        if(rows!=1){
            throw new DeleteExceptrion("删除时产生未知异常");
        }

        Integer count = addressMapper.countByuid(uid);
        if(count==0){
            return;
        }

        if(address.getIsDefault()==0){
            return;
        }
        Address result=addressMapper.findLastModified(uid);
        Integer row=addressMapper.updateDefaultByAid(result.getAid(), username, new Date());
        if(row!=1){
            throw new UpdateExceptrion("设置默认地址时异常");
        }

    }

    @Override
    public Address getByAid(Integer aid, Integer uid) {
        Address address= addressMapper.findByAid(aid);
        if(address==null){
            throw new AddressNotFoundException("用户地址不存在");
        }
        if(!address.getUid().equals(uid)){
            throw new AccessDeniedException("非法访问");
        }
        address.setCreatedTime(null);
        address.setCreatedUser(null);
        address.setModifiedTime(null);
        address.setModifiedUser(null);
        address.setCityCode(null);
        address.setCityCode(null);
        address.setProvinceCode(null);

        return address;

    }


    
}