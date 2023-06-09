package com.cuit.house.uitls;

import com.cuit.house.pojo.User;

public class UserContext {
    private static final ThreadLocal<User> USER_HODLER=new ThreadLocal<User>();
    public static void setUser(User user){USER_HODLER.set(user);}
    public static void remove(){USER_HODLER.remove();}
    public static User getUser(){return USER_HODLER.get();}
}
