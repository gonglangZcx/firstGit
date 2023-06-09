package com.cuit.house.service;

import com.cuit.house.page.PageParams;
import com.cuit.house.pojo.House;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ReommendService {

    @Autowired
    private HouseService houseService;
    private static final String HOT_HOUSE_KEY="hot_house";

    public void increase(Long id){
        Jedis jedis=new Jedis("");
        jedis.zincrby(HOT_HOUSE_KEY,1.0d,id+"");
        //我们不能让链表过长
        jedis.zremrangeByRank(HOT_HOUSE_KEY,0,-11);
        jedis.close();
    }

    //拿到这个热度
    public List<Long> getHot(){
        Jedis jedis=new Jedis("");
        Set<String> idSet = jedis.zrevrange(HOT_HOUSE_KEY, 0, -1);
        List<Long> ids = idSet.stream().map(Long::parseLong).collect(Collectors.toList());
        return ids;
    }

    //返回热门里面的房产的信息
    public List<House> getHotHouse(Integer size){
        List<Long> list = getHot();
        list=list.subList(0,Math.min(list.size(),size));
        if(list.isEmpty()){
            return Lists.newArrayList();
        }
        House query=new House();
        query.setIds(list);
        List<House> houses = houseService.queryAndSetImg(query, PageParams.build(size, 1));
        List<Long> order=list;
        //排序  瓜娃工具类来进行排序
        Ordering<House> houseSort=Ordering.natural().onResultOf(hs->{
            return order.indexOf(hs.getId());//小标进行排序
        });
        return houseSort.sortedCopy(houses);
    }


}
