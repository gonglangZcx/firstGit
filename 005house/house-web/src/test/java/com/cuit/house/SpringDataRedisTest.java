package com.cuit.house;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringDataRedisTest {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testString(){
//        redisTemplate.opsForValue().set("name","aaaa");
//        String name = (String) redisTemplate.opsForValue().get("name");
//        System.out.println(name);
        //存储一个10秒的key值
//        redisTemplate.opsForValue().set("key1","value1",10l, TimeUnit.SECONDS);
        //存在就要设置，不存在就不设置
        Boolean aBoolean = redisTemplate.opsForValue().setIfAbsent("key1", "value1");
        System.out.println(aBoolean);
    }
    @Test
    public void testHash(){
        HashOperations hashOperations = redisTemplate.opsForHash();
//        hashOperations.put("002","name","xiaoming");
//        hashOperations.put("002","age","20");
//        hashOperations.put("002","address","cd");
//        String name = (String) hashOperations.get("002", "name");
//        System.out.println(name);
        Set keys = hashOperations.keys("002");
        for (Object key:keys
             ) {
            System.out.println(key);
        }
        List values = hashOperations.values("002");
        System.out.println(values);
    }
    
    //操作list 类型
    @Test
    public void testList(){
        ListOperations listOperations = redisTemplate.opsForList();
//        listOperations.leftPush("mylist", "a");
//        listOperations.leftPushAll("mylist","b","d","c");

        List mylist = listOperations.range("mylist", 0, -1);
        System.out.println(mylist);
    }

    //操作zset类型的数据
    @Test
    public void testZset(){
        ZSetOperations zSetOperations = redisTemplate.opsForZSet();
        zSetOperations.add("myZset","a",9.0);
        zSetOperations.add("myZset","b",8.0);
        zSetOperations.add("myZset","c",7.0);
        zSetOperations.add("myZset","d",11.0);

        //取值
        Set<String> myZset = zSetOperations.range("myZset", 0, -1);
        System.out.println(myZset);

        //修改分数
        zSetOperations.incrementScore("myZset","c",20.0);

        myZset= zSetOperations.range("myZset", 0, -1);
        System.out.println(myZset);
        //删除成员
        zSetOperations.remove("myZset","a","b");
        myZset= zSetOperations.range("myZset", 0, -1);
        System.out.println(myZset);
    }
    //通用操作
    @Test
    public void testCommon(){
        Set keys = redisTemplate.keys("*");
        System.out.println(keys);


    }


}
