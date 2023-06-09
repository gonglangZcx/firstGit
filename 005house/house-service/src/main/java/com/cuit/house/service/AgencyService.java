package com.cuit.house.service;

import com.cuit.house.mapper.AgencyMapper;
import com.cuit.house.page.PageData;
import com.cuit.house.page.PageParams;
import com.cuit.house.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgencyService {
    @Autowired
    private AgencyMapper agencyMapper;

    @Value("${file.prefix}")
    private String prefix;


/*
访问user表详情信息
添加用户头像
 */
    public User getAgentDetail(Long userId) {
        User user=new User();
        user.setId(userId);
        user.setType(2);//2就是查询经纪人
        List<User> list= agencyMapper.selectAgent(user, PageParams.build(1,1));
        setImg(list);
        if(!list.isEmpty()){
            return list.get(0);
        }
        return null;
    }

    private void setImg(List<User> list) {
        list.forEach(i->{
            i.setAvatar(prefix+i.getAvatar());
        });
    }

    public PageData<User> getAllAgent(PageParams pageParams) {
        List<User> agents = agencyMapper.selectAgent(new User(), pageParams);
        setImg(agents);
        Long count = agencyMapper.selectAgentCount(new User());
        return PageData.buildPage(agents,count,pageParams.getPageSize(),pageParams.getPageNum());
    }
}
