package com.lina.webstore.mapper;


import java.util.Date;
import java.util.List;

import com.lina.webstore.entity.Address;

public interface AddressMapper {

    Integer insert(Address address);

    Integer countByuid(Integer uid);

    List<Address> findByUid(Integer uid);

    Address findByAid(Integer aid);

    Integer updateNonDefault(Integer uid);

    Integer updateDefaultByAid(Integer aid,String modifiedUser,Date modifiedTime);

    Integer deleteByAid(Integer aid);

    Address findLastModified(Integer uid);

}
