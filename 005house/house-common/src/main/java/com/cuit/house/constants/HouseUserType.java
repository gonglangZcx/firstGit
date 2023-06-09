package com.cuit.house.constants;

public enum  HouseUserType {
    //SALE售卖 BOOKMARK  收藏
    SALE(1),BOOKMARK(2);

    public final Integer value;

    HouseUserType(Integer value) {
        this.value=value;
    }
}
