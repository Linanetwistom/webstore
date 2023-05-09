package com.lina.webstore.mapper;

import java.util.List;

import com.lina.webstore.entity.District;

public interface DistrictMapper {
   List<District>  findByParent(String parent);
   
   String findNameByCode(String code);
}
