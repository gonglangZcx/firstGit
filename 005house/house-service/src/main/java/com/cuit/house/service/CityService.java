package com.cuit.house.service;

import com.cuit.house.pojo.City;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
    @Autowired
    private CityService cityService;


    public List<City> getAllCitys() {
        City city=new City();
        city.setCityCode("110000");
        city.setCityName("北京市");
        city.setId(1);
        return Lists.newArrayList(city);
    }
}
