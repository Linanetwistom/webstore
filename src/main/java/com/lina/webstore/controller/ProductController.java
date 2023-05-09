package com.lina.webstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lina.webstore.entity.Product;
import com.lina.webstore.service.IProductService;
import com.lina.webstore.util.JsonResult;


@RestController
@RequestMapping("products")
public class ProductController extends BaseController{

    @Autowired
    IProductService productService;

    @RequestMapping("hot_list")
    public JsonResult<List<Product>> getHotList(){
        List<Product> data=productService.findHotList();
        return new JsonResult<>(ok,data);
    }
    
    @RequestMapping("{id}/details")
    public JsonResult<Product> getProduct(@PathVariable("id") Integer id){
        Product data=productService.findByPid(id);
        return new JsonResult<>(ok,data);

    }
}
