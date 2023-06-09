package com.cuit.house.controller;

import com.cuit.house.constants.CommonConstants;
import com.cuit.house.constants.HouseUserType;
import com.cuit.house.page.PageData;
import com.cuit.house.page.PageParams;
import com.cuit.house.pojo.House;
import com.cuit.house.pojo.HouseUser;
import com.cuit.house.pojo.User;
import com.cuit.house.result.ResultMsg;
import com.cuit.house.service.AgencyService;
import com.cuit.house.service.CityService;
import com.cuit.house.service.HouseService;
import com.cuit.house.service.ReommendService;
import com.cuit.house.uitls.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.Instant;
import java.util.List;

@Controller
public class HouseController {
    @Autowired
    private HouseService houseService;
    @Autowired
    private AgencyService agencyService;
    @Autowired
    private ReommendService reommendService;
    @Autowired
    private CityService cityService;
    /*
     实现分页
     支持小区搜索，类型搜索
     支持排序
     支持展示图片，价格，标题，地址等信息
     */
    @RequestMapping("/house/list")
    public String houseList(Integer pageSize, Integer pageNum, House query, ModelMap modelMap){
        PageData<House> ps = houseService.queryHouse(query, PageParams.build(pageSize, pageNum));
        List<House> hotHouse = reommendService.getHotHouse(CommonConstants.RECOM_SIZE);
        modelMap.put("recomHouses",hotHouse);
        modelMap.put("ps",ps);
        modelMap.put("vo",query);
        return "house/listing";
    }

    /*
    详情：
     查询房屋详情，如果房屋是经纪人发出来的
     */
    @RequestMapping("house/detail")
    public String houseDetail(Long id,ModelMap modelMap){
      House house=  houseService.queryOneHouse(id);
      HouseUser houseUser=houseService.getHouseUser(id);
      //热门加1
        reommendService.increase(id);
      if(houseUser.getUserId() !=null && !houseUser.getUserId().equals(0)){
          modelMap.put("agent",agencyService.getAgentDetail(houseUser.getUserId()));
      }
        List<House> hotHouse = reommendService.getHotHouse(CommonConstants.RECOM_SIZE);
        modelMap.put("recomHouses",hotHouse);
      modelMap.put("house",house);
      return "/house/detail";
    }

    //经纪人详情
    @RequestMapping("/agency/agentDetail")
    public String agentDetail(Long id,ModelMap modelMap){
        User user = agencyService.getAgentDetail(id);
        House query=new House();
        query.setUserId(id);
        query.setBookmarked(false);//售卖
        //显示经纪人的三个房产
        PageData<House> bindHouse = houseService.queryHouse(query, new PageParams(3, 1));
        if(bindHouse!=null){
            modelMap.put("bindHouses",bindHouse.getList());
        }
        modelMap.put("agent",user);
        return "/user/agent/agentDetail";
    }

    @RequestMapping("/house/toAdd")
    public String toAdd(ModelMap modelMap){
        modelMap.put("citys",cityService.getAllCitys());
        modelMap.put("communitys",houseService.getAllCommunitys());
        return "/house/add";
    }
    /*
    1.获取用户信息 2.添加房产信息，添加用户绑定房产信息
     */
    @RequestMapping("/house/add")
    public String doAdd(House house){
                //拦截器里面进行获取
        User user =  UserContext.getUser();
        // 设置一个上线的状态
        house.setState(CommonConstants.HOUSE_STATE_UP);
        houseService.addHouse(house,user);
        //跳转到首页
        return "redirect:/index";
    }
    //个人房产
    @RequestMapping("house/ownlist")
    public String ownlist(House house,Integer pageNum,Integer pageSize,ModelMap modelMap){
        User user = UserContext.getUser();
        house.setUserId(user.getId());
        house.setBookmarked(false);
        modelMap.put("ps",houseService.queryHouse(house,PageParams.build(pageSize,pageNum)));
        modelMap.put("pageType","own");//区分是售卖还是收藏
        return "/house/ownlist";
    }

    //评分
    @RequestMapping("/house/rating")
    @ResponseBody
    public ResultMsg houseRate(Double rating,Long id){
        houseService.updateRating(id,rating);
        return ResultMsg.successMsg("ok");
    }
    //收藏
    @ResponseBody
    @RequestMapping("house/bookmark")
    public ResultMsg bookmark(Long id){
        User user = UserContext.getUser();
        houseService.bindUserToHouse(id,user.getId(),true);//true就是收藏
        return ResultMsg.successMsg("ok");
    }
    //删除收藏
    @ResponseBody
    @RequestMapping("house/unbookmark")
    public ResultMsg unbookmark(Long id){
        User user = UserContext.getUser();
        houseService.unbindUserToHouse(id,user.getId(), HouseUserType.BOOKMARK);
        return ResultMsg.successMsg("ok");
    }
    //收藏列表
    @RequestMapping("house/bookmarked")
    public String bookmarked(House house,Integer pageNum,Integer pageSize,ModelMap modelMap){
        User user = UserContext.getUser();
        house.setUserId(user.getId());
        house.setBookmarked(true);
        modelMap.put("ps",houseService.queryHouse(house,PageParams.build(pageSize,pageNum)));
        modelMap.put("pageType","book");//区分是售卖还是收藏
        return "/house/ownlist";
    }
    //删除房产
    @RequestMapping("house/del")
    public String delete(Long id,String pageType){
        User user = UserContext.getUser();
        houseService.unbindUserToHouse(id,user.getId(),
                pageType.equals("own")?HouseUserType.SALE:HouseUserType.BOOKMARK
                );
        return "redirect:/house/ownlist";
    }
}
