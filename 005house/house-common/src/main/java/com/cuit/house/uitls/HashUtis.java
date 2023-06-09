package com.cuit.house.uitls;

import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import java.nio.charset.Charset;

public class HashUtis {

  private static final HashFunction FUNCTION= Hashing.md5();
  //定义一个盐
    private static final String SALT="cuit.com";

  public static String encryPassword(String password){
      HashCode hashCode = FUNCTION.hashString(password+SALT, Charset.forName("utf-8"));
      return hashCode.toString();
  }

  public static void main(String[] args){
      String s = encryPassword("123456");
      System.out.println(s);


  }
}
