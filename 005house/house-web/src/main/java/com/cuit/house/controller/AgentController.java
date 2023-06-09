package com.cuit.house.controller;

import com.cuit.house.page.PageData;
import com.cuit.house.page.PageParams;
import com.cuit.house.pojo.User;
import com.cuit.house.service.AgencyService;
import com.cuit.house.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AgentController {
    @Autowired
    private AgencyService agencyService;
    @Autowired
    private HouseService houseService;

    @RequestMapping("/agency/agentList")
    public String agentList(Integer pageSize, Integer pageNum, ModelMap modelMap){
        PageData<User> ps=agencyService.getAllAgent(PageParams.build(pageSize,pageNum));
        modelMap.put("ps",ps);
        return "/user/agent/agentList";
    }
}
