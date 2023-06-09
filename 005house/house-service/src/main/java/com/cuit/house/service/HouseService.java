package com.cuit.house.service;

import com.cuit.house.constants.HouseUserType;
import com.cuit.house.mapper.HouseMapper;
import com.cuit.house.page.PageData;
import com.cuit.house.page.PageParams;
import com.cuit.house.pojo.Community;
import com.cuit.house.pojo.House;
import com.cuit.house.pojo.HouseUser;
import com.cuit.house.pojo.User;
import com.cuit.house.uitls.BeanHelper;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HouseService {
    @Autowired
    private HouseMapper houseMapper;
    @Value("${file.prefix}")
    private String prefix;
    @Autowired
    private FileService fileService;
    //这里querHouse要先通过小区名字，定位到小区的id，通过小区的id查找到房产
    /*
    查询小区
    添加图片服务器地址前缀
    构建分页结果
     */
    public PageData<House> queryHouse(House query, PageParams pageParams) {
       List<House> houses = Lists.newArrayList();
       if(!Strings.isNullOrEmpty(query.getName())){
           Community community=new Community();
           community.setName(query.getName());
           List<Community> communities=houseMapper.selectCommunity(community);
           if(!communities.isEmpty()){
               query.setCommunityId(communities.get(0).getId());
           }
       }
        houses=queryAndSetImg(query,pageParams);
        Long count = houseMapper.selectPageCount(query);
        return PageData.buildPage(houses,count,pageParams.getPageSize(),pageParams.getPageNum());
    }

    public List<House> queryAndSetImg(House query, PageParams pageParams) {
        List<House> houses = houseMapper.selectPageHouses(query, pageParams);
        houses.forEach(h->{
            //分页只会显示第一个图片
                    h.setFirstImg(prefix+h.getImageList().get(0));
                    //详情会显示多个图片
                    h.setImageList(h.getImageList().stream().map(img->prefix+img).collect(Collectors.toList()));
                    //显示户型图
                    h.setFloorPlanList(h.getFloorPlanList().stream().map(img->prefix+img).collect(Collectors.toList()));
                }
                );
        return houses;
    }

    public House queryOneHouse(Long id) {
        House query=new House();
        query.setId(id);
        List<House> houses = queryAndSetImg(query, PageParams.build(1, 1));
        if(!houses.isEmpty()){
            return houses.get(0);
        }
        return null;
    }

    public HouseUser getHouseUser(Long houseId) {
        HouseUser houseUser=houseMapper.selectSaleHouseUser(houseId);
        return houseUser;
    }

    public List<House> getLastes() {
        House query=new House();
        query.setSort("create_time");
        List<House> houses = queryAndSetImg(query, new PageParams(8, 1));
        return houses;
    }

    public List<Community> getAllCommunitys() {
        Community community=new Community();
        return houseMapper.selectCommunity(community);
    }
/*
 添加房屋图片
 添加户型图片
 插入房产信息
 绑定用户到房产的关系就是第三张表
 */
    public void addHouse(House house, User user) {
        //判断用户有咩有上传房屋的图片
        if(CollectionUtils.isNotEmpty(house.getHouseFiles())){
            String images = Joiner.on(",").join(fileService.getImgPath(house.getHouseFiles()));
            house.setImages(images);
        }
        if(CollectionUtils.isNotEmpty(house.getFloorPlanList())){
            String images = Joiner.on(",").join(fileService.getImgPath(house.getFloorPlanFiles()));
            house.setFloorPlan(images);
        }
        BeanHelper.onInsert(house);
        houseMapper.insert(house);
        // 绑定用户到房产的关系就是第三张表   false 代表是收藏还是售卖  false售卖
        bindUserToHouse(house.getId(),user.getId(),false);
    }

    public void bindUserToHouse(Long houseId, Long userId, boolean collect) {
        //先查询这个用户和这个房子的关系存在，如果本来就有，就什么都不操作
       HouseUser existhouseUser= houseMapper.selectHouseUser(userId,houseId,collect? HouseUserType.BOOKMARK.value:
                HouseUserType.SALE.value);
       if(existhouseUser!=null){
           return;
       }
       HouseUser houseUser=new HouseUser();
       houseUser.setHouseId(houseId);
       houseUser.setUserId(userId);
       houseUser.setType(collect? HouseUserType.BOOKMARK.value: HouseUserType.SALE.value);
       BeanHelper.setDefaultProp(houseUser,HouseUser.class);
       BeanHelper.onInsert(houseUser);
       houseMapper.insertHouseUser(houseUser);
    }

    public void updateRating(Long id, Double rating) {
        House house = queryOneHouse(id);
        Double oldRating = house.getRating();
        //取平均值
        Double newRating = oldRating.equals(0D) ? rating : Math.min((oldRating + rating) / 2, 5);
        House updateHouse=new House();
        updateHouse.setId(id);
        updateHouse.setRating(newRating);
        BeanHelper.onUpdate(updateHouse);
        houseMapper.updateHouse(updateHouse);
    }

    public void unbindUserToHouse(Long id, Long userId, HouseUserType type) {
        houseMapper.deleteHouseUser(id,userId,type.value);
    }
}
