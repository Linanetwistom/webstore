package com.lina.webstore.service;

import java.util.List;

import com.lina.webstore.entity.District;

public interface IDistrictService {

    List<District> getByParent(String partent);

    String findNameByCode(String code);

}
