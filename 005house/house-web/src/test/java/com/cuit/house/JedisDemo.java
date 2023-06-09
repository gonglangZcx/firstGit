package com.cuit.house;

import org.junit.Test;
import redis.clients.jedis.Jedis;

public class JedisDemo {
    @Test
    public void testRedis(){
        Jedis jedis=new Jedis("192.168.213.121",6379);
        jedis.set("username","laoli");
        jedis.close();
    }

    @Test
    public void getRedis(){
        Jedis jedis=new Jedis("192.168.213.121",6379);
        jedis.select(1);
        jedis.get("usernmae");

        jedis.hset("myhash","addr","bj");
        jedis.hset("user:1001","username","qek");
        jedis.hset("user:1001","name","33");

        jedis.lpush("teamName","日本","美国","英国");

        jedis.zadd("english:scoreboard",90,"laoli");
        jedis.zadd("english:scoreboard",93,"老王");
        jedis.zadd("english:scoreboard",92,"老廖");





        jedis.close();
    }
}
