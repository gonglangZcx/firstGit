package com.cuit.house.mapper;

import com.cuit.house.page.PageParams;
import com.cuit.house.pojo.Community;
import com.cuit.house.pojo.House;
import com.cuit.house.pojo.HouseUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface HouseMapper {
    public List<House> selectPageHouses(@Param("house") House house,@Param("pageParams")  PageParams pageParams);

    //总数
    public Long selectPageCount(@Param("house") House house);

    List<Community> selectCommunity(Community community);

    HouseUser selectSaleHouseUser(@Param("id") Long houseId);

    void insert(House house);

    HouseUser selectHouseUser(@Param("userId") Long userId,@Param("houseId")  Long houseId,@Param("type")  Integer integer);

    void insertHouseUser(HouseUser houseUser);

    void updateHouse(House updateHouse);

    void deleteHouseUser(@Param("id") Long id,@Param("userId") Long userId,@Param("type")  Integer type);

}
