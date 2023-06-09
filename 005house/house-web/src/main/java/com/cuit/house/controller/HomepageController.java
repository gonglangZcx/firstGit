package com.cuit.house.controller;

import com.cuit.house.pojo.House;
import com.cuit.house.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomepageController {

    @Autowired
    private HouseService houseService;

    @RequestMapping("")
    public String index(ModelMap modelMap){
        return "redirect:/index";
    }

    @RequestMapping("index")
    public String indexLastest(ModelMap modelMap){
       List<House> houses= houseService.getLastes();//获取新上的房源
       modelMap.put("recomHouses",houses);
       return "/homepage/index";
    }


}
