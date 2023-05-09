package com.lina.webstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lina.webstore.entity.District;
import com.lina.webstore.service.IDistrictService;
import com.lina.webstore.util.JsonResult;



@RestController
@RequestMapping("districts")
public class DistrictController extends BaseController{

    @Autowired
    IDistrictService districtService;

    @RequestMapping({"/",""})
    public JsonResult<List<District>> getProvinceList(String parent){

        List<District> data= districtService.getByParent(parent);
        return new JsonResult<>(ok,data);

    }

    
}
